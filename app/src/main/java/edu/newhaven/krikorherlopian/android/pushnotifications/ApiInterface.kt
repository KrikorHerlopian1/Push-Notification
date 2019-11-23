package edu.newhaven.krikorherlopian.android.pushnotifications

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    //AIzaSyALmJzvJKj9yK23lUj7-M4e8CmfnWENx2s this is legacy server key
    @Headers(
        "Authorization: key=AIzaSyALmJzvJKj9yK23lUj7-M4e8CmfnWENx2s\n",
        "Content-Type:application/json"
    )
    @POST("fcm/send")
    fun sendChatNotification(@Body requestBody: RequestBody): Call<ResponseBody>
}