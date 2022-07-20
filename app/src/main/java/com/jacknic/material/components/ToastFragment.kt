package com.jacknic.material.components

import android.os.Bundle
import android.view.View
import com.jacknic.material.databinding.FragToastBinding
import com.jacknic.material.misc.BindingFragment

/**
 * Toast 组件样式
 *
 * @author Jacknic
 */
class ToastFragment : BindingFragment<FragToastBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.btnCenter.setOnClickListener {

        }
        bind.btnTop.setOnClickListener {

        }
        bind.btnBottom.setOnClickListener {

        }
    }
}