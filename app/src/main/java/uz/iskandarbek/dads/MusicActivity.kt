package uz.iskandarbek.dads

import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import uz.iskandarbek.dads.databinding.ActivityMainBinding
import uz.iskandarbek.dads.databinding.ActivityMusicBinding
import uz.iskandarbek.dads.models.User
import uz.iskandarbek.dads.utils.MyMusic
import java.lang.Thread.sleep

class MusicActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMusicBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val musicData = intent.getSerializableExtra("keyMusic") as User
        binding.musicName.text = musicData.name
        binding.authorsName.text = musicData.authors

        binding.apply {

           val anim = disk.animate().rotationBy(360f).setDuration(50000).start()


            pause.setOnClickListener {
                if (MyMusic.mediaPLayer?.isPlaying == true) {
                   MyMusic.mediaPLayer?.pause()
                    pause.visibility = View.INVISIBLE
                    play.visibility = View.VISIBLE
                }
            }

            play.setOnClickListener {
                if (MyMusic.mediaPLayer?.isPlaying == false) {
                    MyMusic.mediaPLayer?.start()
                    pause.visibility = View.VISIBLE
                    play.visibility = View.INVISIBLE
                }
            }
        }
    }
}