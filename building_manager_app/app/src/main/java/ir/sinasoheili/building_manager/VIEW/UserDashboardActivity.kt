package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import ir.sinasoheili.building_manager.R

class UserDashboardActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener
{
    private lateinit var bnv : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard)

        initObj()
    }

    private fun initObj()
    {
        bnv = findViewById(R.id.bnv_userDashboard)
        bnv.setOnNavigationItemSelectedListener(this)
        bnv.selectedItemId = R.id.bnv_item_profile
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.bnv_item_receipt ->
            {
                val fragment : FragmentUserDashboardReceipt = FragmentUserDashboardReceipt()
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_userDashboard , fragment).commit()
                return true
            }

            R.id.bnv_item_repair ->
            {
                val fragment : FragmentUserDashboardRepair = FragmentUserDashboardRepair()
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_userDashboard , fragment).commit()
                return true
            }

            R.id.bnv_item_profile ->
            {
                val fragment : FragmentUserDashboardProfile = FragmentUserDashboardProfile()
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_userDashboard , fragment).commit()
                return true
            }

            R.id.bnv_item_notification ->
            {
                val fragment : FragmentUserDashboardNotification = FragmentUserDashboardNotification()
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_userDashboard , fragment).commit()
                return true
            }

            R.id.bnv_item_charge ->
            {
                val fragment : FragmentUserDashboardCharge = FragmentUserDashboardCharge()
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_userDashboard , fragment).commit()
                return true
            }

        }

        return false
    }
}