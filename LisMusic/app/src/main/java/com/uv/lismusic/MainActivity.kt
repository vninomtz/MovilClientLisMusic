package com.uv.lismusic

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.marginLeft
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.main_listview)
        listView.adapter = MyCustomAdapter(this)
    }

    private class MyCustomAdapter(context: Context): BaseAdapter(){

        private val mContext: Context

        private val namesTracks = arrayListOf<String>(
            "Soliá","Diles","Everyday","Good News","Heartless"
        )
        private val namesArtist = arrayListOf<String>(
            "Bad Bunny", "Rels B", "Logic", "Mac Miller", "The Weeknd"
        )
        init {
            mContext = context
        }
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.row_main_track, viewGroup, false)
            val nameTrack = rowMain.findViewById<TextView>(R.id.track_name)
            val nameArtist = rowMain.findViewById<TextView>(R.id.artist_name)
            nameTrack.text = namesTracks.get(position)
            nameArtist.text = namesArtist.get(position)
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
