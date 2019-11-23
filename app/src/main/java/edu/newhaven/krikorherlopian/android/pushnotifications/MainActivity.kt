package edu.newhaven.krikorherlopian.android.pushnotifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://fcm.googleapis.com/"
    private var retrofit: Retrofit? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeToPushNotifications()

        subscribe.setOnClickListener {
            subscribeToPushNotifications()
        }
        unsubscribe.setOnClickListener{
            unsubscribeToPushNotifications()
        }

        send_notification.setOnClickListener{
            sendNotification()
        }
    }

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }


    //this will send notification, when send notification button clicked.
    // user will recieve notification if subscribed to news topic, or wont recieve if he is unsusbcribed or not subscribed at all.
    fun sendNotification() {
        val sendNotificationModel = SendNotificationModel(
            "Body",
            "Title")
        val requestNotificaton = RequestNotificaton()
        requestNotificaton.sendNotificationModel = sendNotificationModel
        var title = "Title"
        var subTitle = "SubTitle"

        var postJsonData = "{\n" +
                " \"to\" : \"/topics/news\",\n" +
                " \"collapse_key\" : \"type_a\",\n" +
                " \"notification\" : {\n" +
                "     \"body\" : \"" + subTitle + "\",\n" +
                "     \"title\": \"" + title + "\"\n" +
                " }\n" +
                "}"

        var apiService = getClient().create(ApiInterface::class.java)
        var body =
            RequestBody.create(MediaType.parse("application/json"), postJsonData)
        val responseBodyCall = apiService.sendChatNotification(body)
        responseBodyCall.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: retrofit2.Call<ResponseBody>,
                response: retrofit2.Response<ResponseBody>
            ) {
            }
            override fun onFailure(
                call: retrofit2.Call<ResponseBody>,
                t: Throwable
            ) {
            }
        })
    }

    //this enables push notification to news topic
    private fun subscribeToPushNotifications(){
        FirebaseMessaging.getInstance().subscribeToTopic("news")
            .addOnCompleteListener { task ->
            }
    }

    //this disables push notification to news topic.
    private fun unsubscribeToPushNotifications(){
        FirebaseMessaging.getInstance().unsubscribeFromTopic("news")
            .addOnCompleteListener { task ->
            }
    }
}
