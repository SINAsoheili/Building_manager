package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRepair.ContractManagerRepairPresenter
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRepair.ContractManagerRepairView
import ir.sinasoheili.building_manager.PRESENTER.API_RepairListHandler.CallBack
import ir.sinasoheili.building_manager.R

class PresenterManagerRepair(val context:Context , val view:ContractManagerRepairView) : ContractManagerRepairPresenter
{
    override fun getRepairList(buildingId: Int)
    {
        val api:API_RepairListHandler = API_RepairListHandler()
        api.start(buildingId , object:CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.fail_connect_to_server))
                view.showRefreshButton()
            }

            override fun onResponse(repairList: List<Repair>)
            {
                if(repairList.isEmpty())
                {
                    view.showEmptyListAlert()
                }
                else
                {
                    view.showList(repairList)
                }
            }

        })
    }

}