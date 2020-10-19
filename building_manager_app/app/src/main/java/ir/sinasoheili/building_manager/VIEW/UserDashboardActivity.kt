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
    private var bnv : BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard)

        initObj()
    }

    private fun initObj()
    {
        bnv = findViewById(R.id.bnv_userDashboard)
        bnv!!.setOnNavigationItemSelectedListener(this)
        bnv!!.selectedItemId = R.id.bnv_item_profile
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.bnv_item_receipt ->
            {
                var fragment : FragmentUserDashboardReceipt = FragmentUserDashboardReceipt()
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_userDashboard , fragment).commit()
                return true
            }

            R.id.bnv_item_repair ->
            {
                Toast.makeText(this@UserDashboardActivity , "repair" , Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.bnv_item_profile ->
            {
                var fragment : FragmentUserDashboardProfile = FragmentUserDashboardProfile()
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_userDashboard , fragment).commit()
                return true
            }

            R.id.bnv_item_notification ->
            {
                Toast.makeText(this@UserDashboardActivity , "notification" , Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.bnv_item_charge ->
            {
                Toast.makeText(this@UserDashboardActivity , "charge" , Toast.LENGTH_SHORT).show()
                return true
            }

        }

        return false
    }
}