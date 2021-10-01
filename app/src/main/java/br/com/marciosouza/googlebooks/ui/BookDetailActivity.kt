package br.com.marciosouza.googlebooks.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.marciosouza.googlebooks.R
import br.com.marciosouza.googlebooks.databinding.ActivityBookDetailBinding
import br.com.marciosouza.googlebooks.model.Volume
import com.squareup.picasso.Picasso

class BookDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val volume = intent.getParcelableExtra<Volume>(EXTRA_BOOK)
        if(volume != null){

            if(volume.volumeInfo.imageLinks?.smallThumbnail != null) {
                Picasso.get().load(volume.volumeInfo.imageLinks.smallThumbnail).into(
                    binding.itemBookImageBook)
            } else{
                binding.itemBookImageBook.setImageResource(R.drawable.ic_broken_image)
            }

            binding.itemBookTitulo.text = volume.volumeInfo.title
            binding.itemBookAutores.text = volume.volumeInfo.authors?.toString() ?: ""
            binding.itemBookPaginas.text = volume.volumeInfo.pageCount?.toString() ?: ""
            binding.itemBooksDescricao.text = volume.volumeInfo.description?.toString() ?: ""
        }else{
            finish()
        }
    }

    companion object {
        private const val EXTRA_BOOK = "book"

        fun open(context: Context, volume: Volume){
            val detailIntent = Intent(context, BookDetailActivity::class.java)
            detailIntent.putExtra(EXTRA_BOOK, volume)
            context.startActivity(detailIntent)
        }
    }
}