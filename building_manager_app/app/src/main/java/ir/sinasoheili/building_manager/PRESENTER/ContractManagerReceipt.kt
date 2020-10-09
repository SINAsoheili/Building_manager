package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Receipt

interface ContractManagerReceipt
{
    interface ContractManagerReceiptView
    {
        fun showToast(text:String)
        fun showList(items:List<Receipt>)
    }

    interface ContractManagerReceiptPresenter
    {
        fun fetchReceiptList(buildingId:Int)
    }
}
