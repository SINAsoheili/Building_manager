package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Unit

interface ContractUnitInfo
{
    interface ContractUnitInfoView
    {
        fun showToast(text:String)
        fun onUnitDeleted()
    }

    interface ContractUnitInfoPresenter
    {
        fun deleteUnit(unit:Unit)
    }
}