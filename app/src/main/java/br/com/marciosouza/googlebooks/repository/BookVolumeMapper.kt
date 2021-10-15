package br.com.marciosouza.googlebooks.repository

import br.com.marciosouza.googlebooks.model.ImageLinks
import br.com.marciosouza.googlebooks.model.Volume
import br.com.marciosouza.googlebooks.model.VolumeInfo

object BookVolumeMapper {

    fun bookToVolume(book: Book) = Volume(
        book.id,
        book.selfLink,
        VolumeInfo(
            book.title,
            book.description,
            book.authors,
            book.publisher,
            book.publishedDate,
            book.pageCount,
            ImageLinks(book.smallThumbnail, book.thumbnail)
        )
    )

    fun volumeToBook(volume: Volume) =
        volume.run {
            Book(
                id,
                selfLink,
                volumeInfo.title.toString(),
                volumeInfo.description,
                volumeInfo.authors,
                volumeInfo.publisher,
                volumeInfo.publishedDate,
                volumeInfo.pageCount,
                volumeInfo.imageLinks?.smallThumbnail,
                volumeInfo.imageLinks?.thumbnail
            )
        }
}