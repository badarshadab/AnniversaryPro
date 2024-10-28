package birthdaygreetings.birthdayframe.greetingswishes.invitationCard.viewModel


import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.model.InvitationModel
import retrofit2.Call
import retrofit2.http.GET

interface InvitationApi {
//    @GET("Wedding.json/file")
    @GET("Invite/Bithday+invite.txt")
   fun getInvitation() : Call<InvitationModel>
}