package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardReceipt.ContractUserDashboardReceiptView
import ir.sinasoheili.building_manager.PRESENTER.PresenterUserDashboardReceipt

class FragmentUserDashboardReceipt : Fragment(R.layout.user_dashboard_receipt_fragment) , ContractUserDashboardReceiptView, View.OnClickListener
{
    private var listView : ListView? = null
    private var ivRefresh : ImageView? = null

    private var presenter : PresenterUserDashboardReceipt? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        presenter!!.getReceiptList()
    }

    private fun initObj(view:View)
    {
        presenter = PresenterUserDashboardReceipt(view.context , this)

        listView = view.findViewById(R.id.lv_userDashboard_receipt_List)

        ivRefresh = view.findViewById(R.id.iv_userDashboard_receipt_refresh)
        ivRefresh!!.setOnClickListener(this)
    }

    override fun showRefreshButton()
    {
        visibleRefreshButton()
    }

    override fun showReceiptList(items: List<Receipt>)
    {
        visibleListView()

         val adapter : UserReceiptListAdapter = UserReceiptListAdapter(context!! , items)
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
                presenter!!.getReceiptList()
            }
        }
    }
}