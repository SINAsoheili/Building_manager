package ir.sinasoheili.building_manager.VIEW

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.PRESENTER.ContractUnitList
import ir.sinasoheili.building_manager.PRESENTER.PresenterUnitList
import ir.sinasoheili.building_manager.R

class ManagerUnitListActivity : AppCompatActivity() , ContractUnitList.ContractUnitListView , View.OnClickListener, Toolbar.OnMenuItemClickListener {
    private var listView : ListView? = null
    private var ivReload : ImageView? = null
    private var tvReload : TextView? = null
    private var bottomAppBar : BottomAppBar? = null
    private var floatBtnAdd : FloatingActionButton? = null
    private var menuItemNotification : MenuItem? = null
    private var menuItemRepair : MenuItem? = null
    private var menuItemReceipt : MenuItem? = null
    private var frameLayout : FrameLayout? = null

    private var buildingId : Int = -1
    private var presenter : PresenterUnitList = PresenterUnitList(this@ManagerUnitListActivity , this)
    private val UNIT_REQUEST_CODE : Int = 100 //this code use for start activity to show unit info

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_list)

        initObj()

        val bundle : Bundle? = intent.extras
        if(bundle == null)
        {
            visibleRefreshButton()
        }
        else
        {
            buildingId = bundle.getInt("building_id")
            presenter.getUnitList(buildingId)
        }
    }

    private fun initObj()
    {
        listView = findViewById(R.id.lv_unitList)

        ivReload = findViewById(R.id.iv_unitList_reload)
        ivReload!!.setOnClickListener(this)

        tvReload = findViewById(R.id.tv_unitList_refresh)

        bottomAppBar = findViewById(R.id.bab_unitList)
        bottomAppBar!!.setOnMenuItemClickListener(this)

        floatBtnAdd = findViewById(R.id.fab_unitList)
        floatBtnAdd!!.setOnClickListener(this)

        frameLayout = findViewById(R.id.fl_unitList)
    }

    override fun visibleRefreshButton()
    {
        listView!!.visibility = View.GONE
        ivReload!!.visibility = View.VISIBLE
        tvReload!!.visibility = View.VISIBLE
    }

    private fun visibleListView()
    {
        listView!!.visibility = View.VISIBLE
        ivReload!!.visibility = View.GONE
        tvReload!!.visibility = View.GONE
    }

    override fun showEmptyListText()
    {
        tvReload!!.visibility = View.VISIBLE
        tvReload!!.text = this@ManagerUnitListActivity.getString(R.string.empty_unit_list)
    }

    override fun showUnitList(list: List<Unit>)
    {
        visibleListView()

        val adapterManager : ManagerUnitListAdapter = ManagerUnitListAdapter(this@ManagerUnitListActivity , list)
        listView!!.adapter = adapterManager

        listView!!.setOnItemClickListener(object:AdapterView.OnItemClickListener
        {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
            {
                val intent : Intent = Intent(this@ManagerUnitListActivity , ManagerUnitInfoActivity::class.java)
                intent.putExtra("unit" , list.get(p2))
                startActivityForResult(intent , UNIT_REQUEST_CODE)
            }
        })
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            ivReload ->
            {
                presenter.getUnitList(buildingId)
            }

            floatBtnAdd ->
            {
                val fragment : FragmentRegitsterNewUnit = FragmentRegitsterNewUnit(buildingId , object: FragmentRegitsterNewUnit.CallBack
                {
                    override fun updateUnitList()
                    {
                        presenter.getUnitList(buildingId)
                    }
                })
                supportFragmentManager.beginTransaction().replace(R.id.fl_unitList , fragment).addToBackStack(null).commit()
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
                startActivity(intent)
                return true
            }

            R.id.menu_bab_unitList_addReceipt ->
            {
                val intent : Intent = Intent(this@ManagerUnitListActivity , ManagerReceiptActivity::class.java)
                intent.putExtra("buildingId" , buildingId)
                startActivity(intent)
                return true
            }

            R.id.menu_bab_unitList_addRepair ->
            {
                val intent : Intent = Intent(this@ManagerUnitListActivity , ManagerRepairActivity::class.java)
                intent.putExtra("buildingId" , buildingId)
                startActivity(intent)
                return true
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
                Log.i("tag" , "fetch list")
                presenter.getUnitList(buildingId)
            }
        }
    }
}