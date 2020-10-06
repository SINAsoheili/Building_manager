package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Building

interface ContractRegisterNewBuilding
{
    interface ContractRegisterNewBuildingView
    {
        fun showToast(text:String)
        fun buildingRegistered(building:Building)
    }

    interface ContractRegisterNewBuildingPresenter
    {
        fun registerBuilding(context: Context, building:Building)
    }
}