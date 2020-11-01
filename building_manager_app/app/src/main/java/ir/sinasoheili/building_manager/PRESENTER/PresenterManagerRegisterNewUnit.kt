package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.MODEL.UnitAddResponse
import ir.sinasoheili.building_manager.R

class PresenterManagerRegisterNewUnit constructor(viewManager:ContractManagerRegisterNewUnit.ContractManagerRegisterNewUnitView) : ContractManagerRegisterNewUnit.ContractManagerRegisterNewUnitPresenter
{
    val viewManager : ContractManagerRegisterNewUnit.ContractManagerRegisterNewUnitView = viewManager

    override fun registerUnit(context: Context, unit: Unit)
    {
        val apiHandler : API_UnitAddHandler =  API_UnitAddHandler(context)
        apiHandler.start(unit , object:API_UnitAddHandler.CallBack
        {
            override fun onFailure()
            {
                viewManager.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(response: UnitAddResponse)
            {
                if(response.status)
                {
                    viewManager.unitRegistered()
                }
            }

        })
    }

}