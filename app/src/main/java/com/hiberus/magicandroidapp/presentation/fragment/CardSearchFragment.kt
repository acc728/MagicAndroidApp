package com.hiberus.magicandroidapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hiberus.magicandroidapp.databinding.FragmentCardSearchBinding
import com.hiberus.magicandroidapp.presentation.viewmodel.CardsViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CardSearchFragment : Fragment() {

    private var _binding: FragmentCardSearchBinding? = null
    private val binding get() = _binding!!

    private val notesViewModel: CardsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardSearchBinding.inflate(inflater)
        return binding.root
    }
}