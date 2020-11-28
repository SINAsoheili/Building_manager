package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Building

interface ContractManagerBuildingList
{
    interface ContractManagerBuildingListView
    {
        fun showReloadButton()
        fun showEmptyListAlert()
        fun showBuildingList(buildingList:List<Building>)
        fun showToast(text:String)
    }

    interface ContractManagerBuildingListPresenter
    {
        fun getBuildingList()
    }
}