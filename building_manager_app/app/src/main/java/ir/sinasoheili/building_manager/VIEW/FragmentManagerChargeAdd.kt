package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.ChargeStatus
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.PRESENTER.UserAuthFilePreferenceHandler
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerChargeAdd.ContractManagerChargeAddView
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerChargeAdd
import ir.sinasoheili.building_manager.R

class FragmentManagerChargeAdd constructor(val unit:Unit): Fragment(R.layout.fragment_charge_add) , ContractManagerChargeAddView, View.OnClickListener,
    View.OnFocusChangeListener {
    private var tilAmount : TextInputLayout? = null
    private var tilIssueDate : TextInputLayout? = null
    private var tilPayDate : TextInputLayout? = null
    private var etAmount : EditText? = null
    private var etPayDate : EditText? = null
    private var etIssueDate : EditText? = null
    private var spStatus : Spinner? = null
    private var btnSubmit : Button? = null

    private var presenter : PresenterManagerChargeAdd? = null
    private var datePickerDialog : PersianDatePickerDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view:View)
    {
        presenter = PresenterManagerChargeAdd(view.context , this)

        datePickerDialog = PersianDatePickerDialog(context)

        tilAmount = view.findViewById(R.id.til_fragment_addCharge_amount)
        etAmount = view.findViewById(R.id.et_fragment_addCharge_amount)

        tilIssueDate = view.findViewById(R.id.til_fragment_addCharge_issueDate)
        etIssueDate = view.findViewById(R.id.et_fragment_addCharge_issueDate)
        etIssueDate!!.setOnClickListener(this)
        etIssueDate!!.setOnFocusChangeListener(this)

        tilPayDate = view.findViewById(R.id.til_fragment_addCharge_payDate)
        etPayDate = view.findViewById(R.id.et_fragment_addCharge_payDate)
        etPayDate!!.setOnClickListener(this)
        etPayDate!!.setOnFocusChangeListener(this)

        spStatus = view.findViewById(R.id.sp_fragment_addCharge_status)
        initStatusSpinner()

        btnSubmit = view.findViewById(R.id.btn_fragment_addCharge_submit)
        btnSubmit!!.setOnClickListener(this)
    }

    private fun initStatusSpinner()
    {
        val adapter : ArrayAdapter<ChargeStatus> = ArrayAdapter(context!! , android.R.layout.simple_spinner_item , listOf(ChargeStatus.paid , ChargeStatus.unpaid))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
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
                    val managerId : Int = UserAuthFilePreferenceHandler.readFromFile(context!! , UserAuthFilePreferenceHandler.KEY_MANAGER_ID)!!.toInt()

                    var charge : Charge = Charge(amount , status , issueDate , managerId , unit.building_id , unit.unit_number)

                    if(! etPayDate!!.text.isEmpty())
                    {
                        val payDate : String = etPayDate!!.text.toString()
                        charge.pay_date = payDate
                    }

                    presenter!!.registerCharge(charge)
                }
            }

            etIssueDate->
            {
                datePickerDialog!!.showDateDialog(datePickerDialog!! , etIssueDate!!)
            }
            etPayDate->
            {
                datePickerDialog!!.showDateDialog(datePickerDialog!! , etPayDate!!)
            }
        }
    }

    override fun onFocusChange(view: View?, p1: Boolean)
    {
        if(p1) {
            when(view) {
                etIssueDate-> {
                    datePickerDialog!!.showDateDialog(datePickerDialog!! , etIssueDate!!)
                }
                etPayDate-> {
                    datePickerDialog!!.showDateDialog(datePickerDialog!! , etPayDate!!)
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

    fun PersianDatePickerDialog.showDateDialog(dateDialog : PersianDatePickerDialog, etDate:EditText)
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