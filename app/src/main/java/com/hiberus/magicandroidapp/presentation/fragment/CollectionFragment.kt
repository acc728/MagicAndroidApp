package com.hiberus.magicandroidapp.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hiberus.magicandroidapp.R
import com.hiberus.magicandroidapp.databinding.FragmentCollectionBinding
import com.hiberus.magicandroidapp.model.Card
import com.hiberus.magicandroidapp.model.ResourceState
import com.hiberus.magicandroidapp.presentation.adapter.CardListAdapter
import com.hiberus.magicandroidapp.presentation.viewmodel.CardsViewModel
import com.hiberus.magicandroidapp.presentation.viewmodel.DeleteCardState
import com.hiberus.magicandroidapp.presentation.viewmodel.GetCardListState
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CollectionFragment : Fragment() {

    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!

    private val cardListAdapter = CardListAdapter()

    private val cardsViewModel: CardsViewModel by activityViewModel()

    private lateinit var cardsFiltered: List<Card>
    private lateinit var listCards: ArrayList<Card>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecyclerView()
        initUI()
    }

    private fun initViewModel() {

        cardsViewModel.deleteCardLiveData.observe(viewLifecycleOwner) { state ->
            handleDeleteCardState(state)
        }

        cardsViewModel.getCardListLiveData.observe(viewLifecycleOwner) { state ->
            handleGetCardListState(state)
        }

        cardsViewModel.fetchCardList()
    }

    private fun initUI() {
        var filteredText = ""
        val textSearchHandler = Handler(Looper.getMainLooper())

        val textSearchTask = Runnable {
            if (filteredText.isNotBlank() || cardsFiltered.isEmpty()) {

                cardsFiltered = listCards.filter { card ->
                    card.name.lowercase().contains(filteredText.lowercase())
                }

                cardListAdapter.cardList = ArrayList(cardsFiltered)
            } else {
                cardsViewModel.fetchCardList()
            }

            cardListAdapter.notifyDataSetChanged()
        }

        binding.atvFilterCard.addTextChangedListener { text ->
            filteredText = text.toString()

            textSearchHandler.removeCallbacks(textSearchTask)
            textSearchHandler.postDelayed(textSearchTask, 500)
        }
    }

    private fun initRecyclerView() {
        binding.rvCardsCollection.adapter = cardListAdapter
        binding.rvCardsCollection.layoutManager = LinearLayoutManager(requireContext())

        cardListAdapter.onClickListener = { card ->
            findNavController().navigate(
                CollectionFragmentDirections.actionCollectionFragmentToCardDetailFragment(
                    card.id.toInt()
                )
            )
        }

        val itemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val card = cardListAdapter.cardList[position]

                    cardListAdapter.cardList.removeAt(position)
                    cardListAdapter.notifyItemRemoved(position)

                    cardsViewModel.deleteCard(card)
                }

            })

        itemTouchHelper.attachToRecyclerView(binding.rvCardsCollection)
    }

    private fun handleDeleteCardState(state: DeleteCardState?) {
        when (state) {
            is ResourceState.Loading -> {
                //
            }

            is ResourceState.Success -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_card_deleted_succesfully),
                    Toast.LENGTH_SHORT
                ).show()
            }

            is ResourceState.Error -> {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
            }

            else -> {}
        }
    }

    private fun handleGetCardListState(state: GetCardListState?) {
        when (state) {
            is ResourceState.Loading -> {
                //
            }

            is ResourceState.Success -> {
                cardListAdapter.submitList(state.result)
                listCards = cardListAdapter.cardList
            }

            is ResourceState.Error -> {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
            }

            else -> {}
        }
    }
}