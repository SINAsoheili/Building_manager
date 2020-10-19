package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboard.ContractUserDashboardView
import ir.sinasoheili.building_manager.PRESENTER.PresenterUserDashboard

class UserDashboardActivity : AppCompatActivity() , ContractUserDashboardView, BottomNavigationView.OnNavigationItemSelectedListener
{
    private var tvOwnerName : TextView? = null
    private var tvPhone : TextView? = null
    private var tvUnitNumber : TextView? = null
    private var tvTag : TextView? = null
    private var bnv : BottomNavigationView? = null

    private var presenter : PresenterUserDashboard? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard)

        initObj()
        presenter!!.getUnitInfo()
    }

    private fun initObj()
    {
        presenter = PresenterUserDashboard(this@UserDashboardActivity , this)

        bnv = findViewById(R.id.bnv_userDashboard)
        bnv!!.setOnNavigationItemSelectedListener(this)

        tvOwnerName = findViewById(R.id.tv_userDashboard_ownerName)
        tvPhone = findViewById(R.id.tv_userDashboard_phone)
        tvUnitNumber = findViewById(R.id.tv_userDashboard_unitNumber)
        tvTag = findViewById(R.id.tv_userDashboard_tag)
    }

    override fun showToast(text: String)
    {
        Toast.makeText(this@UserDashboardActivity , text , Toast.LENGTH_SHORT).show()
    }

    override fun showUnitInfo(unit: Unit)
    {
        tvOwnerName!!.text = unit.owner_name
        tvPhone!!.text = unit.phone
        tvUnitNumber!!.text = unit.unit_number.toString()
        tvTag!!.text = unit.tag.toString()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.bnv_item_receipt ->
            {
                Toast.makeText(this@UserDashboardActivity , "receipt" , Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.bnv_item_repair ->
            {
                Toast.makeText(this@UserDashboardActivity , "repair" , Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.bnv_item_profile ->
            {
                Toast.makeText(this@UserDashboardActivity , "profile" , Toast.LENGTH_SHORT).show()
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