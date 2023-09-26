package com.hiberus.magicandroidapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hiberus.magicandroidapp.databinding.RowCardListItemBinding
import com.hiberus.magicandroidapp.model.Card

class CardListAdapter : RecyclerView.Adapter<CardListAdapter.CardListViewHolder>() {

    private var cardList: List<Card> = emptyList()

    var onClickListener: (Card) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        val binding = RowCardListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {
        val item = cardList[position]

        holder.rootView.setOnClickListener {
            onClickListener.invoke(item)
        }

        holder.cardName.text = item.name
        holder.cardDescription.text = item.oracleText

        Glide.with(holder.cardImageView)
            .load(item.imageUris.artCrop)
            .into(holder.cardImageView)
    }

    inner class CardListViewHolder(binding: RowCardListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val rootView = binding.root
        val cardName = binding.tvCardNameList
        val cardImageView = binding.ivCardImageList
        val cardDescription = binding.tvCardDescriptionList
    }
}