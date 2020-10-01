package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import ir.sinasoheili.building_manager.MODEL.Building
import ir.sinasoheili.building_manager.PRESENTER.ContractBuildingList
import ir.sinasoheili.building_manager.PRESENTER.PresenterBuildingList
import ir.sinasoheili.building_manager.R
import kotlinx.android.synthetic.main.activity_building_list.*

class BuildingListActivity : AppCompatActivity() , ContractBuildingList.ContractBuildingListView , View.OnClickListener
{
    private var listView : ListView? = null
    private var iv_reload : ImageView? = null
    private var tv_retry : TextView? = null

    private var presenter : PresenterBuildingList? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_building_list)

        initObj()

        presenter!!.getBuildingList()
    }

    private fun initObj()
    {
        listView = findViewById(R.id.lv_buildingList)

        iv_reload = findViewById(R.id.iv_buildingList_reload)
        iv_reload!!.setOnClickListener(this)

        tv_retry = findViewById(R.id.tv_buildingList_retry)

        presenter = PresenterBuildingList(this@BuildingListActivity , this)
    }

    override fun showReloadButton()
    {
        invisibleList()
    }

    override fun showBuildingList(buildingList: List<Building>)
    {
        visibleList()

        val adapter : AdapterBuildingList = AdapterBuildingList(this@BuildingListActivity , buildingList)
        lv_buildingList.adapter = adapter

        listView!!.setOnItemClickListener(object : AdapterView.OnItemClickListener
        {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
            {
                Toast.makeText(this@BuildingListActivity , buildingList.get(p2).toString() , Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onClick(view : View?)
    {
        when(view)
        {
            iv_reload ->
            {
                invisibleList()

                presenter!!.getBuildingList()
            }
        }
    }

    private fun invisibleList()
    {
        listView!!.visibility  = View.GONE
        iv_reload!!.visibility = View.VISIBLE
        tv_retry!!.visibility  = View.VISIBLE
    }

    private fun visibleList()
    {
        listView!!.visibility  = View.VISIBLE
        iv_reload!!.visibility = View.GONE
        tv_retry!!.visibility  = View.GONE
    }
}