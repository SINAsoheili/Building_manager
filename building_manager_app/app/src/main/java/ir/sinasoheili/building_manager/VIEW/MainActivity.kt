package ir.sinasoheili.building_manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import ir.sinasoheili.building_manager.PRESENTER.UserAuthFilePreferenceHandler
import ir.sinasoheili.building_manager.VIEW.*

class MainActivity : AppCompatActivity() , View.OnClickListener
{
    private lateinit var btn_manager:Button
    private lateinit var btn_user:Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when
        {
            ! isIntroSliderVisited() -> {
                startActivity(Intent(this , IntroSliderActivity::class.java))
                finish()
            }
            isManagerRegistered()->
            {
                goToBuildingListActivity()
            }
            isUserRegistered()->
            {
                goToUserDashboard()
            }
            else ->
            {
                initObj()
            }
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
        btn_manager.setOnClickListener(this)

        btn_user = findViewById(R.id.btn_setRole_user)
        btn_user.setOnClickListener(this)
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
        return UserAuthFilePreferenceHandler.containKey(this@MainActivity , UserAuthFilePreferenceHandler.KEY_MANAGER_ID)
    }

    fun isUserRegistered():Boolean
    {
        if(UserAuthFilePreferenceHandler.containKey(this@MainActivity , UserAuthFilePreferenceHandler.KEY_USER_ID_UnitNumber) &&
            UserAuthFilePreferenceHandler.containKey(this@MainActivity , UserAuthFilePreferenceHandler.KEY_USER_ID_BuildignId)
        )
        {
            return true
        }

        return false
    }

    fun goToBuildingListActivity()
    {
        val intent:Intent = Intent(this@MainActivity , ManagerBuildingListActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToUserDashboard()
    {
        val intent:Intent = Intent(this@MainActivity , UserDashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isIntroSliderVisited() : Boolean {
        if (! UserAuthFilePreferenceHandler.containKey(this , UserAuthFilePreferenceHandler.KEY_INTRO_VISIT_STATUS)) {
           return false
        } else {
            val status:String? = UserAuthFilePreferenceHandler.readFromFile(this , UserAuthFilePreferenceHandler.KEY_INTRO_VISIT_STATUS)
            if (status.equals(UserAuthFilePreferenceHandler.KEY_INTRO_VISITED))
                return true
        }

        return false
    }
}