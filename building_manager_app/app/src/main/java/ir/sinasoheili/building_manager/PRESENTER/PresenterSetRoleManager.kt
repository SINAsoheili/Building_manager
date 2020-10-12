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
                view.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(response: Response<ManagerRegisterResponse>)
            {
                if(response.body()!!.status) //if status was true : server can insert data to db
                {
                    val id : Int = response.body()!!.manager_id

                    if(id != -1) // if id was not -1 means can retrieve id of inserted manager
                    {
                        ManagerAuthFilePreferenceHandler.writeToFile(context , ManagerAuthFilePreferenceHandler.KEY_MANAGER_ID , id.toString())
                        view.moveToBuildingListActivity()
                    }
                }
                else
                {
                    view.showToast(context.getString(R.string.toast_register_server_error))
                }
            }

        })
    }



//    Toast.makeText(context , context.getString(R.string.toast_fail_connect_to_server) , Toast.LENGTH_SHORT).show()



//    if(response.code() == 200)
//    {
//        if(response.body()!!.status == true)
//        {
//            val id : Int = response.body()!!.manager_id
//            if(id != -1)
//            {
//                AuthFilePreferenceHandler.writeToFile(context , AuthFilePreferenceHandler.KEY_MANAGER_ID , id.toString())
//                view.handlerRegisterResult(result)
//            }
//        }
//        else
//        {
//            Toast.makeText(context , context.getString(R.string.toast_register_server_error), Toast.LENGTH_SHORT).show()
//        }
//    }

}