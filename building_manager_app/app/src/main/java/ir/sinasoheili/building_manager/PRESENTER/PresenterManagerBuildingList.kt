package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Building
import retrofit2.Response

class PresenterManagerBuildingList constructor(context:Context, viewManager:ContractManagerBuildingList.ContractManagerBuildingListView) : ContractManagerBuildingList.ContractManagerBuildingListPresenter
{
    private val context:Context = context
    private val viewManager:ContractManagerBuildingList.ContractManagerBuildingListView = viewManager

    override fun getBuildingList()
    {
        val api : API_BuildingListHandler = API_BuildingListHandler(context)

        val managerId:Int = UserAuthFilePreferenceHandler.readFromFile(context , UserAuthFilePreferenceHandler.KEY_MANAGER_ID)!!.toInt()

        api.start(managerId , object:API_BuildingListHandler.CallBack
        {
            override fun onFailure()
            {
                viewManager.showReloadButton()
            }

            override fun onResponse(response: Response<List<Building>>)
            {
                if(response.code() == 200)
                {
                    val buildingList : List<Building> = response.body()!!
                    if(buildingList.isEmpty())
                    {
                        viewManager.showEmptyListAlert()
                    }
                    else
                    {
                        viewManager.showBuildingList(buildingList)
                    }
                }
                else
                {
                    viewManager.showReloadButton()
                }
            }

        })
    }

}