package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRegisterNewUnit
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerRegisterNewUnit
import ir.sinasoheili.building_manager.R

class FragmentRegitsterNewUnitManager constructor(buildintId:Int, callback:CallBack): Fragment(R.layout.fragment_register_new_unit),View.OnClickListener , ContractManagerRegisterNewUnit.ContractManagerRegisterNewUnitView
{
    private lateinit var tilOwnerName : TextInputLayout
    private lateinit var tilPhone : TextInputLayout
    private lateinit var tilUnitNumber : TextInputLayout
    private lateinit var tilTag : TextInputLayout
    private lateinit var etOwnerName : EditText
    private lateinit var etPhone : EditText
    private lateinit var etUnitNumber : EditText
    private lateinit var etTag : EditText
    private lateinit var btnSubmit : Button

    private val presenter:PresenterManagerRegisterNewUnit = PresenterManagerRegisterNewUnit(this)
    private val buildingId : Int = buildintId
    private val callback : CallBack = callback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(viwe:View)
    {
        tilOwnerName = view!!.findViewById(R.id.til_fragment_registerNewUnit_ownerName)
        tilPhone = view!!.findViewById(R.id.til_fragment_registerNewUnit_phone)
        tilUnitNumber = view!!.findViewById(R.id.til_fragment_registerNewUnit_unitNumber)
        tilTag = view!!.findViewById(R.id.til_fragment_registerNewUnit_tag)

        etOwnerName = view!!.findViewById(R.id.et_fragment_registerNewUnit_ownerName)
        etPhone = view!!.findViewById(R.id.et_fragment_registerNewUnit_phone)
        etUnitNumber = view!!.findViewById(R.id.et_fragment_registerNewUnit_unitNumber)
        etTag = view!!.findViewById(R.id.et_fragment_registerNewUnit_tag)

        btnSubmit = view!!.findViewById(R.id.btn_frament_registerNewUnit_submit)
        btnSubmit.setOnClickListener(this)
    }

    override fun onClick(view: View?)
    {
        if(view!!.equals(btnSubmit))
        {
            if(checkOwnerName() && checkPhone() && checkUnitNumber() && checkTag())
            {
                val ownerName : String = etOwnerName.text.toString()
                val phone : String = etPhone.text.toString()
                val unitNumber : Int = etUnitNumber.text.toString().toInt()
                val tag : Int = etTag.text.toString().toInt()

                val unit : Unit = Unit(ownerName , phone , unitNumber , tag , buildingId)
                presenter.registerUnit(context!! , unit)
            }
        }
    }

    private fun checkOwnerName():Boolean
    {
        if(etOwnerName.text.isEmpty())
        {
            tilOwnerName.error = context?.getString(R.string.fill_field)
            etOwnerName.requestFocus()
            return false
        }

        tilOwnerName.isErrorEnabled = false
        return true
    }

    private fun checkPhone():Boolean
    {
        if(etPhone.text.isEmpty())
        {
            tilPhone.error = context?.getString(R.string.fill_field)
            etPhone.requestFocus()
            return false
        }
        tilPhone.isErrorEnabled = false
        return true
    }

    private fun checkUnitNumber():Boolean
    {
        if(etUnitNumber.text.isEmpty())
        {
            tilUnitNumber.error = context?.getString(R.string.fill_field)
            etUnitNumber.requestFocus()
            return false
        }

        tilUnitNumber.isErrorEnabled = false
        return true
    }

    private fun checkTag():Boolean
    {
        if(etTag.text.isEmpty())
        {
            tilTag.error = context?.getString(R.string.fill_field)
            etTag.requestFocus()
            return false
        }

        tilTag.isErrorEnabled = false
        return true
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun unitRegistered()
    {
        callback.updateUnitList()
        fragmentManager!!.beginTransaction().remove(this).commit()
    }

    //callback interface to communicate with activity and fragment
    interface CallBack
    {
        fun updateUnitList()
    }
}