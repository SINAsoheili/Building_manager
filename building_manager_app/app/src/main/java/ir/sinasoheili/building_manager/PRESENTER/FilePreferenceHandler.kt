package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import android.content.SharedPreferences

object FilePreferenceHandler
{
    val fileName : String = "user_auth_info"

    fun writeToFile(context:Context , key:String , value:String)
    {
        val pref : SharedPreferences = context.getSharedPreferences(fileName , Context.MODE_PRIVATE)
        pref.edit().putString(key , value).apply()
    }

    fun readFromFile(context:Context , key:String):String?
    {
        val pref : SharedPreferences = context.getSharedPreferences(fileName , Context.MODE_PRIVATE)
        return pref.getString(key , null)
    }

    fun containKey(context:Context , key:String) : Boolean
    {
        val pref : SharedPreferences = context.getSharedPreferences(fileName , Context.MODE_PRIVATE)
        return pref.contains(key)
    }
}