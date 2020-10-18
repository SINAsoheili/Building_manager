package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.PRESENTER.ContractSetRoleUser.ContractSetRoleUserPresenter
import ir.sinasoheili.building_manager.PRESENTER.ContractSetRoleUser.ContractSetRoleUserView

class FragmentSetRoleUser : Fragment(R.layout.fragment_setrole_user) , ContractSetRoleUserView, View.OnClickListener
{
    private var tilPhone : TextInputLayout? = null
    private var tilBuildingId : TextInputLayout? = null
    private var etPhone : EditText? = null
    private var etBuildingId : EditText? = null
    private var btnSubmit : Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        initObj(view)
    }

    private fun initObj(view:View)
    {
        tilPhone = view.findViewById(R.id.til_fragment_setRole_user_phone)
        etPhone = view.findViewById(R.id.et_fragment_setRole_user_phone)

        tilBuildingId = view.findViewById(R.id.til_fragment_setRole_user_buildingId)
        etBuildingId = view.findViewById(R.id.et_fragment_setRole_user_buildingId)

        btnSubmit = view.findViewById(R.id.btn_fragment_setRole_user_submit)
        btnSubmit!!.setOnClickListener(this)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            btnSubmit ->
            {
                if(checkPhone() && checkBuildingId())
                {
                    Toast.makeText(view!!.context , "click " , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkPhone():Boolean
    {
        if(etPhone!!.text.isEmpty())
        {
            tilPhone!!.error = context!!.getString(R.string.fill_field)
            etPhone?.requestFocus()
            return false
        }

        tilPhone!!.isErrorEnabled = false
        return true
    }

    private fun checkBuildingId():Boolean
    {
        if(etBuildingId!!.text.isEmpty())
        {
            tilBuildingId!!.error = context!!.getString(R.string.fill_field)
            etBuildingId?.requestFocus()
            return false
        }
        tilBuildingId!!.isErrorEnabled = false
        return true
    }
}