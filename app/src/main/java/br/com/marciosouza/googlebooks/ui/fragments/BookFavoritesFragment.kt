package br.com.marciosouza.googlebooks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.marciosouza.googlebooks.databinding.FragmentBookListBinding
import br.com.marciosouza.googlebooks.model.Volume
import br.com.marciosouza.googlebooks.repository.BookRepository
import br.com.marciosouza.googlebooks.ui.BookDetailActivity
import br.com.marciosouza.googlebooks.ui.adapter.BookListAdapter
import br.com.marciosouza.googlebooks.ui.viewmodel.BookFavoritesViewModel
import br.com.marciosouza.googlebooks.ui.viewmodel.BookVmFactory

class BookFavoritesFragment : Fragment() {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding

    private val viewModel: BookFavoritesViewModel by lazy {
        ViewModelProvider(this, BookVmFactory(BookRepository(requireContext()))).get(
            BookFavoritesViewModel::class.java
        )
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

        binding?.searchView?.visibility = View.GONE
        binding?.recycleView?.layoutManager = layoutManager

        binding?.recycleView?.addItemDecoration(
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        )

        viewModel.favoritesBook.observe(viewLifecycleOwner, Observer { items ->

            binding?.fragmentLoading?.root?.visibility = View.GONE
            binding?.recycleView?.adapter =
                BookListAdapter(items, this::openBookDetail)

        })
    }

    private fun openBookDetail(volume: Volume) {
        BookDetailActivity.open(requireContext(), volume)
    }
}
