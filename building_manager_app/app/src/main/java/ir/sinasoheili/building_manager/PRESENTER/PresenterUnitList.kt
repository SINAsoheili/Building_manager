package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Unit

class PresenterUnitList constructor(context: Context, view:ContractUnitList.ContractUnitListView) : ContractUnitList.ContractUnitListPresenter
{
    val view : ContractUnitList.ContractUnitListView = view
    val context : Context = context

    override fun getUnitList(buildingId: Int)
    {
        if(buildingId < 0)
            return

        val api : API_UnitListHandler = API_UnitListHandler(context)
        api.start(buildingId , object:API_UnitListHandler.CallBack{
            override fun onFailure()
            {
                view.visibleRefreshButton()
            }

            override fun onResponse(unitList: List<Unit>)
            {
                if(unitList.isEmpty())
                {
                    view.showEmptyListText()
                }
                else
                {
                    view.showUnitList(unitList)
                }
            }
        })
    }
}