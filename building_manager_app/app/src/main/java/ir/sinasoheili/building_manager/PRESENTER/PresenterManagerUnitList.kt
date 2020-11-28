package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.R

class PresenterManagerUnitList constructor(context: Context, viewManager:ContractManagerUnitList.ContractManagerUnitListView) : ContractManagerUnitList.ContractManagerUnitListPresenter
{
    val viewManager : ContractManagerUnitList.ContractManagerUnitListView = viewManager
    val context : Context = context

    override fun getUnitList(buildingId: Int)
    {
        if(buildingId < 0)
            return

        val api : API_UnitListHandler = API_UnitListHandler(context)
        api.start(buildingId , object:API_UnitListHandler.CallBack
        {
            override fun onFailure()
            {
                viewManager.showRefreshButton()
                viewManager.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(unitList: List<Unit>)
            {
                if(unitList.isEmpty())
                {
                    viewManager.showEmptyListText()
                }
                else
                {
                    viewManager.showUnitList(unitList)
                }
            }
        })
    }
}