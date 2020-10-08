package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Repair

interface ContractRegisterNewRepair
{
    interface ContractRegisterNewRepairView
    {
        fun showToast(text:String)
        fun registeredRepair()
    }

    interface ContractRegisterNewRepairPresenter
    {
        fun registerRepair(repair: Repair)
    }
}