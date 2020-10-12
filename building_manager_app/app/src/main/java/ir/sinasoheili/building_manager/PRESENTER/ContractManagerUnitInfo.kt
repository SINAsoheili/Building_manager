package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Unit

interface ContractManagerUnitInfo
{
    interface ContractManagerUnitInfoView
    {
        fun showToast(text:String)
        fun onUnitDeleted()
    }

    interface ContractManagerUnitInfoPresenter
    {
        fun deleteUnit(unit:Unit)
    }
}