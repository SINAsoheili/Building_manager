package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Building
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerBuildingInfo.ContractManagerBuildingInfoView
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerBuildingInfo
import ir.sinasoheili.building_manager.PRESENTER.UserAuthFilePreferenceHandler
import ir.sinasoheili.building_manager.R
import kotlinx.android.synthetic.main.fragment_building_info.*

class FragmentBuildingInfo constructor(val buildingId:Int): Fragment(R.layout.fragment_building_info) , ContractManagerBuildingInfoView , View.OnClickListener
{
    private var tvName : TextView? = null
    private var tvAddress : TextView? = null
    private var tvUnitCount : TextView? = null
    private var tvCash : TextView? = null
    private var tvBuildingId : TextView? = null
    private var tvRefreshAlert : TextView? = null
    private var ivRefresh : ImageView? = null
    private var container : LinearLayout? = null

    private var presenter : PresenterManagerBuildingInfo? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        presenter!!.getBuildingInfo(buildingId)
    }

    private fun initObj(view:View)
    {
        presenter = PresenterManagerBuildingInfo(view.context , this)

        container = view.findViewById(R.id.ll_building_info_container)

        tvName = view.findViewById(R.id.tv_building_info_name)
        tvAddress = view.findViewById(R.id.tv_building_info_address)
        tvUnitCount = view.findViewById(R.id.tv_building_info_unitCount)
        tvCash = view.findViewById(R.id.tv_building_info_cash)
        tvBuildingId = view.findViewById(R.id.tv_building_info_buildingId)
        tvRefreshAlert = view.findViewById(R.id.tv_building_info_refreshAlert)

        ivRefresh = view.findViewById(R.id.iv_building_info_refresh)
        ivRefresh!!.setOnClickListener(this)
    }

    override fun fillItem(building : Building)
    {
        invisibleRefreshButton()
        visibleItem()

        tvName!!.text = building.name
        tvAddress!!.text = building.address
        tvUnitCount!!.text = building.unit_count.toString()
        tvCash!!.text = building.cash.toString()
        tvBuildingId!!.text = building.id.toString()
    }

    override fun showRefreshButton()
    {
        invisibleItem()
        visibleRefreshButton()
    }

    private fun visibleItem()
    {
        ll_building_info_container.visibility = View.VISIBLE
    }

    private fun invisibleItem()
    {
        ll_building_info_container.visibility = View.GONE
    }

    private fun invisibleRefreshButton()
    {
        ivRefresh!!.visibility = View.GONE
        tvRefreshAlert!!.visibility = View.GONE
    }

    private fun visibleRefreshButton()
    {
        ivRefresh!!.visibility = View.VISIBLE
        tvRefreshAlert!!.visibility = View.VISIBLE
    }

    override fun onClick(p0: View?)
    {
        when(p0)
        {
            ivRefresh ->
            {
                presenter!!.getBuildingInfo(buildingId)
            }
        }
    }
}