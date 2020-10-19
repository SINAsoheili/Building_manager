package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Unit

interface ContractUserDashboardProfile
{
    interface ContractUserDashboardProfileView
    {
        fun showToast(text:String)
        fun showUnitInfo(unit:Unit)
    }

    interface ContractUserDashboardProfilePresenter
    {
        fun getUnitInfo()
    }
}