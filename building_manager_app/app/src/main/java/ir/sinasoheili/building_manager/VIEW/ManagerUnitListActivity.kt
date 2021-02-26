package ir.sinasoheili.building_manager.VIEW

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityOptionsCompat
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerUnitList
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerUnitList
import ir.sinasoheili.building_manager.R

class ManagerUnitListActivity : AppCompatActivity()
    ,ContractManagerUnitList.ContractManagerUnitListView
    ,View.OnClickListener
    ,Toolbar.OnMenuItemClickListener
{
    private lateinit var listView : ListView
    private lateinit var ivReload : ImageView
    private lateinit var tvReload : TextView
    private lateinit var bottomAppBar : BottomAppBar
    private lateinit var floatBtnAdd : FloatingActionButton
    private lateinit var frameLayout : FrameLayout
    private lateinit var progressBar : ProgressBar
    private lateinit var actionBarTitle : TextView

    private var buildingId : Int = -1
    private var presenter : PresenterManagerUnitList = PresenterManagerUnitList(this@ManagerUnitListActivity , this)
    private val UNIT_REQUEST_CODE : Int = 100 //this code use for start activity to show unit info

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_list)

        initObj()

        val bundle : Bundle? = intent.extras
        if(bundle == null)
        {
            showRefreshButton()
        }
        else
        {
            visibleProgressBar()
            buildingId = bundle.getInt("building_id")
            actionBarTitle.text = getString(R.string.page_title_unit_list , bundle.getString("building_name"))
            presenter.getUnitList(buildingId)
        }
    }

    private fun initObj()
    {
        listView = findViewById(R.id.lv_unitList)

        ivReload = findViewById(R.id.iv_unitList_reload)
        ivReload.setOnClickListener(this)

        progressBar = findViewById(R.id.pb_unitList_prograssBar)

        tvReload = findViewById(R.id.tv_unitList_refresh)

        bottomAppBar = findViewById(R.id.bab_unitList)
        bottomAppBar.setOnMenuItemClickListener(this)

        floatBtnAdd = findViewById(R.id.fab_unitList)
        floatBtnAdd.setOnClickListener(this)

        frameLayout = findViewById(R.id.fl_unitList)

        actionBarTitle = findViewById(R.id.action_bar_unit_list)
    }

    override fun showRefreshButton()
    {
        invisibleUnitList()
        invisibleTextViewEmptyAlert()
        visibleRefreshButton()
    }

    private fun showListView()
    {
        invisibleRefreshButton()
        invisibleTextViewEmptyAlert()
        inVisibleProgressBar()
        visibleUnitList()
    }

    override fun showEmptyListText()
    {
        invisibleUnitList()
        invisibleRefreshButton()
        inVisibleProgressBar()
        visibleTextViewEmptyAlert()
    }

    override fun showUnitList(list: List<Unit>)
    {
        showListView()
        invisibleRefreshButton()
        inVisibleProgressBar()
        invisibleTextViewEmptyAlert()

        val adapterManager : ManagerUnitListAdapter = ManagerUnitListAdapter(this@ManagerUnitListActivity , list)
        listView.adapter = adapterManager

        listView.setOnItemClickListener(object:AdapterView.OnItemClickListener
        {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
            {
                val intent : Intent = Intent(this@ManagerUnitListActivity , ManagerUnitInfoActivity::class.java)
                intent.putExtra("unit" , list.get(p2))
                startActivityForResult(intent , UNIT_REQUEST_CODE)
            }
        })
    }

    override fun showToast(text: String)
    {
        inVisibleProgressBar()
        invisibleUnitList()
        inVisibleProgressBar()
        visibleRefreshButton()

        Toast.makeText(this , text , Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            ivReload ->
            {
                invisibleRefreshButton()
                visibleProgressBar()
                presenter.getUnitList(buildingId)
            }

            floatBtnAdd ->
            {
                val fragment : FragmentRegitsterNewUnitManager = FragmentRegitsterNewUnitManager(buildingId , object: FragmentRegitsterNewUnitManager.CallBack
                {
                    override fun updateUnitList()
                    {
                        presenter.getUnitList(buildingId)
                    }
                })
                supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in , android.R.anim.fade_out , android.R.anim.fade_in , android.R.anim.fade_out)
                    .replace(R.id.fl_unitList , fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean
    {
        when(item!!.itemId)
        {
            R.id.menu_bab_unitList_addNotification ->
            {
                val intent : Intent = Intent(this@ManagerUnitListActivity , ManagerNotificationActivity::class.java)
                intent.putExtra("buildingId" , buildingId)
                val option : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this , findViewById(R.id.fab_unitList) , "unit_list_FAB")
                startActivity(intent , option.toBundle())
                return true
            }

            R.id.menu_bab_unitList_addReceipt ->
            {
                val intent : Intent = Intent(this@ManagerUnitListActivity , ManagerReceiptActivity::class.java)
                intent.putExtra("buildingId" , buildingId)
                val option : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this , findViewById(R.id.fab_unitList) , "unit_list_FAB")
                startActivity(intent , option.toBundle())
                return true
            }

            R.id.menu_bab_unitList_addRepair ->
            {
                val intent : Intent = Intent(this@ManagerUnitListActivity , ManagerRepairActivity::class.java)
                intent.putExtra("buildingId" , buildingId)
                val option : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this , findViewById(R.id.fab_unitList) , "unit_list_FAB")
                startActivity(intent , option.toBundle())
                return true
            }

            R.id.menu_bab_unitList_building_info ->
            {
                val fragment : FragmentBuildingInfo = FragmentBuildingInfo(buildingId)
                supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in , android.R.anim.fade_out , android.R.anim.fade_in , android.R.anim.fade_out)
                    .replace(R.id.fl_unitList , fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == UNIT_REQUEST_CODE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                presenter.getUnitList(buildingId)
            }
        }
    }

    private fun visibleUnitList()
    {
        ivReload.visibility = View.GONE
        tvReload.visibility = View.GONE
        listView.visibility = View.VISIBLE
    }

    private fun invisibleUnitList()
    {
        listView.visibility = View.GONE
    }

    private fun visibleRefreshButton()
    {
        listView.visibility = View.GONE
        ivReload.visibility = View.VISIBLE
        tvReload.visibility = View.GONE
    }

    private fun invisibleRefreshButton()
    {
        ivReload.visibility = View.GONE
    }

    private fun visibleTextViewEmptyAlert()
    {
        listView.visibility = View.GONE
        ivReload.visibility = View.GONE
        tvReload.visibility = View.VISIBLE
    }

    private fun invisibleTextViewEmptyAlert()
    {
        tvReload.visibility = View.GONE
    }

    private fun visibleProgressBar()
    {
        progressBar.visibility = View.VISIBLE
    }

    private fun inVisibleProgressBar()
    {
        progressBar.visibility = View.GONE
    }
}