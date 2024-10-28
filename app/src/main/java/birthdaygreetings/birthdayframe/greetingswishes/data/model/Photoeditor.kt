package birthdaygreetings.birthdayframe.greetingswishes.data.model
import androidx.annotation.Keep


@Keep
data class Photoeditor(
    val createCard: ArrayList<CreateCard>? = null,
    val framecat: ArrayList<Framecat>? = null,
    val posterMaker:ArrayList<PosterMaker>? = null
)