package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.ChargeAddResponse
import ir.sinasoheili.building_manager.PRESENTER.ContractChargeAdd.ContractChargeAddPresenter
import ir.sinasoheili.building_manager.PRESENTER.ContractChargeAdd.ContractChargeAddView
import ir.sinasoheili.building_manager.R

class PresenterChargeAdd constructor(val context:Context , val view:ContractChargeAddView): ContractChargeAddPresenter
{
    override fun registerCharge(charge: Charge)
    {
        val api : API_ChargeAddHandler = API_ChargeAddHandler()
        api.start(charge , object:API_ChargeAddHandler.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(result: ChargeAddResponse)
            {
                view.onChageRegistered()
            }
        })
    }
}