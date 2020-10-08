package ir.sinasoheili.building_manager.VIEW

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class UnitListActivity : AppCompatActivity() , ContractUnitList.ContractUnitListView , View.OnClickListener, Toolbar.OnMenuItemClickListener {
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
    private var presenter : PresenterUnitList = PresenterUnitList(this@UnitListActivity , this)

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
        tvReload!!.text = this@UnitListActivity.getString(R.string.empty_unit_list)
    }

    override fun showUnitList(list: List<Unit>)
    {
        visibleListView()

        val adapter : UnitListAdapter = UnitListAdapter(this@UnitListActivity , list)
        listView!!.adapter = adapter

        listView!!.setOnItemClickListener(object:AdapterView.OnItemClickListener
        {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
            {
                val intent : Intent = Intent(this@UnitListActivity , UnitInfoActivity::class.java)
                intent.putExtra("unit" , list.get(p2))
                startActivity(intent)
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
                val intent : Intent = Intent(this@UnitListActivity , ManagerNotificationActivity::class.java)
                intent.putExtra("buildingId" , buildingId)
                startActivity(intent)
                return true
            }

            R.id.menu_bab_unitList_addReceipt ->
            {
                startActivity(Intent(this@UnitListActivity , ManagerReceiptActivity::class.java))
                return true
            }

            R.id.menu_bab_unitList_addRepair ->
            {
                startActivity(Intent(this@UnitListActivity , ManagerRepairActivity::class.java))
                return true
            }
        }

        return false
    }
}