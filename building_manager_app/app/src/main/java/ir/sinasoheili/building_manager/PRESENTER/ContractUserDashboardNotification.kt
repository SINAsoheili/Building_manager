package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Notification

interface ContractUserDashboardNotification
{
    interface ContractUserDashboardNotificationView
    {
        fun showNotificationList(items:List<Notification>)
        fun showEmptyAlert()
        fun showToast(text:String)
    }

    interface ContractUserDashboardNotificationPresenter
    {
        fun getNotificationList()
    }
}