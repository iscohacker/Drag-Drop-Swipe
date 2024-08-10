package uz.iskandarbek.dads

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import uz.iskandarbek.dads.adapters.RvAdapters
import uz.iskandarbek.dads.databinding.ActivityMainBinding
import uz.iskandarbek.dads.models.User
import uz.iskandarbek.dads.utils.MyMusic

class MainActivity : AppCompatActivity(), RvAdapters.RvAction {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var list: ArrayList<User>
    lateinit var rvAdapters: RvAdapters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadData()
        rvAdapters = RvAdapters(list, this)
        binding.rv.adapter = rvAdapters


        val itemTouchHelper = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                rvAdapters.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                rvAdapters.onItemDismiss(viewHolder.adapterPosition)
            }
        }
        val itemTouch = ItemTouchHelper(itemTouchHelper)
        itemTouch.attachToRecyclerView(binding.rv)
    }

    private fun loadData() {
        list = ArrayList()
        list.add(User("Jaloliddin Ahmadaliyev", "19 yil", R.raw.yil))
        list.add(User("Jaloliddin Ahmadaliyev", "Indamaygina", R.raw.indamaygina))
        list.add(User("Jaloliddin Ahmadaliyev", "Yetmasmidi", R.raw.yetmasmidi))
        list.add(User("Jaloliddin Ahmadaliyev", "Chiroylisan", R.raw.chiroylisan))
        list.add(User("Jaloliddin Ahmadaliyev", "Qo'qonlik", R.raw.quqonlik))
        list.add(User("Jaloliddin Ahmadaliyev", "Bevafo dil", R.raw.bevafodil))
        list.add(User("Jaloliddin Ahmadaliyev", "Xavotirman", R.raw.xavotirman))
        list.add(User("Jaloliddin Ahmadaliyev", "So'gindim", R.raw.sogindim))
        list.add(User("Jaloliddin Ahmadaliyev", "Setoram", R.raw.setoram))
        list.add(User("Jaloliddin Ahmadaliyev", "Yurak", R.raw.yurak))
        list.add(User("Jaloliddin Ahmadaliyev", "Bilmaysan yor", R.raw.bilmayssan))
        list.add(User("Jaloliddin Ahmadaliyev", "Men edim", R.raw.menedim))
        list.add(User("Jaloliddin Ahmadaliyev", "Dadamni soyasida", R.raw.soyasida))
    }

    override fun itemClick(user: User) {
        val intent = Intent(this, MusicActivity::class.java)
        intent.putExtra("keyMusic", user)
        if (MyMusic.mediaPLayer == null) {
            MyMusic.mediaPLayer = MediaPlayer.create(this, user.music)
            MyMusic.musicData = user.music
            MyMusic.mediaPLayer?.start()
            startActivity(intent)
        } else if (MyMusic.musicData != user.music && MyMusic.mediaPLayer?.isPlaying == true) {
            MyMusic.mediaPLayer?.stop()
            MyMusic.mediaPLayer = MediaPlayer.create(this, user.music)
            MyMusic.musicData = user.music
            MyMusic.mediaPLayer?.start()
            startActivity(intent)
        } else if (MyMusic.musicData != user.music && MyMusic.mediaPLayer?.isPlaying == false) {
            MyMusic.mediaPLayer = MediaPlayer.create(this, user.music)
            MyMusic.musicData = user.music
            MyMusic.mediaPLayer?.start()
            startActivity(intent)
        } else if (MyMusic.musicData == user.music && MyMusic.mediaPLayer?.isPlaying == true) {
            startActivity(intent)
        } else if (MyMusic.musicData == user.music && MyMusic.mediaPLayer?.isPlaying == false) {
            MyMusic.mediaPLayer?.start()
            startActivity(intent)
        }

    }
}
