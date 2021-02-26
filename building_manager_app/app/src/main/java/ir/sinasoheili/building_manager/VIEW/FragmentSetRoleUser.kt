package ir.sinasoheili.building_manager.VIEW

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.sinasoheili.building_manager.R
import ir.sinasoheili.building_manager.PRESENTER.ContractSetRoleUser.ContractSetRoleUserView
import ir.sinasoheili.building_manager.PRESENTER.PresenterSetRoleUser
import ir.sinasoheili.building_manager.PRESENTER.UserAuthFilePreferenceHandler

class FragmentSetRoleUser : Fragment(R.layout.fragment_setrole_user) , ContractSetRoleUserView, View.OnClickListener
{
    private lateinit var tilPhone : TextInputLayout
    private lateinit var tilBuildingId : TextInputLayout
    private lateinit var etPhone : EditText
    private lateinit var etBuildingId : EditText
    private lateinit var btnSubmit : Button

    private lateinit var presenter : PresenterSetRoleUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        initObj(view)
    }

    private fun initObj(view:View)
    {
        presenter = PresenterSetRoleUser(view.context , this)

        tilPhone = view.findViewById(R.id.til_fragment_setRole_user_phone)
        etPhone = view.findViewById(R.id.et_fragment_setRole_user_phone)

        tilBuildingId = view.findViewById(R.id.til_fragment_setRole_user_buildingId)
        etBuildingId = view.findViewById(R.id.et_fragment_setRole_user_buildingId)

        btnSubmit = view.findViewById(R.id.btn_fragment_setRole_user_submit)
        btnSubmit.setOnClickListener(this)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            btnSubmit ->
            {
                if(checkPhone() && checkBuildingId())
                {
                    val phone = etPhone.text.toString()
                    val buildingId : Int = etBuildingId.text.toString().toInt()

                    presenter.authenticateUser(phone , buildingId)
                }
            }
        }
    }

    private fun checkPhone():Boolean
    {
        if(etPhone.text.isEmpty())
        {
            tilPhone.error = context!!.getString(R.string.fill_field)
            etPhone.requestFocus()
            return false
        }

        tilPhone.isErrorEnabled = false
        return true
    }

    private fun checkBuildingId():Boolean
    {
        if(etBuildingId.text.isEmpty())
        {
            tilBuildingId.error = context!!.getString(R.string.fill_field)
            etBuildingId.requestFocus()
            return false
        }
        tilBuildingId.isErrorEnabled = false
        return true
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun userAuthenticateSuccess(unitNumber:Int , buildingId:Int)
    {
        UserAuthFilePreferenceHandler.writeToFile(context!! , UserAuthFilePreferenceHandler.KEY_USER_ID_UnitNumber , unitNumber.toString())
        UserAuthFilePreferenceHandler.writeToFile(context!! , UserAuthFilePreferenceHandler.KEY_USER_ID_BuildignId , buildingId.toString())
        moveToUserDashboardActivity()
    }

    fun moveToUserDashboardActivity()
    {
        fragmentManager!!.beginTransaction().remove(this).commit()
        val intent : Intent = Intent(context , UserDashboardActivity::class.java)
        startActivity(intent)
    }
}