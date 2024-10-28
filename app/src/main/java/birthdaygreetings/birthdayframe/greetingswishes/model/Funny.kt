package birthdaygreetings.birthdayframe.greetingswishes.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class Funny(
    var name: String? = null,
    var type: Int = 0): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Funny> {
        override fun createFromParcel(parcel: Parcel): Funny {
            return Funny(parcel)
        }

        override fun newArray(size: Int): Array<Funny?> {
            return arrayOfNulls(size)
        }
    }
}
