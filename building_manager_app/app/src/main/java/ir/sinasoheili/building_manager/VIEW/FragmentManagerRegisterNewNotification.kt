package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRegisterNewNotification
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerRegisterNewNotification
import ir.sinasoheili.building_manager.R

class FragmentManagerRegisterNewNotification(val buildingId : Int,val  callback:CallBack) : Fragment(R.layout.fragment_register_new_notification), View.OnClickListener , ContractManagerRegisterNewNotification.ContractManagerRegisterNewNotificationView
{
    private var tilTitle : TextInputLayout? = null
    private var tilMessage : TextInputLayout? = null
    private var etTitle : EditText? = null
    private var etText : EditText? = null
    private var btnSubmit : Button? = null
    private var etDate : EditText? = null
    private var tilDate : TextInputLayout? = null

    private var presenter : PresenterManagerRegisterNewNotification? = null

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

        btnSubmit = view.findViewById(R.id.btn_fragment_registerNewNotification_submit)
        btnSubmit!!.setOnClickListener(this)

        presenter = PresenterManagerRegisterNewNotification(view.context , this)
    }

    override fun onClick(p0: View?)
    {
        if(checkTitle() && checkText() && checkDate())
        {
            val title : String = etTitle!!.text.toString()
            val text : String = etText!!.text.toString()
            val date : String = etDate!!.text.toString()

            Log.i("tag" , date)

            val notification : Notification = Notification(title , text , date , buildingId)

            presenter!!.registerNewNotification(notification)
        }
    }

    private fun checkDate():Boolean
    {
        if(etDate!!.text.isEmpty())
        {
            tilDate!!.error = context!!.getString(R.string.fill_field)
            etDate!!.requestFocus()
            return false
        }

        tilDate!!.isErrorEnabled = false
        return true
    }

    private fun checkTitle():Boolean
    {
        if(etTitle!!.text.isEmpty())
        {
            tilTitle!!.error = context!!.getString(R.string.fill_field)
            etTitle?.requestFocus()
            return false
        }
        tilTitle!!.isErrorEnabled = false
        return true
    }

    private fun checkText():Boolean
    {
        if(etText!!.text.isEmpty())
        {
            tilMessage!!.error = context!!.getString(R.string.fill_field)
            etText?.requestFocus()
            return false
        }
        tilMessage!!.isErrorEnabled = false
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
    }

    interface CallBack
    {
        fun onNotificationRegistered()
    }
}