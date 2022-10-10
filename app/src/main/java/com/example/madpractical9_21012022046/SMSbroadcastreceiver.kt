package com.example.madpractical9_21012022046

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony

class SMSbroadcastreceiver : BroadcastReceiver() {
interface Listner {
    fun onTextReceived(sPhoneNo: String?, sMsg: String?)

}var listner:Listner?=null
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        if (intent.action==Telephony.Sms.Intents.SMS_RECEIVED_ACTION){
            var sphoneNo = ""
            var sSMSBody = ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for(smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                  sphoneNo=smsMessage.displayOriginatingAddress
                  sSMSBody += smsMessage.messageBody
                }


                if (listner != null) {
                    listner?.onTextReceived(sphoneNo,sSMSBody)
                }
            }
        }
    }

}