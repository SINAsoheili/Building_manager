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
    private lateinit var listView : ListView
    private lateinit var ivRefresh : ImageView
    private lateinit var progressBar : ProgressBar
    private lateinit var tvEmptyList : TextView

    private lateinit var presenter : PresenterUserDashboardCharge
    private var isFragmentEnable : Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        visibleProgressBar()
        presenter.getChargeList()
    }

    private fun initObj(view:View)
    {
        isFragmentEnable = true

        presenter = PresenterUserDashboardCharge(view.context , this)

        listView = view.findViewById(R.id.lv_userDashboard_charge_List)

        ivRefresh = view.findViewById(R.id.iv_userDashboard_charge_refresh)
        ivRefresh.setOnClickListener(this)

        progressBar = view.findViewById(R.id.pb_userDashboard_charge_progressBar)

        tvEmptyList = view.findViewById(R.id.tv_userDashboard_charge_emptyList)
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
        if(isFragmentEnable) {
            visibleListView()
            invisibleRefreshButton()
            invisibleProgressBar()
            invisibleEmptyListAlert()

            val adapter : UserChargeLIstAdapter = UserChargeLIstAdapter(context!! , items.reversed())
            listView.adapter = adapter
        }
    }

    override fun showEmptyListAlert()
    {
        if(isFragmentEnable) {
            invisibleListView()
            invisibleRefreshButton()
            invisibleProgressBar()
            visibleEmptyListAlert()
        }
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
                presenter.getChargeList()
            }
        }
    }

    private fun visibleProgressBar()
    {
        if(isFragmentEnable)
            progressBar.visibility = View.VISIBLE
    }

    private fun invisibleProgressBar()
    {
        if(isFragmentEnable)
            progressBar.visibility = View.GONE
    }

    private fun visibleRefreshButton()
    {
        if(isFragmentEnable)
            ivRefresh.visibility = View.VISIBLE
    }

    private fun invisibleRefreshButton()
    {
        if(isFragmentEnable)
            ivRefresh.visibility = View.GONE
    }

    private fun visibleListView()
    {
        if(isFragmentEnable)
            listView.visibility = View.VISIBLE
    }

    private fun invisibleListView()
    {
        if(isFragmentEnable)
            listView.visibility = View.GONE
    }

    private fun visibleEmptyListAlert()
    {
        if(isFragmentEnable)
            tvEmptyList.visibility = View.VISIBLE
    }

    private fun invisibleEmptyListAlert()
    {
        if(isFragmentEnable)
            tvEmptyList.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        isFragmentEnable = false
    }
}