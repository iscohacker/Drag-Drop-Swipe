package uz.iskandarbek.dads.utils

interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition:Int, toPosition:Int)

    fun onItemDismiss(position:Int)
}