package birthdaygreetings.birthdayframe.greetingswishes.invitationCard.model

import androidx.annotation.Keep
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.model.Component

@Keep
data class Template(
    val component: List<Component>,
    val id: Int,
    val img_url: String,
    val sample_url: String
)