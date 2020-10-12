package ir.sinasoheili.building_manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import ir.sinasoheili.building_manager.PRESENTER.ManagerAuthFilePreferenceHandler
import ir.sinasoheili.building_manager.VIEW.ManagerBuildingListActivity
import ir.sinasoheili.building_manager.VIEW.FragmentSetRoleManager
import ir.sinasoheili.building_manager.VIEW.FragmentSetRoleUser

class MainActivity : AppCompatActivity() , View.OnClickListener
{
    private var btn_manager:Button? = null
    private var btn_user:Button? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isManagerRegistered())
        {
            goToBuildingListActivity()
        }
        else
        {
            initObj()
        }
    }

    override fun onStop()
    {
        super.onStop()
        finish()
    }

    private fun initObj()
    {
        btn_manager = findViewById(R.id.btn_setRole_manager)
        btn_manager?.setOnClickListener(this)

        btn_user = findViewById(R.id.btn_setRole_user)
        btn_user?.setOnClickListener(this)

    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            btn_manager->
            {
                val managerFragment:FragmentSetRoleManager = FragmentSetRoleManager()
                supportFragmentManager.beginTransaction().replace(R.id.fl_setRole , managerFragment).addToBackStack(null).commit()
            }

            btn_user->
            {
                val userFragment:FragmentSetRoleUser = FragmentSetRoleUser()
                supportFragmentManager.beginTransaction().replace(R.id.fl_setRole , userFragment).addToBackStack(null).commit()
            }
        }
    }

    fun isManagerRegistered():Boolean
    {
        return ManagerAuthFilePreferenceHandler.containKey(this@MainActivity , ManagerAuthFilePreferenceHandler.KEY_MANAGER_ID)
    }

    fun goToBuildingListActivity()
    {
        val intent:Intent = Intent(this@MainActivity , ManagerBuildingListActivity::class.java)
        startActivity(intent)
        finish()
    }
}