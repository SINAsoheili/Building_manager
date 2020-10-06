package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Building
import ir.sinasoheili.building_manager.MODEL.BuildingRegisterResponse
import ir.sinasoheili.building_manager.R

class PresenterRegisterNewBuilding constructor(view:ContractRegisterNewBuilding.ContractRegisterNewBuildingView) : ContractRegisterNewBuilding.ContractRegisterNewBuildingPresenter
{
    val view : ContractRegisterNewBuilding.ContractRegisterNewBuildingView = view

    override fun registerBuilding(context: Context, building: Building)
    {
        val apiHandler : API_BuildingRegisterHandler = API_BuildingRegisterHandler(context)
        val managerId : Int = AuthFilePreferenceHandler.readFromFile(context , AuthFilePreferenceHandler.KEY_MANAGER_ID)!!.toInt()
        apiHandler.start(building , managerId , object:API_BuildingRegisterHandler.callBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(response: BuildingRegisterResponse)
            {
                if(response.status == true)
                {
                    if(response.id != -1)
                    {
                        building.id = response.id
                        view.buildingRegistered(building)
                    }
                }
                else
                {
                    view.showToast(context.getString(R.string.toast_register_server_error))
                }
            }

        })
    }

}