package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Receipt

interface ContractUserDashboardReceipt
{
    interface ContractUserDashboardReceiptView
    {
        fun showToast(text:String)
        fun showReceiptList(items:List<Receipt>)
        fun showEmptyListAlert()
    }

    interface ContractUserDashboardReceiptPresenter
    {
        fun getReceiptList()
    }
}