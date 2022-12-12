package com.example.apiunsplash.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apiunsplash.Api.RetrofitService
import com.example.apiunsplash.databinding.ItemPhotoBinding
import com.example.apiunsplash.model.Photos
import com.squareup.picasso.Picasso

class PhotoAdapter(): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {



    var listaPhotos = mutableListOf<Photos>()

    fun adicionarLista(lista: List<Photos>){
        this.listaPhotos = lista.toMutableList()
        notifyDataSetChanged()

    }
   inner class PhotoViewHolder(itemPhoto: ItemPhotoBinding): RecyclerView.ViewHolder(itemPhoto.root) {
       private val binding: ItemPhotoBinding

       private val image = itemPhoto.IvPhoto
       init {
           this.binding = itemPhoto
       }
       fun bind( photos: Photos ) {

           binding.txtPoster.text = photos.user.name
           binding.txtTitulo.text = photos.description
           Picasso.get().load(photos.urls.small).into(image)

       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemFilmeView = ItemPhotoBinding.inflate(
            layoutInflater,parent,false
        )
        return PhotoViewHolder(itemFilmeView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val filme = listaPhotos[position]
        holder.bind( filme )
    }

    override fun getItemCount(): Int {
       return listaPhotos.size
    }
}