package ir.sinasoheili.building_manager.VIEW

import android.icu.util.LocaleData
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.PRESENTER.ContractRegisterNewNotification
import ir.sinasoheili.building_manager.PRESENTER.PresenterRegisterNewNotification
import ir.sinasoheili.building_manager.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class FragmentRegisterNewNotification(buildingId : Int , callback:CallBack) : Fragment(R.layout.fragment_register_new_notification), View.OnClickListener , ContractRegisterNewNotification.ContractRegisterNewNotificationView
{
    private var etTitle : EditText? = null
    private var etText : EditText? = null
    private var btnSubmit : Button? = null
    private var callback : CallBack = callback

    private val buildingId : Int = buildingId
    private val presenter : ContractRegisterNewNotification.ContractRegisterNewNotificationPresenter = PresenterRegisterNewNotification(context!! , this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view:View)
    {
        etTitle = view.findViewById(R.id.et_fragment_registerNewNotification_title)

        etText = view.findViewById(R.id.et_fragment_registerNewNotification_text)

        btnSubmit = view.findViewById(R.id.btn_fragment_registerNewNotification_submit)
        btnSubmit!!.setOnClickListener(this)
    }

    override fun onClick(p0: View?)
    {
        if(checkTitle() && checkText())
        {
            val title : String = etTitle!!.text.toString()
            val text : String = etText!!.text.toString()

            val notificaiton : Notification = Notification(text , title , buildingId)
            presenter.registerNewNotification(notificaiton)
        }
    }

    private fun checkTitle():Boolean
    {
        if(etTitle!!.text.isEmpty())
        {
            Snackbar.make(etTitle!! , context!!.getString(R.string.fragment_registerNewNotification_fill_title) , Snackbar.LENGTH_SHORT).show()
            etTitle?.requestFocus()
            return false
        }

        return true
    }

    private fun checkText():Boolean
    {
        if(etText!!.text.isEmpty())
        {
            Snackbar.make(etText!! , context!!.getString(R.string.fragment_registerNewNotification_fill_text) , Snackbar.LENGTH_SHORT).show()
            etText?.requestFocus()
            return false
        }

        return true
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context!! , text , Toast.LENGTH_SHORT).show()
    }

    override fun onNotificationRegistered()
    {
        callback.onNotificationRegistered()
        fragmentManager?.beginTransaction()?.remove(this)
    }

    interface CallBack
    {
        fun onNotificationRegistered()
    }
}