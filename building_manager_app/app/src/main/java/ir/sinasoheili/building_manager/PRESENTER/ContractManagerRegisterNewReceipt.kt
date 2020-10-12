package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Receipt

interface ContractManagerRegisterNewReceipt
{
    interface ContractManagerRegisterNewReceiptView
    {
        fun showToast(text:String)
        fun registeredReceipt()
    }

    interface ContractManagerRegisterNewReceiptPresenter
    {
        fun registerReceipt(receipt:Receipt)
    }
}