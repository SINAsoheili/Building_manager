package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardReceipt.ContractUserDashboardReceiptView
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardReceipt.ContractUserDashboardReceiptPresenter
import ir.sinasoheili.building_manager.R

class PresenterUserDashboardReceipt constructor(val context: Context, val view:ContractUserDashboardReceiptView) : ContractUserDashboardReceiptPresenter
{
    override fun getReceiptList()
    {
        val api : API_ReceiptListHandler = API_ReceiptListHandler()

        val buildingId : Int = UserAuthFilePreferenceHandler.readFromFile(context , UserAuthFilePreferenceHandler.KEY_USER_ID_BuildignId)?.toInt() ?: -1

        api.start(buildingId , object:API_ReceiptListHandler.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(receiptList: List<Receipt>)
            {
                if(receiptList.isEmpty())
                {
                    view.showEmptyListAlert()
                }
                else
                {
                    view.showReceiptList(receiptList)
                }
            }

        })
    }

}