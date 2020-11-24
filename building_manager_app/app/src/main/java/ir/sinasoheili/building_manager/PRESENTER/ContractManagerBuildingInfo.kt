package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Building

interface ContractManagerBuildingInfo
{
    interface ContractManagerBuildingInfoView
    {
        fun fillItem(building : Building)
        fun showRefreshButton()
    }

    interface ContractManagerBuildingInfoPresenter
    {
        fun getBuildingInfo(buildingId : Int)
    }
}