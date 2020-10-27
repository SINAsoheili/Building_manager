package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardCharge.ContractUserDashboardChargeView
import ir.sinasoheili.building_manager.PRESENTER.PresenterUserDashboardCharge

class FragmentUserDashboardCharge : Fragment(R.layout.user_dashboard_charge_fragment) , ContractUserDashboardChargeView, View.OnClickListener
{
    private var listView : ListView? = null
    private var ivRefresh : ImageView? = null

    private var presenter : PresenterUserDashboardCharge? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        presenter!!.getChargeList()
    }

    private fun initObj(view:View)
    {
        presenter = PresenterUserDashboardCharge(view.context , this)

        listView = view.findViewById(R.id.lv_userDashboard_charge_List)

        ivRefresh = view.findViewById(R.id.iv_userDashboard_charge_refresh)
        ivRefresh!!.setOnClickListener(this)
    }

    override fun showRefreshButton()
    {
        visibleRefreshButton()
    }

    override fun showChargeList(items: List<Charge>)
    {
        visibleListView()

        val adapter : UserChargeLIstAdapter = UserChargeLIstAdapter(context!! , items)
        listView!!.adapter = adapter
    }

    private fun visibleListView()
    {
        listView!!.visibility = View.VISIBLE
        ivRefresh!!.visibility = View.GONE
    }

    private fun visibleRefreshButton()
    {
        listView!!.visibility = View.GONE
        ivRefresh!!.visibility = View.VISIBLE
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            ivRefresh ->
            {
                presenter!!.getChargeList()
            }
        }
    }
}