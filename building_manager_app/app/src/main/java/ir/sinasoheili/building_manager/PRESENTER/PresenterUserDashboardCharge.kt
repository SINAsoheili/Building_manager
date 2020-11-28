package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardCharge.ContractUserDashboardChargeView
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardCharge.ContractUserDashboardChargePresenter
import ir.sinasoheili.building_manager.R

class PresenterUserDashboardCharge constructor(val context: Context, val view:ContractUserDashboardChargeView) : ContractUserDashboardChargePresenter
{
    override fun getChargeList()
    {
        val api : API_ChargeListHandler = API_ChargeListHandler()

        val buildingId : Int = UserAuthFilePreferenceHandler.readFromFile(context , UserAuthFilePreferenceHandler.KEY_USER_ID_BuildignId)?.toInt() ?: -1
        val unitNumber : Int = UserAuthFilePreferenceHandler.readFromFile(context , UserAuthFilePreferenceHandler.KEY_USER_ID_UnitNumber)?.toInt() ?: -1

        api.start(buildingId , unitNumber , object:API_ChargeListHandler.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(items: List<Charge>)
            {
                view.showChargeList(items)
            }

        })
    }
}