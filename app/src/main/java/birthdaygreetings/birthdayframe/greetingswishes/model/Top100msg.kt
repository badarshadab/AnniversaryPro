package birthdaygreetings.birthdayframe.greetingswishes.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep

data class Top100msg(
    var name: String? = null,
    var type: Int = 0
) : Parcelable {
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

    companion object CREATOR : Parcelable.Creator<Top100msg> {
        override fun createFromParcel(parcel: Parcel): Top100msg {
            return Top100msg(parcel)
        }

        override fun newArray(size: Int): Array<Top100msg?> {
            return arrayOfNulls(size)
        }
    }
}

