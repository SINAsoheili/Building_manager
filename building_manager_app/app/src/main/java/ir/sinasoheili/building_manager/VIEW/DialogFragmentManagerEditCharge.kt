package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.ChargeStatus
import ir.sinasoheili.building_manager.PRESENTER.ContractDialogFragmentManagerEditCharge
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerDialogEditCharge
import ir.sinasoheili.building_manager.R

class DialogFragmentManagerEditCharge constructor(val charge:Charge , val callback:CallBack): DialogFragment(), ContractDialogFragmentManagerEditCharge.ContractDialogFragmentManagerEditChargeView , View.OnClickListener,
    View.OnFocusChangeListener {
    private lateinit var etAmount : EditText
    private lateinit var etIssueDate : EditText
    private lateinit var etPayDate : EditText
    private lateinit var spinner : Spinner
    private lateinit var btnSubmit : Button
    private lateinit var btnDelete : Button
    private lateinit var tilAmount : TextInputLayout
    private lateinit var tilPaydate: TextInputLayout
    private lateinit var tilIssueDate : TextInputLayout

    private lateinit var presenter : PresenterManagerDialogEditCharge
    private lateinit var datePickerDialog : PersianDatePickerDialog

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
        presenter = PresenterManagerDialogEditCharge(view.context , this)

        datePickerDialog = PersianDatePickerDialog(context)

        tilAmount = view.findViewById(R.id.til_dialogFragmentEditCharge_amount)
        etAmount = view.findViewById(R.id.et_dialogFragmentEditCharge_amount)

        tilIssueDate = view.findViewById(R.id.til_dialogFragmentEditCharge_issueDate)
        etIssueDate = view.findViewById(R.id.et_dialogFragmentEditCharge_issueDate)
        etIssueDate.setOnClickListener(this)
        etIssueDate.setOnFocusChangeListener(this)

        tilPaydate = view.findViewById(R.id.til_dialogFragmentEditCharge_payDate)
        etPayDate = view.findViewById(R.id.et_dialogFragmentEditCharge_payDate)
        etPayDate.setOnClickListener(this)
        etPayDate.setOnFocusChangeListener(this)

        spinner = view.findViewById(R.id.sp_dialogFragmentEditCharge_status)
        initSpinner()

        btnDelete = view.findViewById(R.id.btn_dialogFragmentEditCharge_delete)
        btnDelete.setOnClickListener(this)

        btnSubmit = view.findViewById(R.id.btn_dialogFragmentEditCharge_submit)
        btnSubmit.setOnClickListener(this)
    }

    private fun fillItem()
    {
        etAmount.setText(charge.amount.toString())
        etIssueDate.setText(Charge.convertDate(charge.issue_date))
        spinner.setSelection(charge.status)
        if(charge.pay_date == null)
        {
            etPayDate.setText("")
        }
        else
        {
            etPayDate.setText(Charge.convertDate(charge.pay_date!!))
        }
    }

    private fun initSpinner()
    {
        val statusArray : Array<ChargeStatus> = arrayOf(ChargeStatus.unpaid , ChargeStatus.paid)
        val adapter : ArrayAdapter<ChargeStatus> = ArrayAdapter(context!! , android.R.layout.simple_spinner_item , statusArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            btnSubmit ->
            {
                if(checkAmount() && checkIssueDate())
                {
                    val amount : Double = etAmount.text.toString().toDouble()
                    val status : ChargeStatus = spinner.selectedItem as ChargeStatus
                    val issueDate : String = etIssueDate.text.toString()

                    val newCharge : Charge

                    if(etPayDate.text!!.isEmpty())
                    {
                        newCharge = Charge(charge.id , amount , status , issueDate , charge.manager_id , charge.building_id , charge.unit_number)
                    }
                    else
                    {
                        val payDate : String = etPayDate.text.toString()

                        newCharge = Charge(charge.id , amount , status , issueDate , payDate , charge.manager_id , charge.building_id , charge.unit_number)
                    }

                    presenter.updateCharge(newCharge)
                }
            }

            btnDelete ->
            {
                presenter.deleteCharge(charge)
            }

            etIssueDate ->
            {
                datePickerDialog.showDateDialog(datePickerDialog , etIssueDate)
            }

            etPayDate ->
            {
                datePickerDialog.showDateDialog(datePickerDialog , etPayDate)
            }
        }
    }

    override fun onFocusChange(view: View?, p1: Boolean)
    {
        if(p1) {
            when(view) {
                etIssueDate -> {
                    datePickerDialog.showDateDialog(datePickerDialog , etIssueDate)
                }

                etPayDate -> {
                    datePickerDialog.showDateDialog(datePickerDialog , etPayDate)
                }
            }
        }
    }

    private fun checkAmount():Boolean
    {
        if(etAmount.text.isEmpty())
        {
            tilAmount.error = context?.getString(R.string.fill_field)
            etAmount.requestFocus()
            return false
        }

        tilAmount.isErrorEnabled = false
        return true
    }

    private fun checkIssueDate():Boolean
    {
        if(etIssueDate.text.isEmpty())
        {
            tilIssueDate.error = context?.getString(R.string.fill_field)
            tilIssueDate.requestFocus()
            return false
        }

        tilIssueDate.isErrorEnabled = false
        return true
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun chargeDeleted()
    {
        callback.onChargeDeleted()
        this.dismiss()
    }

    override fun chargeUpdated()
    {
        callback.onChargeUpdated()
        this.dismiss()
    }

    interface CallBack
    {
        fun onChargeDeleted()
        fun onChargeUpdated()
    }

    private fun PersianDatePickerDialog.showDateDialog(dateDialog : PersianDatePickerDialog, etDate:EditText)
    {
        //set input type to null because when open dialog and keyboard screen flashed
        etDate.inputType = InputType.TYPE_NULL

        //show date picker dialog
        dateDialog
            .setPositiveButtonString(context?.getString(R.string.submit))
            .setNegativeButton(context?.getString(R.string.cancel))
            .setTodayButton(context?.getString(R.string.today))
            .setTodayButtonVisible(true)
            .setMinYear(1300)
            .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
            .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
            .setListener(object : Listener
            {
                override fun onDismissed()
                {

                }

                override fun onDateSelected(persianCalendar: PersianCalendar?)
                {
                    if(persianCalendar != null)
                    {
                        etDate.setText(context?.getString(
                            R.string.date_format ,
                            persianCalendar.persianYear.toString() ,
                            persianCalendar.persianMonth.toString() ,
                            persianCalendar.persianDay.toString()
                        ))
                    }
                }

            })
            .show()
    }
}