package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Charge

interface ContractUserDashboardCharge
{
    interface ContractUserDashboardChargeView
    {
        fun showToast(text:String)
        fun showChargeList(items:List<Charge>)
        fun showEmptyListAlert()
    }

    interface ContractUserDashboardChargePresenter
    {
        fun getChargeList()
    }
}