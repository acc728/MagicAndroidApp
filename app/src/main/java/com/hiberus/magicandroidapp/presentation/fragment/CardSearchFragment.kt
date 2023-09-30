package com.hiberus.magicandroidapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hiberus.magicandroidapp.R
import com.hiberus.magicandroidapp.databinding.FragmentCardSearchBinding
import com.hiberus.magicandroidapp.model.Card
import com.hiberus.magicandroidapp.model.ResourceState
import com.hiberus.magicandroidapp.presentation.viewmodel.CardsViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CardSearchFragment : Fragment() {

    private var _binding: FragmentCardSearchBinding? = null
    private val binding get() = _binding!!

    private val notesViewModel: CardsViewModel by activityViewModel()

    private var card: Card? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        notesViewModel.randomCardLiveData.observe(viewLifecycleOwner) { state ->
            handleRandomCardState(state)
        }

        notesViewModel.cardAutocompleteLiveData.observe(viewLifecycleOwner) { state ->
            handleAutocompleteCardState(state)
        }

        notesViewModel.fetchRandomCard()
    }

    private fun handleRandomCardState(state: ResourceState<Card>?) {
        when(state) {
            is ResourceState.Loading -> {
                //
            }
            is ResourceState.Success -> {
                card = state.result
                Log.i("CardState", state.result.toString())
                initUI(card)
            }
            is ResourceState.Error -> {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    private fun initUI(card: Card?) {
        if(card != null) {
            Glide
                .with(binding.ivCardImage)
                .load(card.imageUris.normal)
                .into(binding.ivCardImage)
        }else{
            binding.ivCardImage.setImageResource(R.drawable.img_magic_cardback)
        }

    }

    private fun handleAutocompleteCardState(state: ResourceState<List<String>>?) {

    }


}