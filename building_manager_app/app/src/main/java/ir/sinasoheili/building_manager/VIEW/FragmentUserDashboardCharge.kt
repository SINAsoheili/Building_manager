package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardCharge.ContractUserDashboardChargeView
import ir.sinasoheili.building_manager.PRESENTER.PresenterUserDashboardCharge

class FragmentUserDashboardCharge : Fragment(R.layout.user_dashboard_charge_fragment) , ContractUserDashboardChargeView, View.OnClickListener
{
    private var listView : ListView? = null
    private var ivRefresh : ImageView? = null
    private var progressBar : ProgressBar? = null
    private var tvEmptyList : TextView? = null

    private var presenter : PresenterUserDashboardCharge? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        visibleProgressBar()
        presenter!!.getChargeList()
    }

    private fun initObj(view:View)
    {
        presenter = PresenterUserDashboardCharge(view.context , this)

        listView = view.findViewById(R.id.lv_userDashboard_charge_List)

        ivRefresh = view.findViewById(R.id.iv_userDashboard_charge_refresh)
        ivRefresh!!.setOnClickListener(this)

        progressBar = view.findViewById(R.id.pb_userDashboard_charge_progressBar)
    }

    override fun showToast(text: String)
    {
        invisibleListView()
        invisibleProgressBar()
        visibleRefreshButton()
        invisibleEmptyListAlert()
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun showChargeList(items: List<Charge>)
    {
        visibleListView()
        invisibleRefreshButton()
        invisibleProgressBar()
        invisibleEmptyListAlert()

        val adapter : UserChargeLIstAdapter = UserChargeLIstAdapter(context!! , items)
        listView!!.adapter = adapter
    }

    override fun showEmptyListAlert()
    {
        invisibleListView()
        invisibleRefreshButton()
        invisibleProgressBar()
        visibleEmptyListAlert()
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            ivRefresh ->
            {
                invisibleListView()
                invisibleRefreshButton()
                visibleProgressBar()
                presenter!!.getChargeList()
            }
        }
    }

    private fun visibleProgressBar()
    {
        progressBar?.visibility = View.VISIBLE
    }

    private fun invisibleProgressBar()
    {
        progressBar?.visibility = View.GONE
    }

    private fun visibleRefreshButton()
    {
        ivRefresh?.visibility = View.VISIBLE
    }

    private fun invisibleRefreshButton()
    {
        ivRefresh?.visibility = View.GONE
    }

    private fun visibleListView()
    {
        listView?.visibility = View.VISIBLE
    }

    private fun invisibleListView()
    {
        listView?.visibility = View.GONE
    }

    private fun visibleEmptyListAlert()
    {
        tvEmptyList?.visibility = View.VISIBLE
    }

    private fun invisibleEmptyListAlert()
    {
        tvEmptyList?.visibility = View.GONE
    }
}