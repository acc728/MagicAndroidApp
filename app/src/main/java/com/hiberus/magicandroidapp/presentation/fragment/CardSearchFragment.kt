package com.hiberus.magicandroidapp.presentation.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.hiberus.magicandroidapp.R
import com.hiberus.magicandroidapp.databinding.FragmentCardSearchBinding
import com.hiberus.magicandroidapp.model.Card
import com.hiberus.magicandroidapp.model.ResourceState
import com.hiberus.magicandroidapp.presentation.viewmodel.AddCardState
import com.hiberus.magicandroidapp.presentation.viewmodel.CardAutocompleteState
import com.hiberus.magicandroidapp.presentation.viewmodel.CardsViewModel
import com.hiberus.magicandroidapp.presentation.viewmodel.RandomCardState
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.lang.NullPointerException

class CardSearchFragment : Fragment() {

    private var _binding: FragmentCardSearchBinding? = null
    private val binding get() = _binding!!

    private val cardsViewModel: CardsViewModel by activityViewModel()

    private var card: Card? = null
    private var autocompleteCards: MutableList<String>? = null

    private lateinit var autocompleteCardsAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initUI()
        cardsViewModel.fetchRandomCard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewModel() {
        cardsViewModel.randomCardLiveData.observe(viewLifecycleOwner) { state ->
            handleLoadCardState(state)
        }

        cardsViewModel.cardAutocompleteLiveData.observe(viewLifecycleOwner) { state ->
            handleAutocompleteCardState(state)
        }

        cardsViewModel.getCardByNameLiveData.observe(viewLifecycleOwner) { state ->
            handleLoadCardState(state)
        }

        cardsViewModel.addCardLiveData.observe(viewLifecycleOwner) { state ->
            handleAddCardState(state)
        }
    }


    private fun updateUI(card: Card?) {
        val uiHandler = Handler(Looper.getMainLooper())

        val uiTask = Runnable {
            if (card != null) {
                try {
                    Glide
                        .with(requireContext())
                        .load(card.imageUris.normal)
                        .into(binding.ivCardImage)
                } catch (e: NullPointerException) {
                    binding.ivCardImage.setImageResource(R.drawable.img_magic_cardback)
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.msg_error_card_random),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                binding.ivCardImage.visibility = View.VISIBLE
                binding.lavCardSearch.visibility = View.GONE

            } else {
                binding.ivCardImage.setImageResource(R.drawable.img_magic_cardback)
            }
        }

        uiHandler.removeCallbacks(uiTask)
        uiHandler.postDelayed(uiTask, 200)
    }

    private fun initUI() {
        binding.btnRandomCard.setOnClickListener {
            cardsViewModel.fetchRandomCard()
        }

        binding.atvSearchCard.addTextChangedListener {
            val text = binding.atvSearchCard.text.toString()

            if (text.count() >= 3) {
                cardsViewModel.fetchAutocompleteCard(text)

                autocompleteCardsAdapter = ArrayAdapter<String>(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    autocompleteCards ?: emptyList()
                )

                binding.atvSearchCard.setAdapter(autocompleteCardsAdapter)
            }
        }

        binding.atvSearchCard.setOnItemClickListener { _, _, position, _ ->
            val cardName = autocompleteCards?.get(position)
            autocompleteCards?.clear()
            autocompleteCards?.add(cardName ?: "")

            if (cardName != null) {
                cardsViewModel.fetchSearchCard(cardName)
            }

            hideKeyboard()
        }

        binding.btnAddCollection.setOnClickListener {
            saveCard()
        }
    }

    private fun handleLoadCardState(state: RandomCardState?) {
        when (state) {
            is ResourceState.Loading -> {
                binding.ivCardImage.visibility = View.GONE
                binding.lavCardSearch.visibility = View.VISIBLE
            }

            is ResourceState.Success -> {
                card = state.result
                Log.i("CardState", state.result.toString())
                updateUI(card)
            }

            is ResourceState.Error -> {
                binding.ivCardImage.visibility = View.VISIBLE
                binding.lavCardSearch.visibility = View.GONE

                Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }


    private fun handleAutocompleteCardState(state: CardAutocompleteState?) {
        when (state) {
            is ResourceState.Loading -> {
                //
            }

            is ResourceState.Success -> {
                autocompleteCards = state.result.toMutableList()
            }

            is ResourceState.Error -> {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    private fun handleAddCardState(state: AddCardState?) {
        when (state) {
            is ResourceState.Loading -> {
                //
            }

            is ResourceState.Success -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_card_added_to_colecction),
                    Toast.LENGTH_SHORT
                ).show()
                cardsViewModel.fetchCardList()
            }

            is ResourceState.Error -> {
                Log.i("Error", state.error)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_error_add_card_to_collection), Toast.LENGTH_SHORT
                ).show()
            }

            else -> {}
        }
    }

    private fun saveCard() {
        if (card != null) {
            card?.comments = ""
            cardsViewModel.addCard(card!!)
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.msg_error_add_card_to_collection), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

}