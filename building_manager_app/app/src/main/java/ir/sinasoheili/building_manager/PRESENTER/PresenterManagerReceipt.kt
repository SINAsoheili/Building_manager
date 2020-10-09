package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerReceipt.ContractManagerReceiptPresenter
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerReceipt.ContractManagerReceiptView
import ir.sinasoheili.building_manager.PRESENTER.API_ReceiptListHandler.CallBack
import ir.sinasoheili.building_manager.R

class PresenterManagerReceipt constructor(val context:Context , val view:ContractManagerReceiptView) : ContractManagerReceiptPresenter
{
    override fun fetchReceiptList(buildingId: Int)
    {
        val api : API_ReceiptListHandler = API_ReceiptListHandler()
        api.start(buildingId , object:CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(receiptList: List<Receipt>)
            {
                view.showList(receiptList)
            }
        })
    }

}