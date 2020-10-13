package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Receipt

interface ContractManagerReceiptInfo
{
    interface ContractManagerReceiptInfoView
    {
        fun showToast(text:String)
        fun ReceiptDelete()
    }

    interface ContractManagerReceiptInfoPresenter
    {
        fun deleteReceipt(receipt:Receipt)
    }
}