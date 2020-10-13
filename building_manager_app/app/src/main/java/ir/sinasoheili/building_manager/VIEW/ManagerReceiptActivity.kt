package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.VIEW.FragmentManagerRegisterNewReceipt.CallBack
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerReceipt.ContractManagerReceiptView
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerReceipt
import kotlinx.android.synthetic.main.fragment_setrole_manager.*

class ManagerReceiptActivity : AppCompatActivity() , ContractManagerReceiptView , View.OnClickListener
{
    private var fabRegisterReceipt : FloatingActionButton? = null
    private var listView : ListView? = null

    private var buildingId : Int = -1
    private var presenter : PresenterManagerReceipt? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_receipt)

        val bundle : Bundle? = intent.extras
        if(bundle != null)
        {
            buildingId = bundle.getInt("buildingId")
        }

        initObj()

        presenter!!.fetchReceiptList(buildingId)
    }

    private fun initObj()
    {
        presenter = PresenterManagerReceipt(this , this)

        fabRegisterReceipt = findViewById(R.id.fab_managerReceipt_AddReceipt)
        fabRegisterReceipt!!.setOnClickListener(this)

        listView = findViewById(R.id.lv_managerReceipt_receiptList)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            fabRegisterReceipt ->
            {
                val fragment : FragmentManagerRegisterNewReceipt = FragmentManagerRegisterNewReceipt(buildingId , object:CallBack
                {
                    override fun onRegisteredReceipt()
                    {
                        presenter!!.fetchReceiptList(buildingId)
                    }

                })
                supportFragmentManager.beginTransaction().add(R.id.fl_managerReceipt , fragment).addToBackStack(null).commit()
            }
        }
    }

    override fun showToast(text: String)
    {
        Toast.makeText(this , text , Toast.LENGTH_SHORT).show()
    }

    override fun showList(items: List<Receipt>)
    {
        val reversedItems : List<Receipt> = items.reversed()
        val adapter : ManagerReceiptListAdapter = ManagerReceiptListAdapter(this , reversedItems)
        listView!!.adapter = adapter
        listView!!.setOnItemClickListener(object:AdapterView.OnItemClickListener
        {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
            {
                showFragmentReceiptInfo(items.reversed().get(p2))
            }

        })
    }

    private fun showFragmentReceiptInfo(receipt:Receipt)
    {
        val fragment:FragmentManagerReceiptInfo = FragmentManagerReceiptInfo(receipt , object:FragmentManagerReceiptInfo.CallBack
        {
            override fun onReceiptDeleted()
            {
                presenter!!.fetchReceiptList(buildingId)
            }

        })
        supportFragmentManager.beginTransaction().replace(R.id.fl_managerReceipt , fragment).addToBackStack(null).commit()
    }
}