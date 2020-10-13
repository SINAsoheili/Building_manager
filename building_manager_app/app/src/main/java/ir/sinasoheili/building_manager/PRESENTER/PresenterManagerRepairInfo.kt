package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.MODEL.RepairDeleteResponse
import ir.sinasoheili.building_manager.R

class PresenterManagerRepairInfo constructor(val context:Context , val view:ContractManagerRepairInfo.ContractManagerRepairInfoView): ContractManagerRepairInfo.ContractManagerRepairInfoPresenter
{
    override fun deleteRepair(repair: Repair)
    {
        val api : API_RepairDeleteHandler = API_RepairDeleteHandler()
        api.start(repair.id , object: API_RepairDeleteHandler.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(result: RepairDeleteResponse)
            {
                if(result.result)
                {
                    view.onRepairDeleted()
                }
                else
                {
                    view.showToast(context.getString(R.string.fail_to_delete))
                }
            }

        })
    }

}