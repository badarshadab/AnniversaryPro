package birthdaygreetings.birthdayframe.greetingswishes.invitationCard.model

import androidx.annotation.Keep

@Keep
data class InvitationModelItem(
    val template: List<Template>,
    val title: String
)