package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.ChargeDeleteResponse
import ir.sinasoheili.building_manager.MODEL.ChargeUpdateResponse
import ir.sinasoheili.building_manager.R

class PresenterManagerDialogEditCharge constructor(val context:Context , val view:ContractDialogFragmentManagerEditCharge.ContractDialogFragmentManagerEditChargeView)
    : ContractDialogFragmentManagerEditCharge.ContractDialogFragmentManagerEditChargePresenter
{
    override fun deleteCharge(charge: Charge)
    {
        val api : API_ChargeDeleteHandler = API_ChargeDeleteHandler()
        api.start(charge , object:API_ChargeDeleteHandler.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(response: ChargeDeleteResponse)
            {
                if(response.result)
                {
                    view.chargeDeleted()
                }
            }

        })
    }

    override fun updateCharge(charge: Charge)
    {
        val api : API_ChargeUpdateHandler = API_ChargeUpdateHandler()
        api.start(charge , object:API_ChargeUpdateHandler.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(response: ChargeUpdateResponse)
            {
                if(response.result)
                {
                    view.chargeUpdated()
                }
            }
        })
    }

}