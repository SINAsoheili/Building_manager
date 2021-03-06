package ir.sinasoheili.building_manager.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.VIEW.FragmentManagerRegisterNewReceipt.CallBack
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerReceipt.ContractManagerReceiptView
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerReceipt

class ManagerReceiptActivity : AppCompatActivity() , ContractManagerReceiptView , View.OnClickListener
{
    private lateinit var fabRegisterReceipt : FloatingActionButton
    private lateinit var listView : ListView
    private lateinit var ivRefresh : ImageView
    private lateinit var tvEmptyList : TextView
    private lateinit var progressBar : ProgressBar

    private var buildingId : Int = -1
    private lateinit var presenter : PresenterManagerReceipt

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
        visibleProgressBar()
        presenter.fetchReceiptList(buildingId)
    }

    private fun initObj()
    {
        presenter = PresenterManagerReceipt(this , this)

        fabRegisterReceipt = findViewById(R.id.fab_managerReceipt_AddReceipt)
        fabRegisterReceipt.setOnClickListener(this)

        listView = findViewById(R.id.lv_managerReceipt_receiptList)

        ivRefresh = findViewById(R.id.iv_managerReceipt_refresh)
        ivRefresh.setOnClickListener(this)

        progressBar = findViewById(R.id.pb_managerReceipt_progressBar)

        tvEmptyList = findViewById(R.id.tv_managerReceipt_emptyList)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            fabRegisterReceipt ->
            {
                showFragmentReceiptAdd()
            }

            ivRefresh ->
            {
                invisibleRefreshButton()
                visibleProgressBar()

                presenter.fetchReceiptList(buildingId)
            }
        }
    }

    override fun showRefreshButton()
    {
        invisibleListView()
        invisibleTextViewEmptyListAlert()
        invisibleProgressBar()
        visibleRefreshButton()
    }

    override fun showEmptyReceiptListAlert()
    {
        invisibleListView()
        invisibleRefreshButton()
        invisibleProgressBar()
        visibleTextViewEmptyListAlert()
    }

    override fun showToast(text: String)
    {
        visibleRefreshButton()
        invisibleProgressBar()
        invisibleListView()
        invisibleTextViewEmptyListAlert()
        Toast.makeText(this , text , Toast.LENGTH_SHORT).show()
    }

    override fun showList(items: List<Receipt>)
    {
        visibleListView()
        invisibleRefreshButton()
        invisibleTextViewEmptyListAlert()
        invisibleProgressBar()

        val reversedItems : List<Receipt> = items.reversed()
        val adapter : ManagerReceiptListAdapter = ManagerReceiptListAdapter(this , reversedItems)
        listView.adapter = adapter
        listView.setOnItemClickListener(object:AdapterView.OnItemClickListener
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
                presenter.fetchReceiptList(buildingId)
            }

        })
        supportFragmentManager.beginTransaction().replace(R.id.fl_managerReceipt , fragment).addToBackStack(null).commit()
    }

    private fun showFragmentReceiptAdd()
    {
        val fragment : FragmentManagerRegisterNewReceipt = FragmentManagerRegisterNewReceipt(buildingId , object:CallBack
        {
            override fun onRegisteredReceipt()
            {
                visibleProgressBar()
                presenter.fetchReceiptList(buildingId)
            }

        })
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in , android.R.anim.fade_out , android.R.anim.fade_in , android.R.anim.fade_out)
            .add(R.id.fl_managerReceipt , fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun visibleListView()
    {
        listView.visibility = View.VISIBLE
    }

    private fun invisibleListView()
    {
        listView.visibility = View.GONE
    }

    private fun visibleRefreshButton()
    {
        ivRefresh.visibility = View.VISIBLE
    }

    private fun invisibleRefreshButton()
    {
        ivRefresh.visibility = View.GONE
    }

    private fun visibleTextViewEmptyListAlert()
    {
        tvEmptyList.visibility = View.VISIBLE
    }

    private fun invisibleTextViewEmptyListAlert()
    {
        tvEmptyList.visibility = View.GONE
    }

    private fun visibleProgressBar()
    {
        progressBar.visibility = View.VISIBLE
    }

    private fun invisibleProgressBar()
    {
        progressBar.visibility = View.GONE
    }
}