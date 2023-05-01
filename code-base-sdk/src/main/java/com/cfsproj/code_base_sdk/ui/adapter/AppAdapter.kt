package com.cfsproj.code_base_sdk.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cfsproj.code_base_sdk.databinding.CharacterItemBinding
import com.cfsproj.code_base_sdk.domain.DomainChar

@Deprecated("This adapter is not being used, is defined just for learning purposes", ReplaceWith("CharactersAdapter"))
class AppAdapter(private val  characters: MutableList<DomainChar> = mutableListOf(),
                 private val clickEvent: (DomainChar) -> Unit): RecyclerView.Adapter<AppAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: CharacterItemBinding) : RecyclerView.ViewHolder (binding.root){
        fun bind(domainChar: DomainChar) {

        }

    }

    fun updateItems(newItems: List<DomainChar>){

        val diffUtil = object : DiffUtil.Callback(){
            override fun getOldListSize() = characters.size
            override fun getNewListSize() = characters.size
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = characters[oldItemPosition] == characters[newItemPosition]
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = characters[oldItemPosition].name == characters[newItemPosition].name
        }

        val result = DiffUtil.calculateDiff(diffUtil)
        characters.clear()
        characters.addAll(newItems)
        result.dispatchUpdatesTo(this)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(CharacterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
           clickEvent( characters[position])
       }

        holder.bind(characters[position])

    }

}

