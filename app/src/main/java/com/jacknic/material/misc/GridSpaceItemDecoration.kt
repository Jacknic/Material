package com.jacknic.material.misc

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 组件控件间隔修饰
 *
 * @author Jacknic
 */
class GridSpaceItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val gm = parent.layoutManager as GridLayoutManager
        val spanCount = gm.spanCount
        val spanGroupIndex = gm.spanSizeLookup.getSpanGroupIndex(position, spanCount)
        val lastGroupIndex = gm.spanSizeLookup.getSpanGroupIndex(gm.itemCount - 1, spanCount)
        val spanIndex = gm.spanSizeLookup.getSpanIndex(position, spanCount)
        val spanSize = gm.spanSizeLookup.getSpanSize(position)
        val isGroupStart = spanIndex == 0
        val isGroupEnd = (spanIndex + spanSize) % spanCount == 0
        val halfSpaceSize = spaceSize / 2
        val isFirstRow = spanGroupIndex == 0
        val isLastRow = spanGroupIndex == lastGroupIndex
        val left = if (isGroupStart) spaceSize else halfSpaceSize
        val top = if (isFirstRow) spaceSize else halfSpaceSize
        val right = if (isGroupEnd) spaceSize else halfSpaceSize
        val bottom = if (isLastRow) spaceSize else halfSpaceSize
        outRect.set(left, top, right, bottom)
    }
}