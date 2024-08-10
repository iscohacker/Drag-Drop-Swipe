package uz.iskandarbek.dads.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.iskandarbek.dads.databinding.ItemRvBinding
import uz.iskandarbek.dads.models.User
import uz.iskandarbek.dads.utils.ItemTouchHelperAdapter
import java.util.Collections

class RvAdapters(var list: ArrayList<User>,var rvAction: RvAction) : RecyclerView.Adapter<RvAdapters.Vh>(),
    ItemTouchHelperAdapter {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(user: User) {
            itemRv.name.text = user.name
            itemRv.authors.text = user.authors
            itemRv.root.setOnClickListener {
                rvAction.itemClick(user)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        //         Har bitta Item ni yasalishi

        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size
    //            Nechta item yasay


    override fun onBindViewHolder(holder: Vh, position: Int) {
//            Item qiymatlarini beruchi

        holder.onBind(list[position])
    }


    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition > toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i + 1)
            }
        } else {
            for (i in fromPosition until toPosition + 1) {
                Collections.swap(list, i, i)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
    interface RvAction{
        fun itemClick(user: User)

    }
}