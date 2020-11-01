package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.MODEL.ReceiptAddResponse
import ir.sinasoheili.building_manager.PRESENTER.API_ReceiptAddHandler.CallBack
import ir.sinasoheili.building_manager.R

class PresenterManagerRegisterNewReceipt constructor(context: Context, viewManager:ContractManagerRegisterNewReceipt.ContractManagerRegisterNewReceiptView): ContractManagerRegisterNewReceipt.ContractManagerRegisterNewReceiptPresenter
{
    val viewManager : ContractManagerRegisterNewReceipt.ContractManagerRegisterNewReceiptView = viewManager
    val context : Context = context

    override fun registerReceipt(receipt: Receipt)
    {
        val api : API_ReceiptAddHandler = API_ReceiptAddHandler()
        api.start(receipt , object:CallBack
        {
            override fun onFailure()
            {
                viewManager.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(result: ReceiptAddResponse)
            {
                if(result.result)
                {
                    viewManager.registeredReceipt()
                }
                else
                {
                    viewManager.showToast(context.getString(R.string.toast_register_server_error))
                }
            }

        })
    }

}