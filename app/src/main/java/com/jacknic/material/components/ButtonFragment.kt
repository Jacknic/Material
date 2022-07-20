package com.jacknic.material.components

import android.os.Bundle
import android.view.View
import com.jacknic.material.databinding.FragButtonBinding
import com.jacknic.material.misc.BindingFragment

/**
 * 按钮组件样式
 *
 * @author Jacknic
 */
class ButtonFragment : BindingFragment<FragButtonBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.btnSelected.isSelected = true
        bind.btnSelected2.isSelected = true
        bind.btnSelected2.isEnabled = false
        bind.btnNormal.isEnabled = false
    }
}