package br.com.marciosouza.googlebooks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.marciosouza.googlebooks.R
import br.com.marciosouza.googlebooks.databinding.ItemBookBinding
import br.com.marciosouza.googlebooks.model.Volume
import com.bumptech.glide.Glide

class BookListAdapter(
    private val volumes: List<Volume>,
    private val onItemClick: (Volume) -> Unit
) : RecyclerView.Adapter<BookListAdapter.BookHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        return BookHolder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val volume = volumes[position]
        holder.bindHolder(volume)

        holder.itemView.setOnClickListener {
            onItemClick(volume)
        }
    }

    override fun getItemCount() = volumes.size

    class BookHolder(binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgCover = binding.itemBookImageBook
        val title = binding.itemBookTitulo
        val autor = binding.itemBookAutores
        val pageCount = binding.itemBookPaginas

        fun bindHolder(
            volume: Volume
        ) {
            Glide.with(itemView.context) //??
                .load(volume.volumeInfo.imageLinks?.smallThumbnail)
                .error(R.drawable.ic_broken_image)
                .into(imgCover)


            autor.text = volume.volumeInfo.authors.toString()
            title.text = volume.volumeInfo.title
            pageCount.text = volume.volumeInfo.pageCount.toString()
        }
    }
}
