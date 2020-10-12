package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Unit

interface ContractManagerRegisterNewUnit
{
    interface ContractManagerRegisterNewUnitView
    {
        fun showToast(text:String)
        fun unitRegistered()
    }

    interface ContractManagerRegisterNewUnitPresenter
    {
        fun registerUnit(context: Context, unit:Unit)
    }
}