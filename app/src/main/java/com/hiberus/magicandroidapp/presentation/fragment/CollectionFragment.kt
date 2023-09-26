package com.hiberus.magicandroidapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiberus.magicandroidapp.databinding.FragmentCollectionBinding
import com.hiberus.magicandroidapp.presentation.adapter.CardListAdapter

class CollectionFragment : Fragment() {

    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!

    private val cardListAdapter = CardListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        binding.rvCardsCollection.adapter = cardListAdapter
        binding.rvCardsCollection.layoutManager = LinearLayoutManager(requireContext())

        cardListAdapter.onClickListener = { card ->
            findNavController().navigate(
                CollectionFragmentDirections.actionCollectionFragmentToCardDetailFragment(
                    card.id.toInt()
                )
            )
        }
    }
}