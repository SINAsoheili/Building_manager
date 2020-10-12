package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Charge

interface ContractManagerChargeAdd
{
    interface ContractManagerChargeAddView
    {
        fun showToast(text:String)
        fun onChageRegistered()
    }

    interface ContractManagerChargeAddPresenter
    {
        fun registerCharge(charge:Charge)
    }
}