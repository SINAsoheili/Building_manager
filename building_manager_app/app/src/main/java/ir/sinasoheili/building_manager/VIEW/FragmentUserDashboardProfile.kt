package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardProfile.ContractUserDashboardProfileView
import ir.sinasoheili.building_manager.PRESENTER.PresenterUserDashboardProfile
import ir.sinasoheili.building_manager.R

class FragmentUserDashboardProfile : Fragment(R.layout.user_dashboard_profile_fragment) , ContractUserDashboardProfileView
{
    private var tvOwnerName : TextView? = null
    private var tvPhone : TextView? = null
    private var tvUnitNumber : TextView? = null
    private var tvTag : TextView? = null

    private var presenterProfile : PresenterUserDashboardProfile? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        presenterProfile!!.getUnitInfo()
    }

    private fun initObj(view:View)
    {
        tvOwnerName = view.findViewById(R.id.tv_userDashboard_ownerName)
        tvPhone = view.findViewById(R.id.tv_userDashboard_phone)
        tvUnitNumber = view.findViewById(R.id.tv_userDashboard_unitNumber)
        tvTag = view.findViewById(R.id.tv_userDashboard_tag)

        presenterProfile = PresenterUserDashboardProfile(view.context , this)
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun showUnitInfo(unit: Unit)
    {
        tvOwnerName!!.text = unit.owner_name
        tvPhone!!.text = unit.phone
        tvUnitNumber!!.text = unit.unit_number.toString()
        tvTag!!.text = unit.tag.toString()
    }
}