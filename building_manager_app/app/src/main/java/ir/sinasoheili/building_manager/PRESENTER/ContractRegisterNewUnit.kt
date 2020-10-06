package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import ir.sinasoheili.building_manager.MODEL.Unit

interface ContractRegisterNewUnit
{
    interface ContractRegisterNewUnitView
    {
        fun showToast(text:String)
        fun unitRegistered()
    }

    interface ContractRegisterNewUnitPresenter
    {
        fun registerUnit(context: Context, unit:Unit)
    }
}