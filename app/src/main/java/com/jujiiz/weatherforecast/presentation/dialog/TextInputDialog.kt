package com.jujiiz.weatherforecast.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import com.jujiiz.weatherforecast.databinding.DialogInputTextBinding
import kotlinx.android.extensions.LayoutContainer

class TextInputDialog private constructor(
    context: Context,
    onSubmit: (String) -> Unit
) : LayoutContainer {
    private val binding: DialogInputTextBinding by lazy {
        DialogInputTextBinding.inflate(LayoutInflater.from(context))
    }

    override val containerView: View
        get() = binding.root

    companion object {
        fun getInstance(
            context: Context,
            onSubmit: (String) -> Unit = {}
        ): TextInputDialog = TextInputDialog(
            context = context,
            onSubmit = onSubmit
        )
    }

    private val dialog = Dialog(context).apply {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(true)
        setContentView(binding.root)
    }

    init {
        binding.apply {
            btnConfirmationSubmit.setOnClickListener {
                val text = edtInput.text.toString()
                if (text.isNotBlank()) {
                    onSubmit(text)
                    hide()
                }
            }
        }
    }


    fun show() {
        if (!dialog.isShowing) dialog.show()
    }

    private fun hide() {
        dialog.dismiss()
    }

}