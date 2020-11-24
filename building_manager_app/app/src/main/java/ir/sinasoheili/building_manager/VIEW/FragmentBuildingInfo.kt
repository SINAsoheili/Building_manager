package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Building
import ir.sinasoheili.building_manager.R

class FragmentBuildingInfo constructor(): Fragment(R.layout.fragment_building_info)
{
    private var tvName : TextView? = null
    private var tvAddress : TextView? = null
    private var tvUnitCount : TextView? = null
    private var tvCash : TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view:View)
    {
        tvName = view.findViewById(R.id.tv_building_info_name)
        tvAddress = view.findViewById(R.id.tv_building_info_address)
        tvUnitCount = view.findViewById(R.id.tv_building_info_unitCount)
        tvCash = view.findViewById(R.id.tv_building_info_cash)
    }

    private fun fillItem(building : Building)
    {
        tvName!!.text = building.name
        tvAddress!!.text = building.address
        tvUnitCount!!.text = building.unit_count.toString()
        tvCash!!.text = building.cash.toString()
    }
}