package com.example.sevayu.WebServer

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.FragmentManager.TAG
import okhttp3.*

class WebSocketService: Service() {

    private lateinit var client: OkHttpClient
    private lateinit var webSocket: WebSocket


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        client = OkHttpClient()

        val request = Request.Builder()
            .url("wss://example.com/ws")
            .build()

        val listener = object : WebSocketListener(){

            override fun onOpen(webSocket: WebSocket, response: Response) {
                // WebSocket connection opened

                Log.d("service", "onOpen")
                subscribe()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                // WebSocket message received
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                // WebSocket connection closing
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                // WebSocket connection closed
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                // WebSocket connection failure
            }

        }
        webSocket =client.newWebSocket(request, listener)

        return START_STICKY
    }

    override fun onDestroy() {
        // Close the WebSocket connection when the service is destroyed
        webSocket.close(1000, null)
        super.onDestroy()
    }

    private fun subscribe() {
        webSocket.send(
            "{\n" +
                    "    \"type\": \"subscribe\",\n" +
                    "    \"channels\": [{ \"name\": \"ticker\", \"product_ids\": [\"BTC-EUR\"] }]\n" +
                    "}"
        )
    }


}