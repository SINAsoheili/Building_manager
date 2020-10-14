package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Charge

interface ContractDialogFragmentManagerEditCharge
{
    interface ContractDialogFragmentManagerEditChargeView
    {
        fun showToast(text:String)
        fun chargeDeleted()
        fun chargeUpdated()
    }

    interface ContractDialogFragmentManagerEditChargePresenter
    {
        fun deleteCharge(charge:Charge)
        fun updateCharge(charge:Charge)
    }
}