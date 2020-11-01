package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.UserAuthenticateResponse
import ir.sinasoheili.building_manager.PRESENTER.ContractSetRoleUser.ContractSetRoleUserPresenter
import ir.sinasoheili.building_manager.PRESENTER.ContractSetRoleUser.ContractSetRoleUserView
import ir.sinasoheili.building_manager.R

class PresenterSetRoleUser constructor(val context:Context , val view:ContractSetRoleUserView) : ContractSetRoleUserPresenter
{
    override fun authenticateUser(phone:String , buildingId:Int)
    {
        val api : API_UserAuthenticate = API_UserAuthenticate()
        api.start(phone , buildingId , object:API_UserAuthenticate.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(result: UserAuthenticateResponse)
            {
                if(result.result)
                {
                    view.userAuthenticateSuccess(result.unitNumber , buildingId)
                }
                else
                {
                    view.showToast(context.getString(R.string.user_fail_authentication))
                }
            }
        })
    }
}