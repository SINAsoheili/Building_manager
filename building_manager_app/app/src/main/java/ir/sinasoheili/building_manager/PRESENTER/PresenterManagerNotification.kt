package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import android.util.Log
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerNotification.ContractManagerNotificationPresenter
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerNotification.ContractManagerNotificationView
import ir.sinasoheili.building_manager.PRESENTER.API_NotificationListHandler.CallBack
import ir.sinasoheili.building_manager.R

class PresenterManagerNotification constructor(val context: Context, val view:ContractManagerNotificationView ) : ContractManagerNotificationPresenter
{
    override fun fetchNotificationList(buildingId: Int)
    {
        val api : API_NotificationListHandler = API_NotificationListHandler()
        api.start(buildingId , object:CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.fail_connect_to_server))
                view.showRefreshButton()
            }

            override fun onResponse(notifList: List<Notification>)
            {
                if(notifList.isEmpty())
                {
                    view.showEmptyListAlert()
                }
                else
                {
                    view.showList(notifList)
                }
            }

        })
    }

}