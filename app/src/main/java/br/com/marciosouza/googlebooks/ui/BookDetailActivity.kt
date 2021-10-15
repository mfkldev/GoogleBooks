package br.com.marciosouza.googlebooks.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.marciosouza.googlebooks.R
import br.com.marciosouza.googlebooks.databinding.ActivityBookDetailBinding
import br.com.marciosouza.googlebooks.model.Volume
import br.com.marciosouza.googlebooks.repository.BookRepository
import br.com.marciosouza.googlebooks.ui.viewmodel.BookDetailViewModel
import br.com.marciosouza.googlebooks.ui.viewmodel.BookVmFactory
import com.bumptech.glide.Glide

class BookDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailBinding

    private val viewModel: BookDetailViewModel by lazy { //????
        ViewModelProvider(
            this,
            BookVmFactory(
                BookRepository(this)
            )
        ).get(BookDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<Volume>(EXTRA_BOOK)?.let { volume ->
            assembleBookDetail(volume)

            observerViews(volume)
            viewModel.onCreate(volume)

        } ?: finish()
    }

    private fun observerViews(volume: Volume) {
        viewModel.isFavorite.observe(
            this,
            Observer { isFavorite ->
                if (isFavorite) {
                    binding.fabFavorite.setImageResource(R.drawable.ic_delete)
                    binding.fabFavorite.setOnClickListener {
                        viewModel.removeFromFavorites(volume)
                    }
                } else {
                    binding.fabFavorite.setImageResource(R.drawable.ic_add)
                    binding.fabFavorite.setOnClickListener {
                        viewModel.saveToFavorites(volume)
                    }
                }
            }
        )
    }

    private fun assembleBookDetail(
        volume: Volume
    ) {
        Glide.with(this) //??
            .load(volume.volumeInfo.imageLinks?.smallThumbnail)
            .error(R.drawable.ic_broken_image)
            .into(binding.itemBookImageBook)

        with(binding){
            itemBookTitulo.text = volume.volumeInfo.title
            itemBookAutores.text = volume.volumeInfo.authors?.toString().orEmpty()
            itemBookPaginas.text = volume.volumeInfo.pageCount?.toString().orEmpty()
            itemBooksDescricao.text = volume.volumeInfo.description
        }
    }

    companion object {
        private const val EXTRA_BOOK = "book"

        fun open(context: Context, volume: Volume) {
            val detailIntent = Intent(context, BookDetailActivity::class.java)
            detailIntent.putExtra(EXTRA_BOOK, volume)
            context.startActivity(detailIntent)
        }
    }
}
