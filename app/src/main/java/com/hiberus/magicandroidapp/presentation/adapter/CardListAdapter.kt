package com.hiberus.magicandroidapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hiberus.magicandroidapp.databinding.RowCardListItemBinding
import com.hiberus.magicandroidapp.model.Card

class CardListAdapter : RecyclerView.Adapter<CardListAdapter.CardListViewHolder>() {

    var cardList: ArrayList<Card> = ArrayList()

    var onClickListener: (Card) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        val binding =
            RowCardListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {
        val item = cardList[position]
        val images = item.imageUris

        holder.rootView.setOnClickListener {
            onClickListener.invoke(item)
        }

        holder.cardName.text = item.name
        holder.cardDescription.text = item.oracleText

        val manaColors = item.colors

        if (manaColors.contains("R")) holder.iv_red_mana.visibility = View.VISIBLE
        if (manaColors.contains("G")) holder.iv_green_mana.visibility = View.VISIBLE
        if (manaColors.contains("B")) holder.iv_black_mana.visibility = View.VISIBLE
        if (manaColors.contains("W")) holder.iv_white_mana.visibility = View.VISIBLE
        if (manaColors.contains("U")) holder.iv_blue_mana.visibility = View.VISIBLE

        Glide.with(holder.cardImageView)
            .load(
                if (!images.artCrop.isNullOrEmpty())
                    images.artCrop
                else
                    images.normal
            )
            .into(holder.cardImageView)
    }

    inner class CardListViewHolder(binding: RowCardListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val rootView = binding.root
        val cardName = binding.tvCardNameList
        val cardImageView = binding.ivCardImageList
        val cardDescription = binding.tvCardDescriptionList
        val iv_blue_mana = binding.ivBlueMana
        val iv_red_mana = binding.ivRedMana
        val iv_black_mana = binding.ivBlackMana
        val iv_white_mana = binding.ivWhiteMana
        val iv_green_mana = binding.ivGreenMana
    }

    fun submitList(list: List<Card>) {
        cardList.clear()
        cardList.addAll(list)
        notifyDataSetChanged()
    }
}