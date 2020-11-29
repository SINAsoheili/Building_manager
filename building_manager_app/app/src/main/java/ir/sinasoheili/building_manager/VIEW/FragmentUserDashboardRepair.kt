package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardRepair.ContractUserDashboardRepairView
import ir.sinasoheili.building_manager.PRESENTER.PresenterUserDashboardRepair

class FragmentUserDashboardRepair : Fragment(R.layout.user_dashboard_repair_fragment) , ContractUserDashboardRepairView, View.OnClickListener
{
    private var listView : ListView? = null
    private var ivRefresh : ImageView? = null
    private var progressBar:ProgressBar? = null
    private var tvEmptyList : TextView? = null

    private var presenter : PresenterUserDashboardRepair? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        visibleProgressBar()
        presenter!!.getRepairList()
    }

    private fun initObj(view:View)
    {
        presenter = PresenterUserDashboardRepair(view.context , this)

        listView = view.findViewById(R.id.lv_userDashboard_repair_List)

        ivRefresh = view.findViewById(R.id.iv_userDashboard_repair_refresh)
        ivRefresh!!.setOnClickListener(this)

        progressBar = view.findViewById(R.id.pb_userDashboard_repair_progressBar)

        tvEmptyList = view.findViewById(R.id.tv_userDashboard_repair_emptyRepair)
    }

    override fun showToast(text: String)
    {
        invisibleListView()
        invisibleProgressBar()
        visibleRefreshButton()
        invisibleEmptyListAlert()
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun showRepairList(items: List<Repair>)
    {
        visibleListView()
        invisibleRefreshButton()
        invisibleProgressBar()
        invisibleEmptyListAlert()

        val adapter : UserRepairListAdapter = UserRepairListAdapter(context!! , items)
        listView!!.adapter = adapter
    }

    override fun showEmptyListAlert()
    {
        invisibleListView()
        invisibleProgressBar()
        invisibleRefreshButton()
        visibleEmptyListAlert()
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            ivRefresh ->
            {
                invisibleRefreshButton()
                visibleProgressBar()
                invisibleListView()
                presenter!!.getRepairList()
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