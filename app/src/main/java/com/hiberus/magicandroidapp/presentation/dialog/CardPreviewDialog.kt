package com.hiberus.magicandroidapp.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.hiberus.magicandroidapp.databinding.DialogCardPreviewBinding

class CardPreviewDialog(
    private val imageUri: String
): DialogFragment() {

    private lateinit var binding : DialogCardPreviewBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogCardPreviewBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        Glide
            .with(requireContext())
            .load(imageUri)
            .into(binding.ivCardPreviewDialog)

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(true)

        return dialog

    }

}