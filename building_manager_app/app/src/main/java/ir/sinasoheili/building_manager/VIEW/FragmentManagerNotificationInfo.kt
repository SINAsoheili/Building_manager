package ir.sinasoheili.building_manager.VIEW

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerNotificationInfo
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerNotificationInfo
import ir.sinasoheili.building_manager.R

class FragmentManagerNotificationInfo constructor(val notification:Notification , val callback:CallBack): Fragment(R.layout.fragment_manager_notification_info) , ContractManagerNotificationInfo.ContractManagerNotificationInfoView, View.OnClickListener
{
    private lateinit var btnDelete : Button
    private lateinit var tvTitle : TextView
    private lateinit var tvText : TextView
    private lateinit var tvDate : TextView

    private lateinit var presenter : PresenterManagerNotificationInfo

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        fillField()
    }

    private fun initObj(view:View)
    {
        presenter = PresenterManagerNotificationInfo(view.context , this)

        btnDelete = view.findViewById(R.id.btn_fragmentNotificationInfo_delete)
        btnDelete.setOnClickListener(this)

        tvTitle = view.findViewById(R.id.tv_fragmentNotificationInfo_title)
        tvText  = view.findViewById(R.id.tv_fragmentNotificationInfo_text)
        tvDate  = view.findViewById(R.id.tv_fragmentNotificationInfo_date)
    }

    private fun fillField()
    {
        tvTitle.text = notification.title
        tvText.text = notification.text
        tvDate.text = Notification.convertDate(notification.date)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            btnDelete ->
            {
                showConfirmDeleteDialog()
            }
        }
    }

    private fun showConfirmDeleteDialog()
    {
        val dialog : AlertDialog.Builder = AlertDialog.Builder(context)
        dialog.setTitle(context?.getString(R.string.Warning))
        dialog.setMessage(context?.getString(R.string.doYouConfirmDeleteNotification))
        dialog.setPositiveButton(R.string.yes , object:DialogInterface.OnClickListener
        {
            override fun onClick(p0: DialogInterface?, p1: Int)
            {
                presenter.deleteNotification(notification)
            }

        })

        dialog.setNegativeButton(R.string.no , object:DialogInterface.OnClickListener
        {
            override fun onClick(p0: DialogInterface?, p1: Int)
            {
                p0?.dismiss()
            }

        })

        dialog.show()
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun notificationDeleted()
    {
        callback.onNotificationDeleted()
        fragmentManager?.popBackStack()
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
    }

    interface CallBack
    {
        fun onNotificationDeleted()
    }
}
