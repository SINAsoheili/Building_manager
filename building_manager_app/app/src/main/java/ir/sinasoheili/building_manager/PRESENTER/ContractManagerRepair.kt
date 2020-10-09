package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Repair

interface ContractManagerRepair
{
    interface ContractManagerRepairView
    {
        fun showToast(text:String)
        fun showList(items:List<Repair>)
    }

    interface ContractManagerRepairPresenter
    {
        fun getRepairList(buildingId:Int)
    }
}