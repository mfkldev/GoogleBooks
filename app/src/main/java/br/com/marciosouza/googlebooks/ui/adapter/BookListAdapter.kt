package br.com.marciosouza.googlebooks.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.marciosouza.googlebooks.R
import br.com.marciosouza.googlebooks.databinding.ItemBookBinding
import br.com.marciosouza.googlebooks.model.Volume
import com.squareup.picasso.Picasso

class BookListAdapter(
    val listItens: List<Volume>,
    private val context: Context, //??
    private val onItemClick: (Volume) -> Unit
): RecyclerView.Adapter<BookListAdapter.BookHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : BookHolder { //Carregar arquivo de Layout e criar instancia de BookViewHolder e retornar
        return BookHolder(ItemBookBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val volume = listItens[position]

        if(volume.volumeInfo.imageLinks?.smallThumbnail != null) {
            Picasso.get().load(volume.volumeInfo.imageLinks.smallThumbnail).into(holder.imgCover)
        } else{
            holder.imgCover.setImageResource(R.drawable.ic_broken_image)
        }

//        Picasso.get().load(volume.volumeInfo.imageLinks?.smallThumbnail).into(holder.imgCover)
//            ?: holder.imgCover.setImageResource(R.drawable.ic_broken_image)

        holder.titulos.text = volume.volumeInfo.title
        holder.autores.text = volume.volumeInfo.authors?.toString() ?: ""
        holder.paginas.text = volume.volumeInfo.pageCount?.toString() ?: ""
        holder.itemView.setOnClickListener{
            onItemClick(volume)
        }
    }

    override fun getItemCount(): Int = listItens.size

    class BookHolder(binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgCover = binding.itemBookImageBook
        val titulos = binding.itemBookTitulo
        val autores = binding.itemBookAutores
        val paginas = binding.itemBookPaginas
    }
}

