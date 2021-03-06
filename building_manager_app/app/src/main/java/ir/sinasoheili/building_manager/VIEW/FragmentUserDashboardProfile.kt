package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardProfile.ContractUserDashboardProfileView
import ir.sinasoheili.building_manager.PRESENTER.PresenterUserDashboardProfile
import ir.sinasoheili.building_manager.R

class FragmentUserDashboardProfile : Fragment(R.layout.user_dashboard_profile_fragment) , ContractUserDashboardProfileView , View.OnClickListener
{
    private lateinit var tvOwnerName : TextView
    private lateinit var tvPhone : TextView
    private lateinit var tvUnitNumber : TextView
    private lateinit var tvTag : TextView
    private lateinit var tvBugReport : TextView
    private lateinit var tvScoreToApp : TextView
    private lateinit var progressBar : ProgressBar
    private lateinit var ivReload : ImageView

    private lateinit var presenterProfile : PresenterUserDashboardProfile
    private var isFragmentEnable : Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        presenterProfile.getUnitInfo()
    }

    private fun initObj(view:View)
    {
        isFragmentEnable = true

        presenterProfile = PresenterUserDashboardProfile(view.context , this)

        tvOwnerName = view.findViewById(R.id.tv_userDashboard_ownerName)
        tvPhone = view.findViewById(R.id.tv_userDashboard_phone)
        tvUnitNumber = view.findViewById(R.id.tv_userDashboard_unitNumber)
        tvTag = view.findViewById(R.id.tv_userDashboard_tag)

        tvBugReport = view.findViewById(R.id.tv_userDashboard_bugReport)
        tvBugReport.setOnClickListener(this)

        tvScoreToApp = view.findViewById(R.id.tv_userDashboard_scoreToApp)
        tvScoreToApp.setOnClickListener(this)

        progressBar = view.findViewById(R.id.pb_userDashboard_progressBar)

        ivReload = view.findViewById(R.id.iv_userDashboard_reload)
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun showUnitInfo(unit: Unit)
    {
        if(isFragmentEnable) {
            tvOwnerName.text = context!!.getString(R.string.name_and_family , unit.owner_name)
            tvPhone.text = context!!.getString(R.string.phone_number , unit.phone)
            tvUnitNumber.text = context!!.getString(R.string.unit , unit.unit_number.toString())
            tvTag.text = context!!.getString(R.string.tag , unit.tag.toString())
        }
    }

    override fun visibleReloatButton() {
        if(isFragmentEnable)
            ivReload.visibility = View.VISIBLE
    }

    override fun invisibleReloatButton() {
        if(isFragmentEnable)
            ivReload.visibility = View.GONE
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            tvBugReport ->
            {
                val dialog : BugReportDialog = BugReportDialog()
                dialog.show(fragmentManager!! , null)
            }

            tvScoreToApp ->
            {
                Toast.makeText(context , "clicked" , Toast.LENGTH_SHORT).show()
            }

            ivReload -> {
                presenterProfile.getUnitInfo()
            }
        }
    }

    override fun visibleProgressBar() {
        if(isFragmentEnable)
            progressBar.visibility = View.VISIBLE
    }

    override fun invisibleProgressBar() {
        if(isFragmentEnable)
            progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        isFragmentEnable = false
    }
}