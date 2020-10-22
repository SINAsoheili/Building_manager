package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Notification

interface ContractUserDashboardNotification
{
    interface ContractUserDashboardNotificationView
    {
        fun showRefreshButton()
        fun showNotificationList(items:List<Notification>)
    }

    interface ContractUserDashboardNotificationPresenter
    {
        fun getNotificationList()
    }
}