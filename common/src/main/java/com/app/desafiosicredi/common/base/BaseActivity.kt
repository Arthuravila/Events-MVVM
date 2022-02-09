package com.app.desafiosicredi.common.base

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.app.desafiosicredi.common.utils.extensions.activityBinding
import com.app.desafiosicredi.common.utils.extensions.animateTransitionOnRebind
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes val resId: Int
) : AppCompatActivity(), CoroutineScope {

    private val activityExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d(
                ">>>CoroutineExcpHndlr",
                "coroutineContext: $coroutineContext throwable: ${throwable.printStackTrace()}"
            )
        }

    var dialog: Dialog? = null
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + activityExceptionHandler

    val binding by activityBinding<T>(resId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.animateTransitionOnRebind<ViewDataBinding>()

        initView()

        subscribeUi()
    }

    open fun initView() {}

    open fun subscribeUi() {}
}
