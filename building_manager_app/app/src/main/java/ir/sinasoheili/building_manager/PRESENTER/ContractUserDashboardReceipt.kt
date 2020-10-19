package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Receipt

interface ContractUserDashboardReceipt
{
    interface ContractUserDashboardReceiptView
    {
        fun showRefreshButton()
        fun showReceiptList(items:List<Receipt>)
    }

    interface ContractUserDashboardReceiptPresenter
    {
        fun getReceiptList()
    }
}