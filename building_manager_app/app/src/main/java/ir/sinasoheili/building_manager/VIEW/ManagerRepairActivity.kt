package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.VIEW.FragmentManagerRegisterNewRepair.CallBack
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRepair.ContractManagerRepairView
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerRepair

class ManagerRepairActivity : AppCompatActivity() , ContractManagerRepairView , View.OnClickListener
{
    private var fabAddRepair : FloatingActionButton? = null
    private var listView : ListView? = null
    private var ivRefresh : ImageView? =  null
    private var tvEmptyRepairList : TextView? = null
    private var progressBar : ProgressBar? = null

    private var buildingId : Int = -1
    private var presenter : PresenterManagerRepair? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_repair)

        val bundle : Bundle? = intent.extras
        if(bundle != null)
        {
            buildingId = bundle.getInt("buildingId")
        }

        initObj()

        visibleProgressBar()
        presenter!!.getRepairList(buildingId)
    }

    private fun initObj()
    {
        presenter = PresenterManagerRepair(this , this)

        fabAddRepair = findViewById(R.id.fab_managerRepair_addRepair)
        fabAddRepair!!.setOnClickListener(this)

        listView = findViewById(R.id.lv_managerRepair_repairList)

        ivRefresh = findViewById(R.id.iv_managerRepair_refresh)
        ivRefresh!!.setOnClickListener(this)

        progressBar = findViewById(R.id.pb_managerRepair_progressBar)

        tvEmptyRepairList = findViewById(R.id.tv_managerRepair_repairListEmpty)
        tvEmptyRepairList!!.setOnClickListener(this)

    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            fabAddRepair->
            {
                showRegisterNewRepairFragment()
            }

            ivRefresh ->
            {
                invisibleRefreshButton()
                visibleProgressBar()

                presenter!!.getRepairList(buildingId)
            }
        }
    }

    override fun showRefreshButton()
    {
        visibleRefreshButton()
        invisibleRepairList()
        invisibleTextViewEmptyRepair()
        invisibleProgressBar()
    }

    override fun showEmptyListAlert()
    {
        invisibleRepairList()
        invisibleRefreshButton()
        invisibleProgressBar()
        visibleTextViewEmptyRepair()
    }

    override fun showToast(text: String)
    {
        invisibleProgressBar()
        invisibleRepairList()
        invisibleProgressBar()
        visibleRefreshButton()

        Toast.makeText(this , text , Toast.LENGTH_SHORT).show()
    }

    override fun showList(items: List<Repair>)
    {
        visibleRepairList()
        invisibleRefreshButton()
        invisibleTextViewEmptyRepair()
        invisibleProgressBar()

        val reversedItem:List<Repair> = items.reversed()
        val adapterManager : ManagerRepairListAdapter = ManagerRepairListAdapter(this , reversedItem)
        listView!!.adapter = adapterManager

        listView!!.setOnItemClickListener(object:AdapterView.OnItemClickListener
        {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
            {
                showRepairInfoFragment(items.reversed().get(p2))
            }
        })
    }

    private fun showRepairInfoFragment(repair:Repair)
    {
        val fragment:FragmentManagerRepairInfo = FragmentManagerRepairInfo(repair , object:FragmentManagerRepairInfo.CallBack
        {
            override fun onRepirDeleted()
            {
                presenter?.getRepairList(buildingId)
            }

        })
        supportFragmentManager.beginTransaction().replace(R.id.fl_managerRepair , fragment).addToBackStack(null).commit()
    }

    private fun showRegisterNewRepairFragment()
    {
        val fragmentRepair : FragmentManagerRegisterNewRepair = FragmentManagerRegisterNewRepair(buildingId , object:CallBack
        {
            override fun onRepairRegistered()
            {
                visibleProgressBar()
                presenter!!.getRepairList(buildingId)
            }

        })
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in , android.R.anim.fade_out , android.R.anim.fade_in , android.R.anim.fade_out)
            .replace(R.id.fl_managerRepair , fragmentRepair)
            .addToBackStack(null)
            .commit()
    }

    private fun visibleRepairList()
    {
        listView!!.visibility = View.VISIBLE
    }

    private fun invisibleRepairList()
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

    private fun visibleTextViewEmptyRepair()
    {
        tvEmptyRepairList!!.visibility = View.VISIBLE
    }

    private fun invisibleTextViewEmptyRepair()
    {
        tvEmptyRepairList!!.visibility = View.GONE
    }

    private fun visibleProgressBar()
    {
        progressBar?.visibility = View.VISIBLE
    }

    private fun invisibleProgressBar()
    {
        progressBar?.visibility = View.GONE
    }
}