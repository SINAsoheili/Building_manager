package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Unit

interface ContractUnitList
{
    interface ContractUnitListView
    {
        fun visibleRefreshButton()
        fun showEmptyListText()
        fun showUnitList(list:List<Unit>)
    }

    interface ContractUnitListPresenter
    {
        fun getUnitList(buildingId:Int)
    }
}