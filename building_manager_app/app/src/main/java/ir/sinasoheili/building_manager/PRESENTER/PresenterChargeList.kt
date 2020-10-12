package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.R

class PresenterChargeList constructor(val context: Context , val view:ContractChargeList.ContractChargeListView): ContractChargeList.ContractChargeListPresenter
{
    override fun getChargeList(buildingId: Int, unitNumber: Int)
    {
        val api : API_ChargeListHandler = API_ChargeListHandler()
        api.start(buildingId , unitNumber , object:API_ChargeListHandler.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(items: List<Charge>)
            {
                view.showChargeList(items)
            }
        })
    }

}