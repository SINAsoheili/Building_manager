package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerNotification
import ir.sinasoheili.building_manager.R

class ManagerNotifiactionActivity : AppCompatActivity() , ContractManagerNotification.ContractManagerNotificationView, View.OnClickListener
{
    private var fabAddNotification : FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_notifiaction)

        initObj()
    }

    private fun initObj()
    {
        fabAddNotification = findViewById(R.id.fab_managerNotification_AddNotification)
        fabAddNotification!!.setOnClickListener(this)
    }

    override fun onClick(p0: View?)
    {
        Toast.makeText(this@ManagerNotifiactionActivity , "add" , Toast.LENGTH_SHORT).show()
    }
}