package com.example.myapplication2

import androidx.recyclerview.widget.DiffUtil
class MyDiffUtilCallback(
    private val oldList: List<MyItem>,
    private val newList: List<MyItem>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        return oldList[oldPos].name == newList[newPos].name   // or an ID property
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        return oldList[oldPos] == newList[newPos]
    }
}