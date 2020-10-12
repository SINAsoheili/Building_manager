package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRegisterNewNotification
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerRegisterNewNotification
import ir.sinasoheili.building_manager.R

class FragmentManagerRegisterNewNotification(buildingId : Int, callback:CallBack) : Fragment(R.layout.fragment_register_new_notification), View.OnClickListener , ContractManagerRegisterNewNotification.ContractManagerRegisterNewNotificationView
{
    private var etTitle : EditText? = null
    private var etText : EditText? = null
    private var btnSubmit : Button? = null
    private var callback : CallBack = callback

    private val buildingId : Int = buildingId
    private var presenter : PresenterManagerRegisterNewNotification? = null

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

        presenter = PresenterManagerRegisterNewNotification(view.context , this)
    }

    override fun onClick(p0: View?)
    {
        if(checkTitle() && checkText())
        {
            val title : String = etTitle!!.text.toString()
            val text : String = etText!!.text.toString()

            val notification : Notification = Notification(text , title , buildingId)

            presenter!!.registerNewNotification(notification)
        }
    }

    private fun checkTitle():Boolean
    {
        if(etTitle!!.text.isEmpty())
        {
            Toast.makeText(context , context!!.getString(R.string.fragment_registerNewNotification_fill_title) , Toast.LENGTH_SHORT).show()
            etTitle?.requestFocus()
            return false
        }

        return true
    }

    private fun checkText():Boolean
    {
        if(etText!!.text.isEmpty())
        {
            Toast.makeText(context , context!!.getString(R.string.fragment_registerNewNotification_fill_text) , Toast.LENGTH_SHORT).show()
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