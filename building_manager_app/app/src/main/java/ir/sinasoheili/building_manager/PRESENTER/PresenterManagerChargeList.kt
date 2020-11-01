package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.R

class PresenterManagerChargeList constructor(val context: Context, val viewManager:ContractManagerChargeList.ContractManagerChargeListView): ContractManagerChargeList.ContractManagerChargeListPresenter
{
    override fun getChargeList(buildingId: Int, unitNumber: Int)
    {
        val api : API_ChargeListHandler = API_ChargeListHandler()
        api.start(buildingId , unitNumber , object:API_ChargeListHandler.CallBack
        {
            override fun onFailure()
            {
                viewManager.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(items: List<Charge>)
            {
                viewManager.showChargeList(items)
            }
        })
    }

}