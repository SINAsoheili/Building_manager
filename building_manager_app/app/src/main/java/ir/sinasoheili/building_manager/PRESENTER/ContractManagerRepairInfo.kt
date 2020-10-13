package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Repair

interface ContractManagerRepairInfo
{
    interface ContractManagerRepairInfoView
    {
        fun showToast(text:String)
        fun onRepairDeleted()
    }

    interface ContractManagerRepairInfoPresenter
    {
        fun deleteRepair(repair:Repair)
    }
}