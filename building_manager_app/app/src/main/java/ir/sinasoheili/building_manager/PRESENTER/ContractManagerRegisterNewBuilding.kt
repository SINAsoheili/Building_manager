package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Building

interface ContractManagerRegisterNewBuilding
{
    interface ContractManagerRegisterNewBuildingView
    {
        fun showToast(text:String)
        fun buildingRegistered(building:Building)
    }

    interface ContractManagerRegisterNewBuildingPresenter
    {
        fun registerBuilding(context: Context, building:Building)
    }
}