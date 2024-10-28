package birthdaygreetings.birthdayframe.greetingswishes.invitationCard.viewModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : InvitationApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://postermakershadab.s3.amazonaws.com/")
            //.baseUrl("https://www.mediafire.com/file_premium/1rc4f2vcc0qf347/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InvitationApi::class.java)
    }

}