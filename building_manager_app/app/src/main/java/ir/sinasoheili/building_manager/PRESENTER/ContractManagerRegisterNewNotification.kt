package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Notification

interface ContractManagerRegisterNewNotification
{
    interface ContractManagerRegisterNewNotificationView
    {
        fun showToast(text:String)
        fun onNotificationRegistered()
    }

    interface ContractManagerRegisterNewNotificationPresenter
    {
        fun registerNewNotification(notification : Notification)
    }
}