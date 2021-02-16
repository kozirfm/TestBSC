package com.example.testbsc.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testbsc.R
import com.example.testbsc.data.Party
import com.example.testbsc.ui.Data
import com.example.testbsc.ui.Error
import com.example.testbsc.ui.Loading
import com.example.testbsc.ui.adapters.MainRecyclerViewAdapter
import com.example.testbsc.ui.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModel<MainViewModel>()

    private lateinit var partyImage: ImageView
    private lateinit var partyName: TextView
    private lateinit var organizerImage: ImageView
    private lateinit var organizerName: TextView
    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var mainProgressBar: ProgressBar
    private lateinit var mainToolbar: Toolbar
    private lateinit var mainLayout: ConstraintLayout
    private val mainRecyclerViewAdapter by lazy { MainRecyclerViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initRecyclerView()

        mainViewModel.viewState.observe(this) { viewState ->
            when (viewState) {
                is Loading -> showLoading()
                is Data<*> -> {
                    updateView(viewState.data as Party)
                    showData()
                }
                is Error -> Toast
                    .makeText(this, viewState.t?.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun initView() {
        partyImage = findViewById(R.id.mainImageView)
        partyName = findViewById(R.id.mainPartyNameTextView)
        organizerImage = findViewById(R.id.mainOrganizerImageView)
        organizerName = findViewById(R.id.mainOrganizerNameTextView)
        mainRecyclerView = findViewById(R.id.mainMembersRecyclerView)
        mainProgressBar = findViewById(R.id.mainProgressBar)
        mainToolbar = findViewById(R.id.mainToolbar)
        mainLayout = findViewById(R.id.mainLayout)
    }

    private fun initRecyclerView() {
        mainRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainRecyclerView.adapter = mainRecyclerViewAdapter
    }


    private fun updateView(party: Party) {
        partyImage.loadImage(party.mainImage)
        partyName.text = party.partyName
        organizerImage.loadImage(party.organizer.image)
        organizerName.text = party.organizer.name
        mainRecyclerViewAdapter.members = party.members
    }

    private fun ImageView.loadImage(url: String) {
        this.load(url) {
            placeholder(R.drawable.ic_twotone_image_24)
            error(R.drawable.ic_twotone_error_24)
        }
    }

    private fun showLoading() {
        mainProgressBar.visibility = View.VISIBLE
        mainLayout.visibility = View.GONE
    }

    private fun showData() {
        mainProgressBar.visibility = View.GONE
        mainLayout.visibility = View.VISIBLE
    }

}