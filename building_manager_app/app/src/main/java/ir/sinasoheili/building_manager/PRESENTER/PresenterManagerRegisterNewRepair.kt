package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.MODEL.RepairAddResponse
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRegisterNewRepair.ContractManagerRegisterNewRepairView
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRegisterNewRepair.ContractManagerRegisterNewRepairPresenter
import ir.sinasoheili.building_manager.PRESENTER.API_RepairAddHandler.CallBack
import ir.sinasoheili.building_manager.R

class PresenterManagerRegisterNewRepair constructor(context:Context, viewManager : ContractManagerRegisterNewRepairView): ContractManagerRegisterNewRepairPresenter
{
    val viewManager : ContractManagerRegisterNewRepairView = viewManager
    val context : Context = context

    override fun registerRepair(repair:Repair)
    {
        val api : API_RepairAddHandler = API_RepairAddHandler()
        api.start(repair , repair.building_id , object:CallBack
        {
            override fun onFailure()
            {
                viewManager.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(result: RepairAddResponse)
            {
                if(result.result)
                {
                    viewManager.registeredRepair()
                }
                else
                {
                    viewManager.showToast(context.getString(R.string.toast_register_server_error))
                }
            }

        })
    }
}