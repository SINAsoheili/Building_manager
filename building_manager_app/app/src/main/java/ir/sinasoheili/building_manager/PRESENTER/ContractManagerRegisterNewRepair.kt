package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Repair

interface ContractManagerRegisterNewRepair
{
    interface ContractManagerRegisterNewRepairView
    {
        fun showToast(text:String)
        fun registeredRepair()
    }

    interface ContractManagerRegisterNewRepairPresenter
    {
        fun registerRepair(repair: Repair)
    }
}