package birthdaygreetings.birthdayframe.greetingswishes.invitationCard.model

import androidx.annotation.Keep

@Keep
data class Component(
    val com_Ypos: String,
    val comp_Xpos: String,
    val comp_id: Int,
    val comp_name: String,
    val font: String,
    val text_color: String,
    val text_size: String
)