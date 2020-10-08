package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Notification

interface ContractRegisterNewNotification
{
    interface ContractRegisterNewNotificationView
    {
        fun showToast(text:String)
        fun onNotificationRegistered()
    }

    interface ContractRegisterNewNotificationPresenter
    {
        fun registerNewNotification(notification : Notification)
    }
}