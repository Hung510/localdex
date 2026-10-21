package com.localdex

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DisplayConnectReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        // Khi phát hiện cắm cáp HDMI hoặc thiết bị USB tương thích xuất hình
        if (action == "android.intent.action.HDMI_PLUGGED" || action == "android.hardware.usb.action.USB_DEVICE_ATTACHED") {
            // Kiểm tra xem cáp thực sự đã cắm vào (đối với HDMI)
            val plugged = intent.getBooleanExtra("state", true)
            if (plugged) {
                // Tự động khởi chạy service DeX ngầm hoặc mở thẳng MainActivity để kích hoạt
                val serviceIntent = Intent(context, MainActivity::class.java).apply {
                    action = "ACTION_START_DEX"
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                context.startActivity(serviceIntent)
            }
        }
    }
}
