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
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerUnitInfo.ContractManagerUnitInfoView
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerUnitInfo

class ManagerUnitInfoActivity : AppCompatActivity()
    ,View.OnClickListener
    ,ContractManagerUnitInfoView
{
    private lateinit var tvUnitNumber : TextView
    private lateinit var tvUnitTag : TextView
    private lateinit var tvOwnerName : TextView
    private lateinit var tvPhone : TextView
    private lateinit var iv_call : ImageView
    private lateinit var iv_sms : ImageView
    private lateinit var tvDeleteUnit: TextView
    private lateinit var tvAddCharge : TextView
    private lateinit var tvChargeList : TextView

    private var unit : Unit? = null
    private lateinit var presenter:PresenterManagerUnitInfo

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
        presenter = PresenterManagerUnitInfo(this , this)

        tvUnitNumber= findViewById(R.id.tv_unitInfo_unitNumber)
        tvUnitTag   = findViewById(R.id.tv_unitInfo_tag)
        tvOwnerName = findViewById(R.id.tv_unitInfo_ownerName)
        tvPhone     = findViewById(R.id.tv_unitInfo_phone)

        iv_call     = findViewById(R.id.iv_unitInfo_call)
        iv_call.setOnClickListener(this)

        iv_sms      = findViewById(R.id.iv_unitInfo_sms)
        iv_sms.setOnClickListener(this)

        tvDeleteUnit = findViewById(R.id.tv_unitInfo_deleteUnit)
        tvDeleteUnit.setOnClickListener(this)

        tvAddCharge = findViewById(R.id.tv_unitInfo_addCharge)
        tvAddCharge.setOnClickListener(this)

        tvChargeList = findViewById(R.id.tv_unitInfo_listCharge)
        tvChargeList.setOnClickListener(this)
    }

    private fun fillItem(unit:Unit?)
    {
        if(unit == null)
        {
            return
        }

        tvUnitNumber.text = this@ManagerUnitInfoActivity.getString(R.string.unit , unit.unit_number.toString())
        tvUnitTag.text = this@ManagerUnitInfoActivity.getString(R.string.tag , unit.tag.toString())
        tvOwnerName.text = unit.owner_name
        tvPhone.text = unit.phone
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
                val fragment : FragmentManagerChargeAdd = FragmentManagerChargeAdd(unit!!)
                supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in , android.R.anim.fade_out , android.R.anim.fade_in , android.R.anim.fade_out)
                    .replace(R.id.fl_unitInfo , fragment)
                    .addToBackStack(null).commit()
            }

            tvChargeList ->
            {
                val fragment : FragmentManagerChargeList = FragmentManagerChargeList(unit!!)
                supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in , android.R.anim.fade_out , android.R.anim.fade_in , android.R.anim.fade_out)
                    .replace(R.id.fl_unitInfo , fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun showToast(text: String)
    {
        Toast.makeText(this , text , Toast.LENGTH_SHORT).show()
    }

    override fun onUnitDeleted()
    {
        val intent : Intent = Intent()
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
                presenter.deleteUnit(unit!!)
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