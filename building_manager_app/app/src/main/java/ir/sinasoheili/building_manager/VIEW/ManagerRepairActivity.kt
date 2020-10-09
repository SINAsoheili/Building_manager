package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.VIEW.FragmentRegisterNewRepair.CallBack
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRepair.ContractManagerRepairView
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerRepair

class ManagerRepairActivity : AppCompatActivity() , ContractManagerRepairView , View.OnClickListener
{
    private var fabAddRepair : FloatingActionButton? = null
    private var listView : ListView? = null

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

        presenter!!.getRepairList(buildingId)
    }

    private fun initObj()
    {
        presenter = PresenterManagerRepair(this , this)

        fabAddRepair = findViewById(R.id.fab_managerRepair_addRepair)
        fabAddRepair!!.setOnClickListener(this)

        listView = findViewById(R.id.lv_managerRepair_repairList)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            fabAddRepair->
            {
                val fragmentRepair : FragmentRegisterNewRepair = FragmentRegisterNewRepair(buildingId , object:CallBack
                {
                    override fun onRepairRegistered()
                    {
                        presenter!!.getRepairList(buildingId)
                    }

                })
                supportFragmentManager.beginTransaction().replace(R.id.fl_managerRepair , fragmentRepair).addToBackStack(null).commit()
            }
        }
    }

    override fun showToast(text: String)
    {
        Toast.makeText(this , text , Toast.LENGTH_SHORT).show()
    }

    override fun showList(items: List<Repair>)
    {
        val adapter : ArrayAdapter<Repair> = ArrayAdapter(this , android.R.layout.simple_list_item_1 , items)
        listView!!.adapter = adapter
    }
}