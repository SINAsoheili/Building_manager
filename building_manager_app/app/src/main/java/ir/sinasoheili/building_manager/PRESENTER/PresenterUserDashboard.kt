package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboard.ContractUserDashboardPresenter
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboard.ContractUserDashboardView
import ir.sinasoheili.building_manager.R

class PresenterUserDashboard constructor (val context: Context, val view:ContractUserDashboardView ) : ContractUserDashboardPresenter
{
    override fun getUnitInfo()
    {
        val api : API_UnitInfoHandler = API_UnitInfoHandler()

        val buildingId:Int = UserAuthFilePreferenceHandler.readFromFile(context , UserAuthFilePreferenceHandler.KEY_USER_ID_BuildignId)?.toInt() ?: -1
        val unitNumber:Int = UserAuthFilePreferenceHandler.readFromFile(context , UserAuthFilePreferenceHandler.KEY_USER_ID_UnitNumber)?.toInt() ?: -1

        api.start(buildingId , unitNumber , object: API_UnitInfoHandler.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(unit: Unit)
            {
                view.showUnitInfo(unit)
            }

        })
    }

}