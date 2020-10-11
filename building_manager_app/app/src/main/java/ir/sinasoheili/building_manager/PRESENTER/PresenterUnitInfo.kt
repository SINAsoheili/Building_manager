package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.MODEL.UnitDeleteResponse
import ir.sinasoheili.building_manager.PRESENTER.ContractUnitInfo.ContractUnitInfoPresenter
import ir.sinasoheili.building_manager.PRESENTER.ContractUnitInfo.ContractUnitInfoView
import ir.sinasoheili.building_manager.PRESENTER.API_UnitDeleteHandler.CallBack
import ir.sinasoheili.building_manager.R

class PresenterUnitInfo constructor(val context:Context , val view:ContractUnitInfoView) : ContractUnitInfoPresenter
{
    override fun deleteUnit(unit: Unit)
    {
        val api:API_UnitDeleteHandler = API_UnitDeleteHandler()
        api.start(unit , object:CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(response: UnitDeleteResponse)
            {
                if(response.status)
                {
                    view.onUnitDeleted()
                }
            }

        })
    }
}