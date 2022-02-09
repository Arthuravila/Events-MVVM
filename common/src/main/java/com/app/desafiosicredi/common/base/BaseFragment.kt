package com.app.desafiosicredi.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.app.desafiosicredi.common.utils.helpers.autoCleared

abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val resId: Int) : Fragment() {

    var binding by autoCleared<T>()

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            resId,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        subscribeUi()
        return binding.root
    }

    open fun subscribeUi() {}
}
