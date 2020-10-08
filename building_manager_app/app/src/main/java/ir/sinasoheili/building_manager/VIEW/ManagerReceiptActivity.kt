package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.sinasoheili.building_manager.R

class ManagerReceiptActivity : AppCompatActivity(), View.OnClickListener
{
    private var fabRegisterReceipt : FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_receipt)

        initObj()
    }

    private fun initObj()
    {
        fabRegisterReceipt = findViewById(R.id.fab_managerReceipt_AddReceipt)
        fabRegisterReceipt!!.setOnClickListener(this)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            fabRegisterReceipt ->
            {
                val fragment : FragmentRegisterNewReceipt = FragmentRegisterNewReceipt()
                supportFragmentManager.beginTransaction().add(R.id.fl_managerReceipt , fragment).addToBackStack(null).commit()
            }
        }
    }
}