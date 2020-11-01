package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.MODEL.ReceiptDeleteResponse
import ir.sinasoheili.building_manager.R

class PresenterManagerReceiptInfo constructor(val context:Context , val view:ContractManagerReceiptInfo.ContractManagerReceiptInfoView) : ContractManagerReceiptInfo.ContractManagerReceiptInfoPresenter
{
    override fun deleteReceipt(receipt: Receipt)
    {
        val api:API_ReceiptDeleteHandler = API_ReceiptDeleteHandler()
        api.start(receipt.id , object: API_ReceiptDeleteHandler.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(result: ReceiptDeleteResponse)
            {
                if(result.result)
                {
                    view.ReceiptDelete()
                }
                else
                {
                    view.showToast(context.getString(R.string.fail_to_delete))
                }
            }

        })
    }

}