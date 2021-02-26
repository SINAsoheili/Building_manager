package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
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
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRegisterNewNotification
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerRegisterNewNotification
import ir.sinasoheili.building_manager.R

class FragmentManagerRegisterNewNotification(val buildingId : Int,val  callback:CallBack) : Fragment(R.layout.fragment_register_new_notification), View.OnClickListener , ContractManagerRegisterNewNotification.ContractManagerRegisterNewNotificationView,
    View.OnFocusChangeListener {
    private lateinit var tilTitle : TextInputLayout
    private lateinit var tilMessage : TextInputLayout
    private lateinit var etTitle : EditText
    private lateinit var etText : EditText
    private lateinit var btnSubmit : Button
    private lateinit var etDate : EditText
    private lateinit var tilDate : TextInputLayout

    private lateinit var presenter : PresenterManagerRegisterNewNotification

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view:View)
    {
        tilTitle = view.findViewById(R.id.til_fragment_registerNewNotification_title)
        etTitle = view.findViewById(R.id.et_fragment_registerNewNotification_title)

        tilMessage = view.findViewById(R.id.til_fragment_registerNewNotification_text)
        etText = view.findViewById(R.id.et_fragment_registerNewNotification_text)

        tilDate = view.findViewById(R.id.til_fragment_registerNewNotification_date)
        etDate = view.findViewById(R.id.et_fragment_registerNewNotification_date)
        etDate.setOnClickListener(this)
        etDate.setOnFocusChangeListener(this)

        btnSubmit = view.findViewById(R.id.btn_fragment_registerNewNotification_submit)
        btnSubmit.setOnClickListener(this)

        presenter = PresenterManagerRegisterNewNotification(view.context , this)
    }

    override fun onClick(p0: View?)
    {
        when(p0)
        {
            btnSubmit ->
            {
                if(checkTitle() && checkText() && checkDate())
                {
                    val title : String = etTitle.text.toString()
                    val text : String = etText.text.toString()
                    val date : String = etDate.text.toString()

                    val notification : Notification = Notification(title , text , date , buildingId)

                    presenter.registerNewNotification(notification)
                }
            }

            etDate ->
            {
                val dialog : PersianDatePickerDialog = PersianDatePickerDialog(context)
                dialog.showDateDialog(dialog , etDate)
            }
        }
    }

    override fun onFocusChange(p0: View?, p1: Boolean)
    {
        when
        {
            p0!!.equals(etDate) && p1 ->
            {
                val dialog : PersianDatePickerDialog = PersianDatePickerDialog(context)
                dialog.showDateDialog(dialog , etDate)
            }
        }
    }

    private fun checkDate():Boolean
    {
        if(etDate.text.isEmpty())
        {
            tilDate.error = context!!.getString(R.string.fill_field)
            etDate.requestFocus()
            return false
        }

        tilDate.isErrorEnabled = false
        return true
    }

    private fun checkTitle():Boolean
    {
        if(etTitle.text.isEmpty())
        {
            tilTitle.error = context!!.getString(R.string.fill_field)
            etTitle.requestFocus()
            return false
        }
        tilTitle.isErrorEnabled = false
        return true
    }

    private fun checkText():Boolean
    {
        if(etText.text.isEmpty())
        {
            tilMessage.error = context!!.getString(R.string.fill_field)
            etText.requestFocus()
            return false
        }
        tilMessage.isErrorEnabled = false
        return true
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context!! , text , Toast.LENGTH_SHORT).show()
    }

    override fun onNotificationRegistered()
    {
        callback.onNotificationRegistered()
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        fragmentManager?.popBackStack()
    }

    interface CallBack
    {
        fun onNotificationRegistered()
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