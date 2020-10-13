package com.app.desafiosicredi.core.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.app.desafiosicredi.R
import com.app.desafiosicredi.core.utils.extensions.verifyEmail
import com.app.desafiosicredi.core.utils.extensions.verifyFullName
import com.app.desafiosicredi.databinding.DialogCheckinBinding

class CustomCheckinDialog(context: Context) : AlertDialog(context) {
    fun showDialog(
        itemClicked: (String, String) -> Unit
    ) {
        val builder = Builder(context)
        val binding = DataBindingUtil.inflate<DialogCheckinBinding>(
            layoutInflater,
            R.layout.dialog_checkin,
            null,
            false
        )
        builder.setView(binding.root)
        val alertDialog: AlertDialog = builder.create()
        binding.btOk.setOnClickListener {
            if (validateFields(binding)) {
                itemClicked(
                    binding.etName.text.toString(),
                    binding.etEmailAddress.text.toString()
                )
                alertDialog.dismiss()
            }
        }
        binding.btCancel.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    private fun validateFields(binding: DialogCheckinBinding): Boolean {
        binding.textInputLayoutName.error = null
        binding.textInputLayoutEmail.error = null
        var isDataValid = true
        if (!isNameValid(binding.etName.text.toString())) {
            isDataValid = false
            binding.textInputLayoutName.error =
                context.getString(R.string.invalid_name)
        }
        if (!isEmailValid(binding.etEmailAddress.text.toString())) {
            isDataValid = false
            binding.textInputLayoutEmail.error =
                context.getString(R.string.invalid_email)
        }
        return isDataValid
    }

    private fun isEmailValid(emailAddress: String?): Boolean {
        return if (emailAddress.isNullOrBlank()) false
        else emailAddress.verifyEmail()
    }

    private fun isNameValid(name: String?): Boolean {
        return if (name.isNullOrBlank()) false
        else name.verifyFullName()
    }
}
