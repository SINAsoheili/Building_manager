package ir.sinasoheili.building_manager.VIEW

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.PRESENTER.ContractUnitInfo.ContractUnitInfoView
import ir.sinasoheili.building_manager.PRESENTER.PresenterUnitInfo

class ManagerUnitInfoActivity : AppCompatActivity() , View.OnClickListener , ContractUnitInfoView
{
    private var tvUnitNumber : TextView? = null
    private var tvUnitTag : TextView? = null
    private var tvOwnerName : TextView? = null
    private var tvPhone : TextView? = null
    private var iv_call : ImageView? = null
    private var iv_sms : ImageView? = null
    private var tvDeleteUnit: TextView? = null
    private var tvAddCharge : TextView? = null
    private var tvChargeList : TextView? = null

    private var unit : Unit? = null
    private var presenter:PresenterUnitInfo? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_info)

        initObj()

        val bundle : Bundle? = intent.extras
        if(bundle != null)
        {
            unit = bundle.get("unit") as Unit
            fillItem(unit)
        }
    }

    private fun initObj()
    {
        presenter = PresenterUnitInfo(this , this)

        tvUnitNumber= findViewById(R.id.tv_unitInfo_unitNumber)
        tvUnitTag   = findViewById(R.id.tv_unitInfo_tag)
        tvOwnerName = findViewById(R.id.tv_unitInfo_ownerName)
        tvPhone     = findViewById(R.id.tv_unitInfo_phone)

        iv_call     = findViewById(R.id.iv_unitInfo_call)
        iv_call!!.setOnClickListener(this)

        iv_sms      = findViewById(R.id.iv_unitInfo_sms)
        iv_sms!!.setOnClickListener(this)

        tvDeleteUnit = findViewById(R.id.tv_unitInfo_deleteUnit)
        tvDeleteUnit!!.setOnClickListener(this)

        tvAddCharge = findViewById(R.id.tv_unitInfo_addCharge)
        tvAddCharge!!.setOnClickListener(this)

        tvChargeList = findViewById(R.id.tv_unitInfo_listCharge)
        tvChargeList!!.setOnClickListener(this)
    }

    private fun fillItem(unit:Unit?)
    {
        if(unit == null)
        {
            return
        }

        tvUnitNumber!!.text = this@ManagerUnitInfoActivity.getString(R.string.unit_list_item_unitNumber)+" "+unit.unit_number.toString()
        tvUnitTag!!.text = this@ManagerUnitInfoActivity.getString(R.string.unit_list_item_tag)+" "+unit.tag.toString()
        tvOwnerName!!.text = this@ManagerUnitInfoActivity.getString(R.string.unit_list_item_ownerName)+" "+unit.owner_name
        tvPhone!!.text = unit.phone
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            iv_call ->
            {
                val intent : Intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel:"+unit!!.phone))
                startActivity(intent)
            }

            iv_sms ->
            {
                val intent : Intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("sms:"+unit!!.phone))
                startActivity(intent)
            }

            tvDeleteUnit ->
            {
                confirmDeleteDialog()
            }

            tvAddCharge ->
            {
                val fragment : FragmentChargeAdd = FragmentChargeAdd(unit!!)
                supportFragmentManager.beginTransaction().replace(R.id.fl_unitInfo , fragment).addToBackStack(null).commit()
            }

            tvChargeList ->
            {
                val fragment : FragmentChargeList = FragmentChargeList(unit!!)
                supportFragmentManager.beginTransaction().replace(R.id.fl_unitInfo , fragment).addToBackStack(null).commit()
            }
        }
    }

    override fun showToast(text: String)
    {
        Toast.makeText(this , text , Toast.LENGTH_SHORT).show()
    }

    override fun onUnitDeleted()
    {
        var intent : Intent = Intent()
        setResult(Activity.RESULT_OK , intent)
        finish()

        Toast.makeText(this , this.getString(R.string.unitSuccessfullyDeleted), Toast.LENGTH_SHORT).show()
    }

    fun confirmDeleteDialog()
    {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setTitle(this.getString(R.string.Warning))
        dialog.setMessage(this.getString(R.string.doYouConfirmDeleteUnit))

        dialog.setPositiveButton(this.getString(R.string.yes) , object:DialogInterface.OnClickListener
        {
            override fun onClick(p0: DialogInterface?, p1: Int)
            {
                presenter!!.deleteUnit(unit!!)
            }
        })
        dialog.setNegativeButton(this.getString(R.string.no) , object:DialogInterface.OnClickListener
        {
            override fun onClick(p0: DialogInterface?, p1: Int)
            {
                p0?.dismiss()
            }
        })

        dialog.show()
    }
}