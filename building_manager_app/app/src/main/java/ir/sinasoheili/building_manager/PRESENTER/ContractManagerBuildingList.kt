package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Building

interface ContractManagerBuildingList
{
    interface ContractManagerBuildingListView
    {
        fun showReloadButton()
        fun showBuildingList(buildingList:List<Building>)
    }

    interface ContractManagerBuildingListPresenter
    {
        fun getBuildingList()
    }
}