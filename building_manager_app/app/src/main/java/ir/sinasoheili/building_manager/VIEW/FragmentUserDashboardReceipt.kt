package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardReceipt.ContractUserDashboardReceiptView
import ir.sinasoheili.building_manager.PRESENTER.PresenterUserDashboardReceipt

class FragmentUserDashboardReceipt : Fragment(R.layout.user_dashboard_receipt_fragment) , ContractUserDashboardReceiptView, View.OnClickListener
{
    private var listView : ListView? = null
    private var ivRefresh : ImageView? = null
    private var progressBar : ProgressBar? = null
    private var tvEmptyList : TextView? = null

    private var presenter : PresenterUserDashboardReceipt? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        visibleProgressBar()
        presenter!!.getReceiptList()
    }

    private fun initObj(view:View)
    {
        presenter = PresenterUserDashboardReceipt(view.context , this)

        listView = view.findViewById(R.id.lv_userDashboard_receipt_List)

        ivRefresh = view.findViewById(R.id.iv_userDashboard_receipt_refresh)
        ivRefresh!!.setOnClickListener(this)

        progressBar = view.findViewById(R.id.pb_userDashboard_receipt_progressBar)

        tvEmptyList = view.findViewById(R.id.tv_userDashboard_receipt_emptyReceipt)
    }

    override fun showToast(text: String)
    {
        visibleRefreshButton()
        invisibleProgressBar()
        invisibleListView()
        invisibleEmptyalert()
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun showReceiptList(items: List<Receipt>)
    {
        visibleListView()
        invisibleProgressBar()
        invisibleRefreshButton()
        invisibleEmptyalert()

        val adapter : UserReceiptListAdapter = UserReceiptListAdapter(context!! , items)
        listView!!.adapter = adapter
    }

    override fun showEmptyListAlert()
    {
        invisibleListView()
        invisibleProgressBar()
        invisibleRefreshButton()
        visibleEmptyalert()
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
                presenter!!.getReceiptList()
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

    private fun visibleEmptyalert()
    {
        tvEmptyList?.visibility = View.VISIBLE
    }

    private fun invisibleEmptyalert()
    {
        tvEmptyList?.visibility = View.GONE
    }
}