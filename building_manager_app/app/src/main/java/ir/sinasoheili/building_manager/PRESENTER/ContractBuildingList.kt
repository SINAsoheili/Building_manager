package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Building

interface ContractBuildingList
{
    interface ContractBuildingListView
    {
        fun showReloadButton()
        fun showBuildingList(buildingList:List<Building>)
    }

    interface ContractBuildingListPresenter
    {
        fun getBuildingList()
    }
}