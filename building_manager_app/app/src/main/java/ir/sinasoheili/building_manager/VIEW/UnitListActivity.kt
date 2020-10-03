package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.PRESENTER.ContractUnitList
import ir.sinasoheili.building_manager.PRESENTER.PresenterUnitList
import ir.sinasoheili.building_manager.R

class UnitListActivity : AppCompatActivity() , ContractUnitList.ContractUnitListView , View.OnClickListener
{
    private var listView : ListView? = null
    private var ivReload : ImageView? = null
    private var tvReload : TextView? = null

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

        val adapter : ArrayAdapter<Unit> = ArrayAdapter(this@UnitListActivity , android.R.layout.simple_list_item_1 , list)
        listView!!.adapter = adapter
    }

    override fun onClick(view: View?)
    {
        if(view!!.equals(ivReload))
        {
            presenter.getUnitList(buildingId)
        }
    }
}