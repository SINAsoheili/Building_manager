package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Receipt

interface ContractRegisterNewReceipt
{
    interface ContractRegisterNewReceiptView
    {
        fun showToast(text:String)
        fun registeredReceipt()
    }

    interface ContractRegisterNewReceiptPresenter
    {
        fun registerReceipt(receipt:Receipt)
    }
}