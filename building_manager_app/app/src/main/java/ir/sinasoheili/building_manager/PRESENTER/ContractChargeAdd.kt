package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Charge

interface ContractChargeAdd
{
    interface ContractChargeAddView
    {
        fun showToast(text:String)
        fun onChageRegistered()
    }

    interface ContractChargeAddPresenter
    {
        fun registerCharge(charge:Charge)
    }
}