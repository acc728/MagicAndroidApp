package com.hiberus.magicandroidapp.presentation.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hiberus.magicandroidapp.R
import com.hiberus.magicandroidapp.databinding.FragmentCardDetailBinding
import com.hiberus.magicandroidapp.model.Card
import com.hiberus.magicandroidapp.model.ResourceState
import com.hiberus.magicandroidapp.presentation.dialog.CardPreviewDialog
import com.hiberus.magicandroidapp.presentation.viewmodel.CardsViewModel
import com.hiberus.magicandroidapp.presentation.viewmodel.EditCardState
import com.hiberus.magicandroidapp.presentation.viewmodel.GetCardState
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CardDetailFragment : Fragment() {

    private var _binding: FragmentCardDetailBinding? = null
    private val binding get() = _binding!!

    private val args: CardDetailFragmentArgs by navArgs()

    private val cardsViewModel: CardsViewModel by activityViewModel()

    private var card: Card? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            editCard()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        cardsViewModel.fetchCard(args.cardId)
    }

    private fun initViewModel() {
        cardsViewModel.getCardLiveData.observe(viewLifecycleOwner) { state ->
            handleCardDetailState(state)
        }

        cardsViewModel.editCardLiveData.observe(viewLifecycleOwner) { state ->
            handleEditCardState(state)
        }
    }

    private fun handleCardDetailState(state: GetCardState) {
        when (state) {
            is ResourceState.Loading -> {
                binding.lavCardDetailImageLoading.visibility = View.VISIBLE
            }

            is ResourceState.Success -> {
                binding.lavCardDetailImageLoading.visibility = View.GONE
                card = state.result
                initUI(state.result)
            }

            is ResourceState.Error -> {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    private fun handleEditCardState(state: EditCardState) {
        when (state) {
            is ResourceState.Loading -> {
                //
            }

            is ResourceState.Success -> {
                findNavController().popBackStack()
            }

            is ResourceState.Error -> {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    private fun editCard() {
        val comments = binding.etCardDetailComments.text.toString()

        if(card != null) {
            card!!.comments = comments
            cardsViewModel.editCard(card!!)
        }
    }


    @SuppressLint("SetTextI18n")
    private fun initUI(card: Card) {
        Glide
            .with(binding.ivCardDetailImage)
            .load(card.imageUris.artCrop)
            .into(binding.ivCardDetailImage)

        binding.tvCardDetailCardName.text = card.name
        binding.tvCardDetailTypeLine.text = card.typeLine
        binding.tvCardDetailSetName.text = card.setName
        binding.tvCardDetailOracleText.text = card.oracleText
        binding.etCardDetailComments.setText(card.comments)
        binding.tvPriceUsd.text = (card.prices.usd ?: "N/A") + " $"
        binding.tvPriceEur.text = (card.prices.eur ?: "N/A") + " €"
        binding.tvCardmarketLink.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.msg_buy_link), Toast.LENGTH_SHORT)
                .show()
            val url = card.purchaseUris.cardmarket
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        binding.tvTcgplayerLink.setOnClickListener {
            val url = card.purchaseUris.tcgplayer
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        val manaColors = card.colors

        if (manaColors.contains("R")) binding.ivRedMana.visibility = View.VISIBLE
        if (manaColors.contains("G")) binding.ivGreenMana.visibility = View.VISIBLE
        if (manaColors.contains("B")) binding.ivBlackMana.visibility = View.VISIBLE
        if (manaColors.contains("W")) binding.ivWhiteMana.visibility = View.VISIBLE
        if (manaColors.contains("U")) binding.ivBlueMana.visibility = View.VISIBLE

        binding.btnOpenDialogCardPreview.setOnClickListener {
            CardPreviewDialog(card.imageUris.normal).show(parentFragmentManager, "")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}