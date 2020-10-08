package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.sinasoheili.building_manager.R

class ManagerRepairActivity : AppCompatActivity(), View.OnClickListener
{
    private var fabAddRepair : FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_repair)

        initObj()
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
                val fragmentRepair : FragmentRegisterNewRepair = FragmentRegisterNewRepair()
                supportFragmentManager.beginTransaction().replace(R.id.fl_managerRepair , fragmentRepair).addToBackStack(null).commit()
            }
        }
    }
}