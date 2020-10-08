package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.VIEW.FragmentRegisterNewRepair.CallBack

class ManagerRepairActivity : AppCompatActivity(), View.OnClickListener
{
    private var fabAddRepair : FloatingActionButton? = null
    private var buildingId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_repair)

        initObj()

        val bundle : Bundle? = intent.extras
        if(bundle != null)
        {
            buildingId = bundle.getInt("buildingId")
        }
    }

    private fun initObj()
    {
        fabAddRepair = findViewById(R.id.fab_managerRepair_addRepair)
        fabAddRepair!!.setOnClickListener(this)
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
                        //todo:update list of repair
                    }

                })
                supportFragmentManager.beginTransaction().replace(R.id.fl_managerRepair , fragmentRepair).addToBackStack(null).commit()
            }
        }
    }
}