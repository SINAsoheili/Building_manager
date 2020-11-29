package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardNotification.ContractUserDashboardNotificationView
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardNotification.ContractUserDashboardNotificationPresenter
import ir.sinasoheili.building_manager.R

class PresenterUserDashboardNotification constructor(val context: Context, val view:ContractUserDashboardNotificationView) : ContractUserDashboardNotificationPresenter
{
    override fun getNotificationList()
    {
        val api : API_NotificationListHandler = API_NotificationListHandler()

        val buildingId : Int = UserAuthFilePreferenceHandler.readFromFile(context , UserAuthFilePreferenceHandler.KEY_USER_ID_BuildignId)?.toInt() ?: -1

        api.start(buildingId , object:API_NotificationListHandler.CallBack
        {
            override fun onFailure()
            {
                view.showToast(context.getString(R.string.fail_connect_to_server))
            }

            override fun onResponse(notifList: List<Notification>)
            {
                if(notifList.isEmpty())
                {
                    view.showEmptyAlert()
                }
                else
                {
                    view.showNotificationList(notifList)
                }
            }

        })
    }
}