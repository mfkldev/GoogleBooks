package br.com.marciosouza.googlebooks.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.marciosouza.googlebooks.R
import br.com.marciosouza.googlebooks.databinding.ActivityMainBinding
import br.com.marciosouza.googlebooks.databinding.LayoutLoadingBinding
import br.com.marciosouza.googlebooks.model.Volume
import br.com.marciosouza.googlebooks.ui.adapter.BookListAdapter
import br.com.marciosouza.googlebooks.ui.viewmodel.BookListViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainRecycleView.layoutManager = LinearLayoutManager(this)

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is BookListViewModel.State.Loading -> {
                    binding.mainLoading.root.visibility = View.VISIBLE
                }
                is BookListViewModel.State.Loaded -> {
                    binding.mainLoading.root.visibility = View.GONE
                    binding.mainRecycleView.adapter =
                        BookListAdapter(state.items, this@MainActivity::openBookDetail)
                }
                is BookListViewModel.State.Error -> {
                    binding.mainLoading.root.visibility = View.GONE
                    Toast.makeText(this@MainActivity, R.string.error_loading_book, Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.loadBooks()
    }

    private fun openBookDetail(volume: Volume) {
        BookDetailActivity.open(this, volume)
    }
}