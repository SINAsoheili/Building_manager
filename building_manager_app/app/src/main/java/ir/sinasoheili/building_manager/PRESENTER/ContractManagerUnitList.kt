package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Unit

interface ContractManagerUnitList
{
    interface ContractManagerUnitListView
    {
        fun showRefreshButton()
        fun showEmptyListText()
        fun showUnitList(list:List<Unit>)
        fun showToast(text:String)
    }

    interface ContractManagerUnitListPresenter
    {
        fun getUnitList(buildingId:Int)
    }
}