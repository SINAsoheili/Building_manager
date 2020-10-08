package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.MODEL.ReceiptAddResponse
import ir.sinasoheili.building_manager.PRESENTER.API_ReceiptAddHandler.CallBack
import ir.sinasoheili.building_manager.R

class PresenterRegisterNewReceipt constructor(context: Context, view:ContractRegisterNewReceipt.ContractRegisterNewReceiptView): ContractRegisterNewReceipt.ContractRegisterNewReceiptPresenter
{
    val view : ContractRegisterNewReceipt.ContractRegisterNewReceiptView = view
    val context : Context = context

    override fun registerReceipt(receipt: Receipt)
    {
        val api : API_ReceiptAddHandler = API_ReceiptAddHandler()
        api.start(receipt , object:CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(result: ReceiptAddResponse)
            {
                if(result.result)
                {
                    view.registeredReceipt()
                }
                else
                {
                    view.showToast(context.getString(R.string.toast_register_server_error))
                }
            }

        })
    }

}