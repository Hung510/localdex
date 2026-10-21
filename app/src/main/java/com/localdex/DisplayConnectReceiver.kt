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
                // Tạo Intent đánh thức MainActivity và ép buộc nhảy lên foreground ngay lập tức
                val activityIntent = Intent(context, MainActivity::class.java).apply {
                    this.action = "ACTION_START_DEX"
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or 
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or 
                            Intent.FLAG_ACTIVITY_SINGLE_TOP or
                            Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                }
                
                try {
                    context.startActivity(activityIntent)
                } catch (e: Exception) {
                    // Dự phòng trường hợp bị Android chặn, ta ép gọi qua phương thức Context thông thường
                    e.printStackTrace()
                }
            }
        }
    }
}
