package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.MODEL.RepairAddResponse
import ir.sinasoheili.building_manager.PRESENTER.ContractRegisterNewRepair.ContractRegisterNewRepairView
import ir.sinasoheili.building_manager.PRESENTER.ContractRegisterNewRepair.ContractRegisterNewRepairPresenter
import ir.sinasoheili.building_manager.PRESENTER.API_RepairAddHandler.CallBack
import ir.sinasoheili.building_manager.R

class PresenterRegisterNewRepair constructor(context:Context , view : ContractRegisterNewRepairView): ContractRegisterNewRepairPresenter
{
    val view : ContractRegisterNewRepairView = view
    val context : Context = context

    override fun registerRepair(repair:Repair)
    {
        val api : API_RepairAddHandler = API_RepairAddHandler()
        api.start(repair , repair.building_id , object:CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(result: RepairAddResponse)
            {
                if(result.result)
                {
                    view.registeredRepair()
                }
                else
                {
                    view.showToast(context.getString(R.string.toast_register_server_error))
                }
            }

        })
    }
}