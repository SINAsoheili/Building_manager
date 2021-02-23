package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardProfile.ContractUserDashboardProfilePresenter
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardProfile.ContractUserDashboardProfileView
import ir.sinasoheili.building_manager.R

class PresenterUserDashboardProfile constructor (val context: Context, val view:ContractUserDashboardProfileView ) : ContractUserDashboardProfilePresenter
{
    override fun getUnitInfo()
    {
        view.visibleProgressBar()
        view.invisibleReloatButton()

        val api : API_UnitInfoHandler = API_UnitInfoHandler()

        val buildingId:Int = UserAuthFilePreferenceHandler.readFromFile(context , UserAuthFilePreferenceHandler.KEY_USER_ID_BuildignId)?.toInt() ?: -1
        val unitNumber:Int = UserAuthFilePreferenceHandler.readFromFile(context , UserAuthFilePreferenceHandler.KEY_USER_ID_UnitNumber)?.toInt() ?: -1

        api.start(buildingId , unitNumber , object: API_UnitInfoHandler.CallBack
        {
            override fun onFailure()
            {
                view.invisibleProgressBar()
                view.visibleReloatButton()
                view.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(unit: Unit)
            {
                view.invisibleProgressBar()
                view.invisibleReloatButton()
                view.showUnitInfo(unit)
            }

        })
    }

}