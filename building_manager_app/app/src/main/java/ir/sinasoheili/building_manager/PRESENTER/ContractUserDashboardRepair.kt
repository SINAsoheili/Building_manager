package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Repair

interface ContractUserDashboardRepair
{
    interface ContractUserDashboardRepairView
    {
        fun showToast(text:String)
        fun showRepairList(items:List<Repair>)
    }

    interface ContractUserDashboardRepairPresenter
    {
        fun getRepairList()
    }
}