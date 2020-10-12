package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.ChargeStatus
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.PRESENTER.ManagerAuthFilePreferenceHandler
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerChargeAdd.ContractManagerChargeAddView
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerChargeAdd
import ir.sinasoheili.building_manager.R

class FragmentManagerChargeAdd constructor(val unit:Unit): Fragment(R.layout.fragment_charge_add) , ContractManagerChargeAddView, View.OnClickListener
{
    private var tilAmount : TextInputLayout? = null
    private var tilIssueDate : TextInputLayout? = null
    private var tilPayDate : TextInputLayout? = null
    private var etAmount : EditText? = null
    private var etPayDate : EditText? = null
    private var etIssueDate : EditText? = null
    private var spStatus : Spinner? = null
    private var btnSubmit : Button? = null

    private var presenter : PresenterManagerChargeAdd? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view:View)
    {
        presenter = PresenterManagerChargeAdd(view.context , this)

        tilAmount = view.findViewById(R.id.til_fragment_addCharge_amount)
        etAmount = view.findViewById(R.id.et_fragment_addCharge_amount)

        tilIssueDate = view.findViewById(R.id.til_fragment_addCharge_issueDate)
        etIssueDate = view.findViewById(R.id.et_fragment_addCharge_issueDate)

        tilPayDate = view.findViewById(R.id.til_fragment_addCharge_payDate)
        etPayDate = view.findViewById(R.id.et_fragment_addCharge_payDate)

        spStatus = view.findViewById(R.id.sp_fragment_addCharge_status)
        initStatusSpinner()

        btnSubmit = view.findViewById(R.id.btn_fragment_addCharge_submit)
        btnSubmit!!.setOnClickListener(this)
    }

    private fun initStatusSpinner()
    {
        val adapter : ArrayAdapter<ChargeStatus> = ArrayAdapter(context!! , android.R.layout.simple_spinner_item , listOf(ChargeStatus.paid , ChargeStatus.unpaid))
        spStatus!!.adapter = adapter
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            btnSubmit->
            {
                if(checkAmount() && checkIssueDate())
                {
                    val amount : Double = etAmount!!.text.toString().toDouble()
                    val issueDate : String = etIssueDate!!.text.toString()
                    val status : ChargeStatus = spStatus!!.selectedItem as ChargeStatus
                    val managerId : Int = ManagerAuthFilePreferenceHandler.readFromFile(context!! , ManagerAuthFilePreferenceHandler.KEY_MANAGER_ID)!!.toInt()

                    var charge : Charge = Charge(amount , status , issueDate , managerId , unit.building_id , unit.unit_number)

                    if(! etPayDate!!.text.isEmpty())
                    {
                        val payDate : String = etPayDate!!.text.toString()
                        charge.pay_date = payDate
                    }

                    presenter!!.registerCharge(charge)
                }
            }
        }
    }

    private fun checkAmount():Boolean
    {
        if(etAmount!!.text.isEmpty())
        {
            tilAmount!!.error = context!!.getString(R.string.fill_field)
            etAmount?.requestFocus()
            return false
        }
        tilAmount!!.isErrorEnabled = false
        return true
    }

    private fun checkIssueDate():Boolean
    {
        if(etIssueDate!!.text.isEmpty())
        {
            tilIssueDate!!.error = context!!.getString(R.string.fill_field)
            etIssueDate?.requestFocus()
            return false
        }
        tilIssueDate!!.isErrorEnabled = false
        return true
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun onChageRegistered()
    {
        fragmentManager!!.beginTransaction().remove(this).commit()
        Toast.makeText(context , context!!.getString(R.string.charge_successfully_added) , Toast.LENGTH_SHORT).show()
    }
}