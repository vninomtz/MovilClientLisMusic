package com.uv.lismusic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.main_listview)

        val namesTracks = arrayListOf<String>(
            "Soliá","Diles","Everyday","Good News","Heartless"
        )
        val namesArtist = arrayListOf<String>(
            "Bad Bunny", "Rels B", "Logic", "Mac Miller", "The Weeknd"
        )
        listView.adapter = MyCustomAdapter(this, namesTracks, namesArtist)
        listView.setOnItemClickListener { parent, view, position, id ->
            goToPlayer(namesTracks[position], namesArtist[position])
        }
        
    }

    fun goToPlayer(track: String, artist:String): Unit {
        val intent = Intent(this, player::class.java).apply {
            putExtra("nameTrack",  track)
            putExtra("nameArtist", artist)
        }

        this.startActivity(intent)
    }

    private class MyCustomAdapter(context: Context, tracks:ArrayList<String>, artist:ArrayList<String>): BaseAdapter(){

        private val mContext: Context
        private val namesTracks: ArrayList<String>
        private val namesArtist: ArrayList<String>

        init {
            mContext = context
            namesTracks = tracks
            namesArtist = artist
        }
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.row_main_track, viewGroup, false)
            val nameTrack = rowMain.findViewById<TextView>(R.id.track_name)
            val nameArtist = rowMain.findViewById<TextView>(R.id.artist_name)
            nameTrack.text = namesTracks.get(position)
            nameArtist.text = namesArtist.get(position)
            val button = Button(mContext)
            button.setText("Más")

            return rowMain
        }

        override fun getItem(position: Int): Any {
            return "Test string"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return namesTracks.size
        }

    }

}
