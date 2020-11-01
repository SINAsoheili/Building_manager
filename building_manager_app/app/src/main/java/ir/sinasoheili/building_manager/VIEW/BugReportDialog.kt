package ir.sinasoheili.building_manager.VIEW

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.sinasoheili.building_manager.R

class BugReportDialog constructor(val context:Context)
{
    fun showDialog()
    {
        val dialog : MaterialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
        dialog.setTitle(context.getString(R.string.bugReport_dialog_title))
        dialog.setMessage(context.getString(R.string.bugReport_dialog_message))
        dialog.setPositiveButton(context.getString(R.string.send_email) , object:DialogInterface.OnClickListener
        {
            override fun onClick(p0: DialogInterface?, p1: Int)
            {
                val intent:Intent = Intent(Intent.ACTION_SENDTO , Uri.parse("sinasoheili79@gmail.com"))
                context.startActivity(intent)
            }

        })
        dialog.show()
    }
}