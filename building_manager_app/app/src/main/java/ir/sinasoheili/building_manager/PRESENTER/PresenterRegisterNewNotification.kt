package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.MODEL.NotificationAddResponse
import ir.sinasoheili.building_manager.R

class PresenterRegisterNewNotification(context: Context, view:ContractRegisterNewNotification.ContractRegisterNewNotificationView) : ContractRegisterNewNotification.ContractRegisterNewNotificationPresenter
{
    val view : ContractRegisterNewNotification.ContractRegisterNewNotificationView = view
    val context : Context = context

    override fun registerNewNotification(notification: Notification)
    {
        val api : API_NotificationAddHandler = API_NotificationAddHandler()
        api.start(notification , object:API_NotificationAddHandler.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(result: NotificationAddResponse)
            {
                if(result.result)
                {
                    view.onNotificationRegistered()
                }
                else
                {
                    view.showToast(context.getString(R.string.toast_register_server_error))
                }
            }
        })
    }

}