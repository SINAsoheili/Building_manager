package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context

interface ContractSetRoleManager
{
    interface ContractSetRoleManagerView
    {
        fun showToast(text:String)
        fun moveToBuildingListActivity()
        fun goneProgressBar()
    }

    interface ContractSetRoleManagerPresenter
    {
        fun registerManager(context: Context, phone:String, passwd:String)
    }
}