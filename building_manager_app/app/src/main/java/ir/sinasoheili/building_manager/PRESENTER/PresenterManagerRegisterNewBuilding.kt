package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Building
import ir.sinasoheili.building_manager.MODEL.BuildingRegisterResponse
import ir.sinasoheili.building_manager.R

class PresenterManagerRegisterNewBuilding constructor(viewManager:ContractManagerRegisterNewBuilding.ContractManagerRegisterNewBuildingView) : ContractManagerRegisterNewBuilding.ContractManagerRegisterNewBuildingPresenter
{
    val viewManager : ContractManagerRegisterNewBuilding.ContractManagerRegisterNewBuildingView = viewManager

    override fun registerBuilding(context: Context, building: Building)
    {
        val apiHandler : API_BuildingRegisterHandler = API_BuildingRegisterHandler(context)
        val managerId : Int = UserAuthFilePreferenceHandler.readFromFile(context , UserAuthFilePreferenceHandler.KEY_MANAGER_ID)!!.toInt()
        apiHandler.start(building , managerId , object:API_BuildingRegisterHandler.callBack
        {
            override fun onFailure()
            {
                viewManager.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(response: BuildingRegisterResponse)
            {
                if(response.status == true)
                {
                    if(response.id != -1)
                    {
                        building.id = response.id
                        viewManager.buildingRegistered(building)
                    }
                }
                else
                {
                    viewManager.showToast(context.getString(R.string.register_server_error))
                }
            }

        })
    }

}