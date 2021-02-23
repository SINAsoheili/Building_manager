package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import ir.sinasoheili.building_manager.VIEW.FragmentManagerRegisterNewNotification.CallBack
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerNotification
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerNotification
import ir.sinasoheili.building_manager.R

class ManagerNotificationActivity : AppCompatActivity() , ContractManagerNotification.ContractManagerNotificationView, View.OnClickListener
{
    private var fabAddNotification : FloatingActionButton? = null
    private var listView : ListView? = null
    private var ivRefresh : ImageView? = null
    private var tvEmptyListAlert : TextView? = null
    private var progressBar : ProgressBar? = null

    private var buildingId : Int = -1
    private var presenter : PresenterManagerNotification? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_notifiaction)

        val bundle : Bundle? = intent.extras
        if(bundle != null)
        {
            buildingId = bundle.getInt("buildingId")
        }

        initObj()
        visibleProgressBar()
        presenter!!.fetchNotificationList(buildingId)
    }

    private fun initObj()
    {
        presenter = PresenterManagerNotification(this@ManagerNotificationActivity , this)

        fabAddNotification = findViewById(R.id.fab_managerNotification_AddNotification)
        fabAddNotification!!.setOnClickListener(this)

        listView = findViewById(R.id.lv_managerNotification_notifList)

        ivRefresh = findViewById(R.id.iv_managerNotification_refresh)
        ivRefresh!!.setOnClickListener(this)

        progressBar = findViewById(R.id.pb_managerNotification_progressBar)

        tvEmptyListAlert = findViewById(R.id.tv_managerNotification_emptyList)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            fabAddNotification ->
            {
                val fragment : FragmentManagerRegisterNewNotification = FragmentManagerRegisterNewNotification(buildingId , object:CallBack
                {
                    override fun onNotificationRegistered()
                    {
                        visibleProgressBar()
                        presenter!!.fetchNotificationList(buildingId)
                    }

                })
                supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in , android.R.anim.fade_out , android.R.anim.fade_in , android.R.anim.fade_out)
                    .add(R.id.fl_managerNotification , fragment)
                    .addToBackStack(null)
                    .commit()
            }

            ivRefresh ->
            {
                invisibleRefreshButton()
                visibleProgressBar()
                invisibleListView()
                presenter!!.fetchNotificationList(buildingId)
            }
        }
    }

    override fun showRefreshButton()
    {
        invisibleListView()
        invisibleTextViewEmptyListAlert()
        invisibleProgressBar()
        visibleRefreshButton()
    }

    override fun showEmptyListAlert()
    {
        invisibleListView()
        invisibleRefreshButton()
        invisibleProgressBar()
        visibleTextViewEmptyListAlert()
    }

    override fun showToast(text: String)
    {
        visibleRefreshButton()
        invisibleProgressBar()
        invisibleListView()
        invisibleRefreshButton()

        Toast.makeText(this@ManagerNotificationActivity , text , Toast.LENGTH_SHORT).show()
    }

    override fun showList(items: List<Notification>)
    {
        visibleListView()
        invisibleRefreshButton()
        invisibleTextViewEmptyListAlert()
        invisibleProgressBar()

        val adapter : ManagerNotificationListAdapter = ManagerNotificationListAdapter(this@ManagerNotificationActivity , items.reversed())
        listView!!.adapter = adapter

        listView!!.setOnItemClickListener(object:AdapterView.OnItemClickListener
        {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
            {
                showNotificationFragment(items.get(p2))
            }
        })
    }

    fun showNotificationFragment(notification: Notification)
    {
        val fragment:FragmentManagerNotificationInfo = FragmentManagerNotificationInfo(notification , object:FragmentManagerNotificationInfo.CallBack
        {
            override fun onNotificationDeleted()
            {
                presenter?.fetchNotificationList(buildingId)
            }

        })
        supportFragmentManager.beginTransaction().replace(R.id.fl_managerNotification , fragment).addToBackStack(null).commit()
    }

    private fun visibleListView()
    {
        listView!!.visibility = View.VISIBLE
    }

    private fun invisibleListView()
    {
        listView!!.visibility = View.GONE
    }

    private fun visibleRefreshButton()
    {
        ivRefresh!!.visibility = View.VISIBLE
    }

    private fun invisibleRefreshButton()
    {
        ivRefresh!!.visibility = View.GONE
    }

    private fun visibleTextViewEmptyListAlert()
    {
        tvEmptyListAlert!!.visibility = View.VISIBLE
    }

    private fun invisibleTextViewEmptyListAlert()
    {
        tvEmptyListAlert!!.visibility = View.GONE
    }

    private fun visibleProgressBar()
    {
        progressBar!!.visibility = View.VISIBLE
    }

    private fun invisibleProgressBar()
    {
        progressBar!!.visibility = View.GONE
    }
}