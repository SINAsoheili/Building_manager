package ir.sinasoheili.building_manager.VIEW

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import ir.sinasoheili.building_manager.R

class BugReportDialog : DialogFragment() , View.OnClickListener
{
    var tvSendMail : TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view : View = inflater.inflate(R.layout.bug_report_dialog , container , false)
        return view
    }

    override fun onResume()
    {
        super.onResume()

        this.dialog!!.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view:View)
    {
        tvSendMail = view.findViewById(R.id.tv_bug_dialog_send_mail)
        tvSendMail!!.setOnClickListener(this)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            tvSendMail ->
            {
                openGmail()
            }
        }
    }

    private fun openGmail()
    {
        val intent : Intent = Intent(Intent.ACTION_SENDTO)
        intent.setData(Uri.parse("mailto:"+getString(R.string.my_email)))
        intent.putExtra(Intent.EXTRA_SUBJECT , getString(R.string.email_subject))
        startActivity(intent)
    }
}