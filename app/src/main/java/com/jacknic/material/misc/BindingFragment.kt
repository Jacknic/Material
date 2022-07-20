package com.jacknic.material.misc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * 泛型解析绑定Fragment基类
 *
 * @author Jacknic
 */
open class BindingFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    protected val bind: VB
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            val pt = javaClass.genericSuperclass as ParameterizedType
            val typeClass = pt.actualTypeArguments[0] as Class<*>
            val inflateMethod = typeClass.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.javaPrimitiveType
            )
            @Suppress("UNCHECKED_CAST")
            _binding = inflateMethod.invoke(null, inflater, container, false) as VB
            return bind.root
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}