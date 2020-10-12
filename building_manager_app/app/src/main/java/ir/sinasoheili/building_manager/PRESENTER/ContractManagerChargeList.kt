package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Charge

interface ContractManagerChargeList
{
    interface ContractManagerChargeListView
    {
        fun showToast(text:String)
        fun showChargeList(items:List<Charge>)
    }

    interface ContractManagerChargeListPresenter
    {
        fun getChargeList(buildingId:Int , unitNumber:Int)
    }
}
