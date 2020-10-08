package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ir.sinasoheili.building_manager.VIEW.FragmentRegisterNewNotification.CallBack
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerNotification
import ir.sinasoheili.building_manager.R

class ManagerNotificationActivity : AppCompatActivity() , ContractManagerNotification.ContractManagerNotificationView, View.OnClickListener
{
    private var fabAddNotification : FloatingActionButton? = null
    private var buildingId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_notifiaction)

        initObj()

        val bundle : Bundle? = intent.extras
        if(bundle != null)
        {
            buildingId = bundle.getInt("buildingId")
        }
    }

    private fun initObj()
    {
        fabAddNotification = findViewById(R.id.fab_managerNotification_AddNotification)
        fabAddNotification!!.setOnClickListener(this)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            fabAddNotification ->
            {
                val fragment : FragmentRegisterNewNotification = FragmentRegisterNewNotification(buildingId , object:CallBack
                {
                    override fun onNotificationRegistered()
                    {
                        //todo:update list of notificaion
                    }

                })
                supportFragmentManager.beginTransaction().add(R.id.fl_managerNotification , fragment).addToBackStack(null).commit()
            }
        }
    }
}