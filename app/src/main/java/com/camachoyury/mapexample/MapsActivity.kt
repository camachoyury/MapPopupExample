package com.camachoyury.mapexample

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.Marker


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val names: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        var marker = mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.setOnMarkerClickListener {
            initiatePopupWindow(it, baseContext)

        }

    }

    lateinit var pw: PopupWindow

    @SuppressLint("WrongViewCast")
    public fun initiatePopupWindow(marker: Marker, context:Context ): Boolean {

        try {

            //We need to get the instance of the LayoutInflater, use the context of this activity
            val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            //Inflate the view from a predefined XML layout
            val layout = inflater.inflate(
                R.layout.popup, this.findViewById<ViewGroup>(R.id.popuplayout)
            )
            names.add("Cocha 1")
            names.add("Cocha 2")
            names.add("Cocha 3")


            val markerListAdapter = MarkerListAdapter(names, context)
            val recyclerView = layout.findViewById(R.id.infoWindowItemRecyclerView) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = markerListAdapter

           val height= getResources().getDimension(R.dimen.row_height);
            val width= getResources().getDimension(R.dimen.row_width);

            pw = PopupWindow(layout, width.toInt(),(height * names.size).toInt() , true)

            // display the popup in the center
            pw.showAtLocation(layout, Gravity.CENTER, 0, -400)


        } catch (e: Exception) {
            e.printStackTrace()
        }

        return true

    }

}

