package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.R

class FragmentManagerSetting : Fragment(R.layout.manager_setting_fragment) , View.OnClickListener
{
    private var tvBugReport : TextView? = null
    private var tvScoreToApp : TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view:View)
    {
        tvBugReport = view.findViewById(R.id.tv_manager_bugReport)
        tvBugReport!!.setOnClickListener(this)

        tvScoreToApp = view.findViewById(R.id.tv_manager_scoreToApp)
        tvScoreToApp!!.setOnClickListener(this)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            tvBugReport ->
            {
                Toast.makeText(context , "click" , Toast.LENGTH_SHORT).show()
            }

            tvScoreToApp ->
            {
                Toast.makeText(context , "click" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}