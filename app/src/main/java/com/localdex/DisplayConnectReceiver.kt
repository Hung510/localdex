package com.localdex

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DisplayConnectReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action == "android.intent.action.HDMI_PLUGGED" || action == "android.hardware.usb.action.USB_DEVICE_ATTACHED") {
            val plugged = intent.getBooleanExtra("state", true)
            if (plugged) {
                // Khởi chạy MainActivity kèm action kích hoạt
                val serviceIntent = Intent(context, MainActivity::class.java).apply {
                    this.action = "ACTION_START_DEX"
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                context.startActivity(serviceIntent)
            }
        }
    }
}
