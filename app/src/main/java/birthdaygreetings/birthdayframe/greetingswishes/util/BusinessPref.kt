package birthdaygreetings.birthdayframe.greetingswishes.util

import android.content.Context
import android.content.SharedPreferences

class BusinessPref(context: Context) {
    private val sharedPrefFile = "DailyWishes"
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun storeBusinessUser(companyName : String, name : String,designation :String,phone : String,email : String,address : String,website : String) {
        editor.putString("U_CNAME", companyName)
        editor.putString("U_NAME",name)
        editor.putString("U_DESIGNATION",designation)
        editor.putString("U_PHONE",phone)
        editor.putString("U_EMAIL",email)
        editor.putString("U_ADDRESS",address)
        editor.putString("U_WEBSITE",website)
        editor.apply()
        editor.commit()
    }

    fun getCName(): String {
        return sharedPreferences.getString("U_CNAME","").toString()
    }

    fun getName(): String {
        return sharedPreferences.getString("U_NAME","").toString()
    }

    fun getDesignation(): String {
        return sharedPreferences.getString("U_DESIGNATION","").toString()
    }

    fun getPhone(): String {
        return sharedPreferences.getString("U_PHONE","").toString()
    }

    fun getEmail(): String {
        return sharedPreferences.getString("U_EMAIL","").toString()
    }

    fun getAddress(): String {
        return sharedPreferences.getString("U_ADDRESS","").toString()
    }

    fun getWebsite(): String {
        return sharedPreferences.getString("U_WEBSITE","").toString()
    }

    fun storeBirthdayDetails(birthdayName :String,birthdayDate : String,birthdayPartyAdd :String,age :String){
        editor.putString("B_NAME", birthdayName)
        editor.putString("B_DATE",birthdayDate)
        editor.putString("B_ADDRESS",birthdayPartyAdd)
        editor.putString("B_AGE",age)
        editor.apply()
        editor.commit()
    }

    fun getBirthdayName():String{
        return sharedPreferences.getString("B_NAME","").toString()
    }

    fun getBirthdayDate():String{
        return sharedPreferences.getString("B_DATE","").toString()
    }

    fun getBirthdayAddress():String{
        return sharedPreferences.getString("B_ADDRESS","").toString()
    }

    fun getAge() :String{
        return sharedPreferences.getString("B_AGE","").toString()
    }

    fun storeInvitationDetails(brideName :String,groomName : String,date :String,contact :String,venue :String,brideFName :String,
                               brideMName:String,groomFName:String,groomMName:String){
        editor.putString("AN_BNAME", brideName)
        editor.putString("AN_GNAME",groomName)
        editor.putString("AN_DATE",date)
        editor.putString("AN_CONTACT",contact)
        editor.putString("AN_VENUE",venue)
        editor.putString("BRIDE_FNAME",brideFName)
        editor.putString("BRIDE_MNAME",brideMName)
        editor.putString("GROOM_FNAME",groomFName)
        editor.putString("GROOM_MNAME",groomMName)
        editor.apply()
        editor.commit()
    }

    fun getANbName():String{
        return sharedPreferences.getString("AN_BNAME","").toString()
    }

    fun getANgName():String{
        return sharedPreferences.getString("AN_GNAME","").toString()
    }

    fun getANDate():String{
        return sharedPreferences.getString("AN_DATE","").toString()
    }

    fun getANContact():String{
        return sharedPreferences.getString("AN_CONTACT","").toString()
    }

    fun getANVenue():String{
        return sharedPreferences.getString("AN_VENUE","").toString()
    }

    fun getBrideFName():String{
        return sharedPreferences.getString("BRIDE_FNAME","").toString()
    }

    fun getBrideMName():String{
        return sharedPreferences.getString("BRIDE_MNAME","").toString()
    }

    fun getGroomFName():String{
        return sharedPreferences.getString("GROOM_FNAME","").toString()
    }

    fun getGroomMName():String{
        return sharedPreferences.getString("GROOM_MNAME","").toString()
    }
}