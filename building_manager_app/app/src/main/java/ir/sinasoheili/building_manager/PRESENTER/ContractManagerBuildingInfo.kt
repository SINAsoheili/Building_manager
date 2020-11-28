package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Building

interface ContractManagerBuildingInfo
{
    interface ContractManagerBuildingInfoView
    {
        fun fillItem(building : Building)
        fun showRefreshButton()
        fun showToast(text:String)
    }

    interface ContractManagerBuildingInfoPresenter
    {
        fun getBuildingInfo(buildingId : Int)
    }
}