package com.jacknic.material.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.TooltipCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.jacknic.material.R
import com.jacknic.material.databinding.FragTabsBinding
import com.jacknic.material.misc.BindingFragment

/**
 * Tabs 组件样式
 *
 * @author Jacknic
 */
class TabsFragment : BindingFragment<FragTabsBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPager()
    }

    /**
     * 关联页面状态
     */
    private fun setupPager() {
        val tabTitles = resources.getStringArray(R.array.media_tab_titles)
        bind.homePager.getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
        bind.homePager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return tabTitles.size
            }

            override fun createFragment(position: Int): Fragment {
                return SubPageFragment.create(position)
            }
        }
        TabLayoutMediator(bind.homeTabs, bind.homePager, true, false) { tab, position ->
            tab.text = tabTitles[position]
            TooltipCompat.setTooltipText(tab.view, null)
        }.attach()
    }

    class SubPageFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val position = arguments?.getInt(KEY)
            val tv = TextView(requireContext())
            tv.text = position.toString()
            return tv
        }

        companion object {
            private const val KEY = "position"
            fun create(position: Int): SubPageFragment {
                val bundle = bundleOf(KEY to position)
                return SubPageFragment().apply {
                    arguments = bundle
                }
            }
        }
    }
}