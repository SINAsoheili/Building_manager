package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Unit

interface ContractManagerUnitList
{
    interface ContractManagerUnitListView
    {
        fun visibleRefreshButton()
        fun showEmptyListText()
        fun showUnitList(list:List<Unit>)
    }

    interface ContractManagerUnitListPresenter
    {
        fun getUnitList(buildingId:Int)
    }
}