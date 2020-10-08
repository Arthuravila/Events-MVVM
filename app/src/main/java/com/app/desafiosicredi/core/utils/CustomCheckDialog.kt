package com.app.desafiosicredi.core.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.app.desafiosicredi.R
import com.app.desafiosicredi.databinding.DialogCheckBinding

class CustomCheckDialog(context: Context) : AlertDialog(context) {
    fun showDialog(
        itemClicked: (String, String) -> Unit
    ) {
        val builder = Builder(context)
        val binding = DataBindingUtil.inflate<DialogCheckBinding>(
            layoutInflater,
            R.layout.dialog_check,
            null,
            false
        )
        builder.setView(binding.root)
        val alertDialog: AlertDialog = builder.create()
        binding.btOk.setOnClickListener {
            itemClicked(binding.etName.text.toString(), binding.etEmailAddress.text.toString())
            alertDialog.cancel()
        }

        binding.btCancel.setOnClickListener {
            alertDialog.cancel()
        }
        alertDialog.show()
    }
}