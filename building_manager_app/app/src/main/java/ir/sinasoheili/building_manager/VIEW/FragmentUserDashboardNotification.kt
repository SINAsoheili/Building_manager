package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardNotification.ContractUserDashboardNotificationView
import ir.sinasoheili.building_manager.PRESENTER.PresenterUserDashboardNotification
import ir.sinasoheili.building_manager.R

class FragmentUserDashboardNotification : Fragment(R.layout.user_dashboard_notification_fragment) , ContractUserDashboardNotificationView , View.OnClickListener
{
    private var listView : ListView? = null
    private var ivRefresh : ImageView? = null
    private var progressBar : ProgressBar? = null
    private var tvEmptyList : TextView? = null

    private var presenter : PresenterUserDashboardNotification? = null
    private var isFragmentEnable : Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        visibleProgressBar()
        presenter!!.getNotificationList()
    }

    private fun initObj(view:View)
    {
        isFragmentEnable = true

        presenter = PresenterUserDashboardNotification(view.context , this)

        listView = view.findViewById(R.id.lv_userDashboard_notification_List)

        ivRefresh = view.findViewById(R.id.iv_userDashboard_notification_refresh)
        ivRefresh!!.setOnClickListener(this)

        progressBar = view.findViewById(R.id.pb_userDashboard_notification_progressBar)

        tvEmptyList = view.findViewById(R.id.tv_userDashboard_notification_emptyList)
    }

    override fun showNotificationList(items: List<Notification>)
    {
        if(isFragmentEnable) {
            visibleListView()
            invisibleProgressBar()
            invisibleRefreshButton()
            invisibleEmptyAlert()

            val adapter : UserNotificationListAdapter = UserNotificationListAdapter(context!! , items)
            listView!!.adapter = adapter
        }
    }

    override fun showEmptyAlert()
    {
        if(isFragmentEnable) {
            invisibleListView()
            invisibleRefreshButton()
            invisibleProgressBar()
            visibleEmptyAlert()
        }
    }

    override fun showToast(text: String)
    {
        if(isFragmentEnable) {
            invisibleProgressBar()
            visibleRefreshButton()
            invisibleListView()
            invisibleEmptyAlert()
            Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
        }
    }

    private fun visibleListView()
    {
        if(isFragmentEnable)
            listView!!.visibility = View.VISIBLE
    }

    private fun invisibleListView()
    {
        if(isFragmentEnable)
            listView!!.visibility = View.GONE
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
                presenter!!.getNotificationList()
            }
        }
    }

    private fun visibleProgressBar()
    {
        if(isFragmentEnable)
            progressBar?.visibility = View.VISIBLE
    }

    private fun invisibleProgressBar()
    {
        if(isFragmentEnable)
            progressBar?.visibility = View.GONE
    }

    private fun visibleRefreshButton()
    {
        if(isFragmentEnable)
            ivRefresh!!.visibility = View.VISIBLE
    }

    private fun invisibleRefreshButton()
    {
        if(isFragmentEnable)
            ivRefresh!!.visibility = View.GONE
    }

    private fun visibleEmptyAlert()
    {
        if(isFragmentEnable)
            tvEmptyList?.visibility = View.VISIBLE
    }

    private fun invisibleEmptyAlert()
    {
        if(isFragmentEnable)
            tvEmptyList?.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        isFragmentEnable = false
    }
}