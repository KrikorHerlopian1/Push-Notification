package edu.newhaven.krikorherlopian.android.pushnotifications

import com.google.gson.annotations.SerializedName


class RequestNotificaton {

    @SerializedName("token") //  "to" changed to token if you want to send to specific user you have to work with tokens.
    var token: String? = null

    @SerializedName("notification")
    var sendNotificationModel: SendNotificationModel? = null
}