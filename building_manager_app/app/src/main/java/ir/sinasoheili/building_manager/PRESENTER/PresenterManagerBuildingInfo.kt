package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Building
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerBuildingInfo.ContractManagerBuildingInfoPresenter
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerBuildingInfo.ContractManagerBuildingInfoView
import ir.sinasoheili.building_manager.R

class PresenterManagerBuildingInfo constructor(val context:Context , val view:ContractManagerBuildingInfoView ) : ContractManagerBuildingInfoPresenter
{
    override fun getBuildingInfo(buildingId: Int)
    {
        val api : API_BuildingInfo = API_BuildingInfo()
        api.start(buildingId , object:API_BuildingInfo.CallBack
        {
            override fun onFailure()
            {
                view.showRefreshButton()
            }

            override fun onResponse(buildingList: List<Building>)
            {
                view.fillItem(buildingList.get(0))
            }
        })
    }
}