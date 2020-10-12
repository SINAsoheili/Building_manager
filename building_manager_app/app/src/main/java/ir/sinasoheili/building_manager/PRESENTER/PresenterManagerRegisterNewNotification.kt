package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.MODEL.NotificationAddResponse
import ir.sinasoheili.building_manager.R

class PresenterManagerRegisterNewNotification(context: Context, viewManager:ContractManagerRegisterNewNotification.ContractManagerRegisterNewNotificationView) : ContractManagerRegisterNewNotification.ContractManagerRegisterNewNotificationPresenter
{
    val viewManager : ContractManagerRegisterNewNotification.ContractManagerRegisterNewNotificationView = viewManager
    val context : Context = context

    override fun registerNewNotification(notification: Notification)
    {
        val api : API_NotificationAddHandler = API_NotificationAddHandler()
        api.start(notification , object:API_NotificationAddHandler.CallBack
        {
            override fun onFailure()
            {
                viewManager.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(result: NotificationAddResponse)
            {
                if(result.result)
                {
                    viewManager.onNotificationRegistered()
                }
                else
                {
                    viewManager.showToast(context.getString(R.string.toast_register_server_error))
                }
            }
        })
    }

}