package com.example.sevayu.ui.Main.fragments

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.sevayu.R
import com.example.sevayu.ui.Main.MainActivity

class AppointmentFinalFragment : Fragment() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "com.example.sevayu.models.Test notification"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_appointment_final, container, false)

        notificationManager=context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        view.findViewById<Button>(R.id.btn_appointment).setOnClickListener {
            setNotification()
        }

        return view
    }

    private fun setNotification(){
        val intent = Intent(requireContext(), MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.WHITE
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(requireContext(), channelId)
                .setSmallIcon(R.drawable.sevayu_logo_asset)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.sevayu_logo_asset))
                .setContentIntent(pendingIntent)
                .setContentText("le")
        } else {
            builder = Notification.Builder(requireContext())
                .setSmallIcon(R.drawable.sevayu_logo_asset)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.sevayu_logo_asset))
                .setContentIntent(pendingIntent)
                .setContentText("le")
        }
        notificationManager.notify(1234, builder.build())


    }


}