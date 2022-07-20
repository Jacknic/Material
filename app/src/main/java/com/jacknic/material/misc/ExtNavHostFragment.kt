package com.jacknic.material.misc

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.use
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.appcompat.R as Rx

/**
 * NavHostFragment 拓展类
 *
 * 用于解析自定义属性
 *
 * @author Jacknic
 */
class ExtNavHostFragment : NavHostFragment() {
    override fun onCreateNavHostController(navHostController: NavHostController) {
        super.onCreateNavHostController(navHostController)
        navHostController.navigatorProvider.addNavigator(
            ExtFragmentNavigator(requireContext(), childFragmentManager, id)
        )
    }

    @Navigator.Name("fragment")
    private class ExtFragmentNavigator(
        context: Context,
        fragmentManager: FragmentManager,
        containerId: Int
    ) : FragmentNavigator(context, fragmentManager, containerId) {
        override fun createDestination(): ExtNavDestination {
            return ExtNavDestination(this)
        }
    }

    @NavDestination.ClassType(Fragment::class)
    class ExtNavDestination(fragmentNavigator: Navigator<out FragmentNavigator.Destination>) :
        FragmentNavigator.Destination(fragmentNavigator) {

        var imageRes = 0
            private set

        override fun onInflate(context: Context, attrs: AttributeSet) {
            super.onInflate(context, attrs)

            context.resources.obtainAttributes(attrs, Rx.styleable.AppCompatImageView).use { ta ->
                imageRes = ta.getResourceId(Rx.styleable.AppCompatImageView_android_src, 0)
            }
        }
    }
}