package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Notification

interface ContractManagerNotificationInfo
{
    interface ContractManagerNotificationInfoView
    {
        fun showToast(text:String)
        fun notificationDeleted()
    }

    interface ContractManagerNotificationInfoPresenter
    {
        fun deleteNotification(notification:Notification)
    }
}