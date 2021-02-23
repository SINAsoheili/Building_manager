package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.os.PersistableBundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRegisterNewRepair.ContractManagerRegisterNewRepairView
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerRegisterNewRepair
import ir.sinasoheili.building_manager.R

class FragmentManagerRegisterNewRepair constructor(val buildingId : Int, val callback:CallBack) : Fragment(R.layout.fragment_register_new_repair) , ContractManagerRegisterNewRepairView, View.OnClickListener,
    View.OnFocusChangeListener
{
    private var etTitle : EditText? = null
    private var etComment : EditText? = null
    private var etAmount : EditText? = null
    private var etDate : EditText? = null
    private var btnSubmit : Button? = null
    private var tilTitle : TextInputLayout? = null
    private var tilComment : TextInputLayout? = null
    private var tilDate : TextInputLayout? = null
    private var tilAmount : TextInputLayout? = null

    private var presenter : PresenterManagerRegisterNewRepair? = null
    private var datePickerDialog : PersianDatePickerDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view:View)
    {
        presenter = PresenterManagerRegisterNewRepair(view.context , this)

        datePickerDialog = PersianDatePickerDialog(context)

        tilTitle = view.findViewById(R.id.til_fragment_registerNewRepair_title)
        etTitle = view.findViewById(R.id.et_fragment_registerNewRepair_title)

        tilComment = view.findViewById(R.id.til_fragment_registerNewRepair_comment)
        etComment = view.findViewById(R.id.et_fragment_registerNewRepair_comment)

        tilDate = view.findViewById(R.id.til_fragment_registerNewRepair_date)
        etDate = view.findViewById(R.id.et_fragment_registerNewRepair_date)
        etDate!!.setOnClickListener(this)
        etDate!!.setOnFocusChangeListener(this)

        tilAmount = view.findViewById(R.id.til_fragment_registerNewRepair_amount)
        etAmount = view.findViewById(R.id.et_fragment_registerNewRepair_amount)

        btnSubmit = view.findViewById(R.id.btn_fragment_registerNewRepair_submit)
        btnSubmit!!.setOnClickListener(this)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            btnSubmit->
            {
                if(checkTitle() && checkComment() && checkDate() && checkAmount())
                {
                    val title : String = etTitle!!.text.toString()
                    val comment : String =  etComment!!.text.toString()
                    val date : String = etDate!!.text.toString()
                    val amount : Double = etAmount!!.text.toString().toDouble()

                    val repair : Repair = Repair(date , comment , title , amount , buildingId)
                    presenter!!.registerRepair(repair)
                }
            }

            etDate ->
            {
                datePickerDialog!!.showDateDialog(datePickerDialog!! , etDate!!)
            }
        }
    }

    override fun onFocusChange(view: View?, p1: Boolean)
    {
        if(p1) {
            when(view) {
                etDate -> {
                    datePickerDialog!!.showDateDialog(datePickerDialog!! , etDate!!)
                }
            }
        }
    }

    private fun checkTitle():Boolean
    {
        if(etTitle!!.text.isEmpty())
        {
            tilTitle?.error = context!!.getString(R.string.fill_field)
            etTitle?.requestFocus()
            return false
        }
        tilTitle?.isErrorEnabled = false
        return true
    }

    private fun checkComment():Boolean
    {
        if(etComment!!.text.isEmpty())
        {
            tilComment?.error = context!!.getString(R.string.fill_field)
            etComment?.requestFocus()
            return false
        }
        tilComment?.isErrorEnabled = false
        return true
    }

    private fun checkDate():Boolean
    {
        if(etDate!!.text.isEmpty())
        {
            tilDate?.error = context!!.getString(R.string.fill_field)
            etDate?.requestFocus()
            return false
        }
        tilDate?.isErrorEnabled = false
        return true
    }

    private fun checkAmount():Boolean
    {
        if(etAmount!!.text.isEmpty())
        {
            tilAmount?.error = context!!.getString(R.string.fill_field)
            etAmount?.requestFocus()
            return false
        }
        tilAmount?.isErrorEnabled = false
        return true
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun registeredRepair()
    {
        callback.onRepairRegistered()
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        fragmentManager?.popBackStack()
    }

    interface CallBack
    {
        fun onRepairRegistered()
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