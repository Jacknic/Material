package com.jacknic.material.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.collection.valueIterator
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jacknic.material.R
import com.jacknic.material.databinding.FragCatalogBinding
import com.jacknic.material.databinding.ItemCatalogBinding
import com.jacknic.material.misc.BindingFragment
import com.jacknic.material.misc.BindingHolder
import com.jacknic.material.misc.ExtNavHostFragment
import com.jacknic.material.misc.GridSpaceItemDecoration


/**
 * 控件目录页面
 *
 * @author Jacknic
 */
class CatalogFragment : BindingFragment<FragCatalogBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.navHostFrag)
        val catalogListAdapter = CatalogListAdapter(navController)
        bind.rvCatalogList.adapter = catalogListAdapter
        val spaceSize = resources.getDimension(R.dimen.list_space_size).toInt()
        val itemDecoration = GridSpaceItemDecoration(spaceSize)
        bind.rvCatalogList.addItemDecoration(itemDecoration)
        val pageNodes = navController.graph.nodes.valueIterator()
        val pageList = mutableListOf<NavDestination>()
        pageNodes.forEach {
            if (it.id != R.id.catalogFragment) {
                pageList.add(it)
            }
        }
        catalogListAdapter.submitList(pageList)
    }

    private class CatalogListAdapter(private val navController: NavController) :
        ListAdapter<NavDestination, BindingHolder<ItemCatalogBinding>>(diffCallback) {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BindingHolder<ItemCatalogBinding> {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCatalogBinding.inflate(inflater, parent, false)
            return BindingHolder(binding)
        }

        private val navOptions = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        override fun onBindViewHolder(holder: BindingHolder<ItemCatalogBinding>, position: Int) {
            val item = getItem(position)
            holder.bind.tvName.text = item.label
            if (item is ExtNavHostFragment.ExtNavDestination) {
                holder.bind.ivImage.setImageResource(item.imageRes)
            }
            holder.bind.root.setOnClickListener {
                navController.navigate(item.id, null, navOptions)
            }
        }

        companion object {
            private val diffCallback = object : DiffUtil.ItemCallback<NavDestination>() {
                override fun areItemsTheSame(old: NavDestination, new: NavDestination) =
                    old === new

                override fun areContentsTheSame(old: NavDestination, new: NavDestination) =
                    old.id == new.id
            }

        }
    }
}

