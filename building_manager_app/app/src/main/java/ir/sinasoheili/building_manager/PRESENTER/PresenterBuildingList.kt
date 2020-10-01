package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Building
import retrofit2.Response

class PresenterBuildingList constructor(context:Context, view:ContractBuildingList.ContractBuildingListView) : ContractBuildingList.ContractBuildingListPresenter
{
    private val context:Context = context
    private val view:ContractBuildingList.ContractBuildingListView = view

    override fun getBuildingList()
    {
        val api : API_BuildingListHandler = API_BuildingListHandler(context)

        val managerId:Int = AuthFilePreferenceHandler.readFromFile(context , AuthFilePreferenceHandler.KEY_MANAGER_ID)!!.toInt()

        api.start(managerId , object:API_BuildingListHandler.CallBack
        {
            override fun onFailure()
            {
                view.showReloadButton()
            }

            override fun onResponse(response: Response<List<Building>>)
            {
                if(response.code() == 200)
                {
                    val buildingList : List<Building> = response.body()!!
                    if(buildingList.isEmpty())
                    {
                        view.showReloadButton()
                    }
                    else
                    {
                        view.showBuildingList(buildingList)
                    }
                }
            }

        })
    }

}