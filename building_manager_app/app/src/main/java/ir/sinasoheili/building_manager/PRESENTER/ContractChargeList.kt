package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Charge

interface ContractChargeList
{
    interface ContractChargeListView
    {
        fun showToast(text:String)
        fun showChargeList(items:List<Charge>)
    }

    interface ContractChargeListPresenter
    {
        fun getChargeList(buildingId:Int , unitNumber:Int)
    }
}
