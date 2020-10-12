package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import android.content.SharedPreferences

object ManagerAuthFilePreferenceHandler
{
    val FILE_NAME : String = "user_auth_info"
    val KEY_MANAGER_ID : String = "MANAGER_ID"

    fun writeToFile(context:Context , key:String , value:String)
    {
        val pref : SharedPreferences = context.getSharedPreferences(FILE_NAME , Context.MODE_PRIVATE)
        pref.edit().putString(key , value).apply()
    }

    fun readFromFile(context:Context , key:String):String?
    {
        val pref : SharedPreferences = context.getSharedPreferences(FILE_NAME , Context.MODE_PRIVATE)
        return pref.getString(key , null)
    }

    fun containKey(context:Context , key:String) : Boolean
    {
        val pref : SharedPreferences = context.getSharedPreferences(FILE_NAME , Context.MODE_PRIVATE)
        return pref.contains(key)
    }
}