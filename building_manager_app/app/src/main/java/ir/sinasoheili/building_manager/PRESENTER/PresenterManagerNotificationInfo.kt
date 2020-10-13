package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.MODEL.NotificationDeleteResponse
import ir.sinasoheili.building_manager.R

class PresenterManagerNotificationInfo constructor(val context:Context , val view:ContractManagerNotificationInfo.ContractManagerNotificationInfoView): ContractManagerNotificationInfo.ContractManagerNotificationInfoPresenter
{
    override fun deleteNotification(notification: Notification)
    {
        val api:API_NotificationDeleteHandler = API_NotificationDeleteHandler()
        api.start(notification.id , object: API_NotificationDeleteHandler.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.toast_fail_connect_to_server))
            }

            override fun onResponse(result: NotificationDeleteResponse)
            {
                if(result.result)
                {
                    view.notificationDeleted()
                }
                else
                {
                    view.showToast(context.getString(R.string.fail_to_delete))
                }
            }

        })
    }

}