package birthdaygreetings.birthdayframe.greetingswishes.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class RootNew(
    var agespecific: ArrayList<Agespecific>? = null,
    var belated: Belated? = null,
    var family: ArrayList<Family>? = null,
    var friends: Friends? = null,
    var funny: Funny? = null,
    var inspiration: Inspiration? = null,
    var nameonbirthdaycake: NameOnBirthdayCake? = null,
    var birthdaynamepoem: BirthdayNamePoem? = null,
    var love: Love? = null,
    var religious: ArrayList<Religiou>? = null,
    var birthdaygif: Birthdaygif? = null,
    var happybirthday: Happybirthday? = null,
    var top100msg: Top100msg? = null,
    var warmmessage: ArrayList<Warmmessage>? = null,
    var createcards: CreateCards? = null,
    var events: ArrayList<Event>? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Agespecific.CREATOR),
        parcel.readParcelable(Belated::class.java.classLoader),
        parcel.createTypedArrayList(Family.CREATOR),
        parcel.readParcelable(Friends::class.java.classLoader),
        parcel.readParcelable(Funny::class.java.classLoader),
        parcel.readParcelable(Inspiration::class.java.classLoader),
        parcel.readParcelable(NameOnBirthdayCake::class.java.classLoader),
        parcel.readParcelable(BirthdayNamePoem::class.java.classLoader),
        parcel.readParcelable(Love::class.java.classLoader),
        parcel.createTypedArrayList(Religiou.CREATOR),
        parcel.readParcelable(Birthdaygif::class.java.classLoader),
        parcel.readParcelable(Happybirthday::class.java.classLoader),
        parcel.readParcelable(Top100msg::class.java.classLoader),
        parcel.createTypedArrayList(Warmmessage.CREATOR),
        parcel.readParcelable(CreateCards::class.java.classLoader),
        parcel.createTypedArrayList(Event.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(agespecific)
        parcel.writeParcelable(belated, flags)
        parcel.writeTypedList(family)
        parcel.writeParcelable(friends, flags)
        parcel.writeParcelable(funny, flags)
        parcel.writeParcelable(inspiration, flags)
        parcel.writeParcelable(nameonbirthdaycake, flags)
        parcel.writeParcelable(birthdaynamepoem, flags)
        parcel.writeParcelable(love, flags)
        parcel.writeTypedList(religious)
        parcel.writeParcelable(birthdaygif, flags)
        parcel.writeParcelable(happybirthday, flags)
        parcel.writeParcelable(top100msg, flags)
        parcel.writeTypedList(warmmessage)
        parcel.writeParcelable(createcards, flags)
        parcel.writeTypedList(events)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RootNew> {
        override fun createFromParcel(parcel: Parcel): RootNew {
            return RootNew(parcel)
        }

        override fun newArray(size: Int): Array<RootNew?> {
            return arrayOfNulls(size)
        }
    }
}

