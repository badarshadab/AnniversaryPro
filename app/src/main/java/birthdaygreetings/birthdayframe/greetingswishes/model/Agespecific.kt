package birthdaygreetings.birthdayframe.greetingswishes.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class Agespecific(
    var name: String? = null,
    var icon: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Agespecific> {
        override fun createFromParcel(parcel: Parcel): Agespecific {
            return Agespecific(parcel)
        }

        override fun newArray(size: Int): Array<Agespecific?> {
            return arrayOfNulls(size)
        }
    }
}
