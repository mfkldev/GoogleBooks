package br.com.marciosouza.googlebooks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.marciosouza.googlebooks.R
import br.com.marciosouza.googlebooks.databinding.FragmentBookListBinding
import br.com.marciosouza.googlebooks.model.Volume
import br.com.marciosouza.googlebooks.ui.BookDetailActivity
import br.com.marciosouza.googlebooks.ui.adapter.BookListAdapter
import br.com.marciosouza.googlebooks.ui.viewmodel.BookListViewModel

class BookListFragment : Fragment() {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding

    private val viewModel: BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding?.recycleView?.layoutManager = layoutManager

        setDivider(layoutManager)
        appendBooks()
        viewModel.loadInitialBooks()
        setQueryText()
    }

    private fun setDivider(layoutManager: LinearLayoutManager) {
        binding?.recycleView?.addItemDecoration(
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        )
    }

    private fun appendBooks() {
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is BookListViewModel.State.Loading -> {
                    binding?.fragmentLoading?.root?.visibility = View.VISIBLE
                }
                is BookListViewModel.State.Loaded -> {
                    binding?.fragmentLoading?.root?.visibility = View.GONE
                    binding?.recycleView?.adapter =
                        BookListAdapter(state.items, this::openBookDetail)
                }
                is BookListViewModel.State.Error -> {
                    binding?.fragmentLoading?.root?.visibility = View.GONE
                    Toast.makeText(requireContext(), R.string.error_loading_book, Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun setQueryText() {
        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.search(query)
                    return true
                }
                return false
            }
            override fun onQueryTextChange(p0: String?) = false
        })
    }

    private fun openBookDetail(volume: Volume) {
        BookDetailActivity.open(requireContext(), volume)
    }
}
