package birthdaygreetings.birthdayframe.greetingswishes.invitationCard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.model.InvitationModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvitationViewModel : ViewModel() {

    var invitationList = MutableLiveData<InvitationModel>()

    fun getPopularInvitations() {
        viewModelScope.launch {
            if (invitationList.value == null) {
                RetrofitInstance.api.getInvitation().enqueue(object : Callback<InvitationModel> {
                    override fun onResponse(
                        call: Call<InvitationModel>,
                        response: Response<InvitationModel>
                    ) {
                        if (response.body() != null) {
                            invitationList.value = response.body()!!
                        }
                    }
                    override fun onFailure(call: Call<InvitationModel>, t: Throwable) {
                        println("err1${t.message}")
                    }
                })
            }
        }
    }
}