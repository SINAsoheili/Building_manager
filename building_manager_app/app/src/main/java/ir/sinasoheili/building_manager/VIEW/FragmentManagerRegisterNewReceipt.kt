package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.MODEL.ReceiptType
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRegisterNewReceipt
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerRegisterNewReceipt
import ir.sinasoheili.building_manager.R

class FragmentManagerRegisterNewReceipt constructor(buildingId:Int, callback:CallBack): Fragment(R.layout.fragment_register_new_receipt), ContractManagerRegisterNewReceipt.ContractManagerRegisterNewReceiptView, View.OnClickListener,
    View.OnFocusChangeListener {
    private lateinit var spinnerReceiptType : Spinner
    private lateinit var etPayDate : EditText
    private lateinit var etIssueDate : EditText
    private lateinit var etAmount : EditText
    private lateinit var etIdReceipt : EditText
    private lateinit var etIdPayment : EditText
    private lateinit var btnSubmit : Button
    private lateinit var tilIdReceipt : TextInputLayout
    private lateinit var tilIdPayment : TextInputLayout
    private lateinit var tilPayDate : TextInputLayout
    private lateinit var tilIssueDate : TextInputLayout
    private lateinit var tilAmount : TextInputLayout

    private val callback : CallBack = callback
    private lateinit var presenter : PresenterManagerRegisterNewReceipt
    private val buildingId : Int = buildingId
    private val listReceiptType : Array<ReceiptType> = arrayOf(ReceiptType.water , ReceiptType.power , ReceiptType.gas)
    private lateinit var datePickerDialog : PersianDatePickerDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view:View)
    {
        presenter =  PresenterManagerRegisterNewReceipt(view.context , this)

        datePickerDialog = PersianDatePickerDialog(context)

        spinnerReceiptType = view.findViewById(R.id.spinner_fragment_registerNewReceipt_receiptType)
        showSpinner(view.context)

        tilPayDate= view.findViewById(R.id.til_fragment_registerNewReceipt_payDate)
        etPayDate = view.findViewById(R.id.et_fragment_registerNewReceipt_payDate)
        etPayDate.setOnClickListener(this)
        etPayDate.setOnFocusChangeListener(this)

        tilIssueDate = view.findViewById(R.id.til_fragment_registerNewReceipt_issueDate)
        etIssueDate = view.findViewById(R.id.et_fragment_registerNewReceipt_issueDate)
        etIssueDate.setOnClickListener(this)
        etIssueDate.setOnFocusChangeListener(this)

        tilAmount = view.findViewById(R.id.til_fragment_registerNewReceipt_amount)
        etAmount = view.findViewById(R.id.et_fragment_registerNewReceipt_amount)

        tilIdReceipt = view.findViewById(R.id.til_fragment_registerNewReceipt_idReceipt)
        etIdReceipt = view.findViewById(R.id.et_fragment_registerNewReceipt_idReceipt)

        tilIdPayment = view.findViewById(R.id.til_fragment_registerNewReceipt_idPayment)
        etIdPayment = view.findViewById(R.id.et_fragment_registerNewReceipt_idPayment)

        btnSubmit = view.findViewById(R.id.btn_fragment_registerNewReceipt_submit)
        btnSubmit.setOnClickListener(this)
    }

    private fun showSpinner(context: Context)
    {
        val adapter : ArrayAdapter<ReceiptType> = ArrayAdapter(context , R.layout.support_simple_spinner_dropdown_item , listReceiptType)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerReceiptType.adapter =  adapter
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            btnSubmit ->
            {
                if(checkIdReceipt() && checkIdPayment() && checkPayDate() && checkIssueDate() && checkAmount())
                {
                    val idReceipt : String = etIdReceipt.text.toString()
                    val idPayment : String = etIdPayment.text.toString()
                    val payDate : String = etPayDate.text.toString()
                    val issueDate : String = etIssueDate.text.toString()
                    val amount : Double = etAmount.text.toString().toDouble()
                    var receiptType : ReceiptType = listReceiptType[spinnerReceiptType.selectedItemPosition]

                    val receipt : Receipt = Receipt(receiptType , payDate , issueDate , amount , idReceipt , idPayment , buildingId)
                    presenter.registerReceipt(receipt)
                }
            }

            etPayDate ->
            {
                datePickerDialog.showDateDialog(datePickerDialog , etPayDate)
            }

            etIssueDate ->
            {
                datePickerDialog.showDateDialog(datePickerDialog , etIssueDate)
            }
        }
    }

    private fun checkIdReceipt():Boolean
    {
        if(etIdReceipt.text.isEmpty())
        {
            tilIdReceipt.error = context?.getString(R.string.fill_field)
            etIdReceipt.requestFocus()
            return false
        }
        tilIdReceipt.isErrorEnabled = false
        return true
    }

    private fun checkIdPayment():Boolean
    {
        if(etIdPayment.text.isEmpty())
        {
            tilIdPayment.error = context?.getString(R.string.fill_field)
            etIdPayment.requestFocus()
            return false
        }
        tilIdPayment.isErrorEnabled = false
        return true
    }

    private fun checkPayDate():Boolean
    {
        if(etPayDate.text.isEmpty())
        {
            tilPayDate.error = context?.getString(R.string.fill_field)
            etPayDate.requestFocus()
            return false
        }
        tilPayDate.isErrorEnabled = false
        return true
    }

    private fun checkIssueDate():Boolean
    {
        if(etIssueDate.text.isEmpty())
        {
            tilIssueDate.error = context?.getString(R.string.fill_field)
            etIssueDate.requestFocus()
            return false
        }
        tilIssueDate.isErrorEnabled = false
        return true
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

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun registeredReceipt()
    {
        callback.onRegisteredReceipt()
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        fragmentManager?.popBackStack()
    }

    interface CallBack
    {
        fun onRegisteredReceipt()
    }

    override fun onFocusChange(view: View?, p1: Boolean)
    {
        if(p1) {
            when(view) {
                etPayDate  -> {
                    datePickerDialog.showDateDialog(datePickerDialog , etPayDate)
                }

                etIssueDate -> {
                    datePickerDialog.showDateDialog(datePickerDialog , etIssueDate)
                }
            }
        }
    }

    fun PersianDatePickerDialog.showDateDialog(dateDialog : PersianDatePickerDialog , etDate:EditText)
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
                        etDate.setText(context?.getString(R.string.date_format ,
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
