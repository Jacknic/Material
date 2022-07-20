package com.jacknic.material.misc

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * 数据绑定类
 *
 * @author Jacknic
 */
data class BindingHolder<T : ViewBinding>(val bind: T) : RecyclerView.ViewHolder(bind.root)
