package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.ManagerRegisterResponse
import ir.sinasoheili.building_manager.R
import retrofit2.Response

class PresenterSetRoleManager constructor(view:ContractSetRoleManager.ContractSetRoleManagerView) : ContractSetRoleManager.ContractSetRoleManagerPresenter
{
    val view : ContractSetRoleManager.ContractSetRoleManagerView = view

    override fun registerManager(context: Context, phone: String, passwd: String)
    {
        val api : API_RegisterManagerHandler = API_RegisterManagerHandler(context)

        api.start(passwd , phone , object:API_RegisterManagerHandler.callBack
        {
            override fun onFailure()
            {
                view.goneProgressBar()
                view.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(response: Response<ManagerRegisterResponse>)
            {
                if(response.isSuccessful)
                {
                    if(response.body()!!.query_execute_status)
                    {
                        val id : Int = response.body()!!.manager_id

                        if(id != -1) //if id was not -1 means insert or login was successful
                        {
                            UserAuthFilePreferenceHandler.writeToFile(context , UserAuthFilePreferenceHandler.KEY_MANAGER_ID , id.toString())
                            view.moveToBuildingListActivity()
                        } else {
                            view.goneProgressBar()
                            view.showToast(context.getString(R.string.user_exists) + context.getString(R.string.insert_correct_password))
                        }
                    }
                    else
                    {
                        view.goneProgressBar()
                        view.showToast(context.getString(R.string.sever_internal_fail))
                    }
                }
                else
                {
                    view.goneProgressBar()
                    view.showToast(context.getString(R.string.sever_internal_fail))
                }
            }

        })
    }
}