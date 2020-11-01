package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.ChargeAddResponse
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerChargeAdd.ContractManagerChargeAddPresenter
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerChargeAdd.ContractManagerChargeAddView
import ir.sinasoheili.building_manager.R

class PresenterManagerChargeAdd constructor(val context:Context, val viewManager:ContractManagerChargeAddView): ContractManagerChargeAddPresenter
{
    override fun registerCharge(charge: Charge)
    {
        val api : API_ChargeAddHandler = API_ChargeAddHandler()
        api.start(charge , object:API_ChargeAddHandler.CallBack
        {
            override fun onFailure()
            {
                viewManager.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(result: ChargeAddResponse)
            {
                viewManager.onChageRegistered()
            }
        })
    }
}