package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardRepair.ContractUserDashboardRepairView
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardRepair.ContractUserDashboardRepairPresenter

class PresenterUserDashboardRepair constructor(val context: Context, val view:ContractUserDashboardRepairView) : ContractUserDashboardRepairPresenter
{
    override fun getRepairList()
    {
        val api : API_RepairListHandler = API_RepairListHandler()

        val buildingId : Int = UserAuthFilePreferenceHandler.readFromFile(context , UserAuthFilePreferenceHandler.KEY_USER_ID_BuildignId)?.toInt() ?: -1

        api.start(buildingId , object:API_RepairListHandler.CallBack
        {
            override fun onFailure()
            {
                view.showRefreshButton()
            }

            override fun onResponse(repairList: List<Repair>)
            {
                view.showRepairList(repairList)
            }

        })
    }

}