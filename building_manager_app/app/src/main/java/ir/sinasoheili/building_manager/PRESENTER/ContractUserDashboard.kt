package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Unit

interface ContractUserDashboard
{
    interface ContractUserDashboardView
    {
        fun showToast(text:String)
        fun showUnitInfo(unit:Unit)
    }

    interface ContractUserDashboardPresenter
    {
        fun getUnitInfo()
    }
}