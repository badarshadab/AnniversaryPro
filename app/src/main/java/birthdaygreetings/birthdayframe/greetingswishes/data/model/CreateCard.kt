package birthdaygreetings.birthdayframe.greetingswishes.data.model
import androidx.annotation.Keep


@Keep
data class CreateCard(
    val date: String? = null,
    val icon: String? = null,
    val name: String? = null
)