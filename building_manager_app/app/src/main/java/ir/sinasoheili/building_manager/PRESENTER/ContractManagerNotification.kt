package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Notification

interface ContractManagerNotification
{
    interface ContractManagerNotificationView
    {
        fun showRefreshButton()
        fun showEmptyListAlert()
        fun showToast(text:String)
        fun showList(items:List<Notification>)
    }

    interface ContractManagerNotificationPresenter
    {
        fun fetchNotificationList(buildingId:Int)
    }
}