package ir.sinasoheili.building_manager.VIEW

import android.app.ActionBar
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.ChargeStatus
import ir.sinasoheili.building_manager.PRESENTER.ContractDialogFragmentManagerEditCharge
import ir.sinasoheili.building_manager.R

class DialogFragmentManagerEditCharge constructor(val charge:Charge): DialogFragment(), ContractDialogFragmentManagerEditCharge.ContractDialogFragmentManagerEditChargeView , View.OnClickListener
{
    private var etAmount : EditText? = null
    private var etIssueDate : EditText? = null
    private var etPayDate : EditText? = null
    private var spinner : Spinner? = null
    private var btnSubmit : Button? = null
    private var btnDelete : Button? = null
    private var tilAmount : TextInputLayout? = null
    private var tilPaydate: TextInputLayout? = null
    private var tilIssueDate : TextInputLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_dialog_manager_edit_charge , container , false)
    }

    override fun onResume()
    {
        super.onResume()

        this.dialog?.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        fillItem()
    }

    private fun initObj(view:View)
    {
        tilAmount = view.findViewById(R.id.til_dialogFragmentEditCharge_amount)
        etAmount = view.findViewById(R.id.et_dialogFragmentEditCharge_amount)

        tilIssueDate = view.findViewById(R.id.til_dialogFragmentEditCharge_issueDate)
        etIssueDate = view.findViewById(R.id.et_dialogFragmentEditCharge_issueDate)

        tilPaydate = view.findViewById(R.id.til_dialogFragmentEditCharge_payDate)
        etPayDate = view.findViewById(R.id.et_dialogFragmentEditCharge_payDate)

        spinner = view.findViewById(R.id.sp_dialogFragmentEditCharge_status)
        initSpinner()

        btnDelete = view.findViewById(R.id.btn_dialogFragmentEditCharge_delete)
        btnDelete!!.setOnClickListener(this)

        btnSubmit = view.findViewById(R.id.btn_dialogFragmentEditCharge_submit)
        btnSubmit!!.setOnClickListener(this)
    }

    private fun fillItem()
    {
        etAmount!!.setText(charge.amount.toString())
        etIssueDate!!.setText(Charge.convertDate(charge.issue_date))
        etPayDate!!.setText(Charge.convertDate(charge.pay_date))
        spinner!!.setSelection(charge.status)
    }

    private fun initSpinner()
    {
        val statusArray : Array<ChargeStatus> = arrayOf(ChargeStatus.unpaid , ChargeStatus.paid)
        val adapter : ArrayAdapter<ChargeStatus> = ArrayAdapter(context!! , android.R.layout.simple_spinner_item , statusArray)
        spinner!!.adapter = adapter
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            btnSubmit ->
            {
                if(checkAmount() && checkIssueDate())
                {
                    Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
                }
            }

            btnDelete ->
            {
                Toast.makeText(context , "click" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkAmount():Boolean
    {
        if(etAmount!!.text.isEmpty())
        {
            tilAmount!!.error = context?.getString(R.string.fill_field)
            etAmount?.requestFocus()
            return false
        }

        tilAmount?.isErrorEnabled = false
        return true
    }

    private fun checkIssueDate():Boolean
    {
        if(etIssueDate!!.text.isEmpty())
        {
            tilIssueDate!!.error = context?.getString(R.string.fill_field)
            tilIssueDate?.requestFocus()
            return false
        }

        tilIssueDate?.isErrorEnabled = false
        return true
    }
}