package com.example.sevayu.ui.Main.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.sevayu.R
import com.example.sevayu.models.Appointments
import com.example.sevayu.models.Blog
import com.example.sevayu.ui.Main.adapters.AppointmentAdapter
import com.example.sevayu.ui.Main.adapters.BlogAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*


class HomeFragment : Fragment() {
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var lastLocation: Location? = null


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        setupDeptRV(view)
        setupBlogRv(view)

        isLocationEnabled(requireContext())

        return view
    }




    private fun setupDeptRV(view: View) {
        view.findViewById<RecyclerView>(R.id.rv_home)?.let { rv ->

            var colors = arrayOf("#F06292","#81C784","#4DD0E1","#FFB74D")


            var dummyList= listOf(Appointments("AIIMS","Cancer","3:30-6:30","14 March,2023")
                ,Appointments("PMCH","Gyno","12:30-6:30","16 March,2023")
                ,Appointments("Apollo","Ortho","4:00-7:30","11 March,2023")
                ,Appointments("Max","Pedia","2:00-7:30","1 March,2023"))


            rv.layoutManager=LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            rv.adapter= AppointmentAdapter(dummyList,colors)
        }
    }

    private fun setupBlogRv(view: View){

        view.findViewById<RecyclerView>(R.id.rv_blogHome)?.let { rv ->
            var dummyList = listOf(
                Blog(
                    "Dr. Praneki",
                    "Why Non veg food very good",
                    "20 March 2023",
                    "coz it tastes good lmao and veg food is kinda mid"
                ),
                Blog(
                    "Dr Yogi",
                    "Yoga cures cancer",
                    "2 March 2023",
                    "Waking up every morning at 6:00AM, Suryanamaskar and a fresh cup of gomutra can cure cancer"
                ),
                Blog(
                    "WHO",
                    "Medical discovery in Africa",
                    "26 Nov 2008",
                    "Scientists discover the main cause of lower health index in central african countries and to everyone's surprise it's poverty."
                ),
                Blog(
                    "Dr Maro",
                    "Premature ejaculation can change your life",
                    "6 September 2023",
                    "will let me satisfy your soul mate"
                ),
                Blog(
                    "Dr. Praneki",
                    "Why Non veg food very good",
                    "20 March 2023",
                    "coz it tastes good lmao and veg food is kinda mid"
                ),
                Blog(
                    "Dr Yogi",
                    "Yoga cures cancer",
                    "2 March 2023",
                    "Waking up every morning at 6:00AM, Suryanamaskar and a fresh cup of gomutra can cure cancer"
                )
            )
            rv.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            rv.adapter= BlogAdapter(dummyList)


        }
    }

    private fun getAddress(lat: Double, lng: Double): String {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val addresses:List<Address> = geocoder.getFromLocation(lat, lng, 1)

        if (addresses != null && addresses.size > 0) {
            val address: String = addresses.get(0)
                .getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            val city: String = addresses.get(0).getLocality()
            val state: String = addresses.get(0).getAdminArea()
            val country: String = addresses.get(0).getCountryName()
            val postalCode: String = addresses.get(0).getPostalCode()
            val knownName: String =
                addresses.get(0).getFeatureName() // Only if available else return NULL
            Log.d("add", "getAddress:  address$address")
            Log.d("add", "getAddress:  city$city")
            Log.d("add", "getAddress:  state$state")
            Log.d("add", "getAddress:  postalCode$postalCode")
            Log.d("add","getAddress:  knownName$knownName")

            view?.findViewById<TextView>(R.id.tv_location)?.text=city
        }


        return addresses[0].getAddressLine(0)
    }
    private fun checkGPS(){
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) !==
            PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            } else {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            }

        }else{
            getLastLocation()
        }

    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation(){

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        fusedLocationClient?.lastLocation!!.addOnCompleteListener(requireActivity()) { task ->
            if(task.isSuccessful){
                val address:String=getAddress(task.result.latitude,task.result.longitude)
                e("address",address)
            }else{
                e("addressE",task.result.toString())
            }

        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {

        when (requestCode) {
            1 -> {

                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(requireContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        getLastLocation()
                        Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.P)
    private fun isLocationEnabled(context: Context) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if(!locationManager.isLocationEnabled){
            AlertDialog.Builder(context).setTitle("GPS not enabled")
                .setPositiveButton("enable") { dialog, which ->
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startForResult.launch(intent)
                }.show()
        }else{
            checkGPS()
        }

    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            _: ActivityResult ->
//            getLastLocation()
    }



}