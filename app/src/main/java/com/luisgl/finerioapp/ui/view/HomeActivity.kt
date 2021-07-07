package com.luisgl.finerioapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luisgl.finerioapp.R
import com.luisgl.finerioapp.data.network.models.MovementsItems
import com.luisgl.finerioapp.data.network.models.responses.movements.Data
import com.luisgl.finerioapp.databinding.ActivityHomeBinding
import com.luisgl.finerioapp.model.interfaces.home.HomeListener
import com.luisgl.finerioapp.ui.adapter.MovementsAdapter
import com.luisgl.finerioapp.ui.utils.hide
import com.luisgl.finerioapp.ui.utils.show
import com.luisgl.finerioapp.ui.utils.showAlert
import com.luisgl.finerioapp.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    // Data Received
    private var userEmail: String ?= null
    private var token: String ?= null
    private var id: String ?= null

    // RecyclerView
    lateinit var adapter: MovementsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    // Paginator
    private var page = 1
    private var loading = false
    private var limitItems = 10
    private var listItems: MutableList<MovementsItems> = ArrayList()
    private var start = 0
    private var end = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.homevm = viewModel
        viewModel.listener = this

        recyclerView = findViewById(R.id.rv_movements)
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        getDataReceived()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if ( dy > 0 ) {
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount

                    if ( !loading ) {

                        start = (page - 1) * limitItems
                        end = page * limitItems

                        if ( (visibleItemCount + pastVisibleItem) >= total ) {
                            if ( start != 0 ) {
                                viewModel.getMovementsService(token!!, id!!, start, end)
                                page++
                            }
                        }
                    }
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }

    private fun getDataReceived() {
        userEmail = intent.extras?.getString("email")
        token = intent.extras?.getString("token")
        id = intent.extras?.getString("id")

        tv_email.text = userEmail

        viewModel.getMovementsService(token!!, id!!, start, end)
        page++
    }

    override fun onSuccess(data: List<Data>) {
        getPage(data)
    }

    private fun getPage(data: List<Data>) {
        loading = true
        pb_home.show()

        data.forEach {
            listItems.add(MovementsItems(
                it.amount,
                it.type,
                it.description
            ))
        }

        Handler().postDelayed({
            if (::adapter.isInitialized ) {
                adapter.notifyDataSetChanged()
            } else {
                adapter = MovementsAdapter(listItems)
                recyclerView.adapter = adapter
            }
        }, 500)

        loading = false
        pb_home.hide()
    }

    override fun onError() {
        showAlert(
            getString(R.string.error_conection),
            getString(R.string.error_conection_message)
        )
    }

    override fun onShowProgress() {
        pb_home.show()
    }

    override fun onHideProgress() {
        pb_home.hide()
    }
}