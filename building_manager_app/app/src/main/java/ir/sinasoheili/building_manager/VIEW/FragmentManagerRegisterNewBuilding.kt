package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.sinasoheili.building_manager.MODEL.Building
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRegisterNewBuilding
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerRegisterNewBuilding
import ir.sinasoheili.building_manager.R

class FragmentManagerRegisterNewBuilding constructor(callBack:CallBack) : Fragment(R.layout.fragment_register_new_building) , View.OnClickListener , ContractManagerRegisterNewBuilding.ContractManagerRegisterNewBuildingView
{
    private lateinit var tilName : TextInputLayout
    private lateinit var tilAddress : TextInputLayout
    private lateinit var tilUnitCount : TextInputLayout
    private lateinit var etName : EditText
    private lateinit var etAddress : EditText
    private lateinit var etUnitCount : EditText
    private lateinit var btnSubmit : Button

    private val presenter : PresenterManagerRegisterNewBuilding = PresenterManagerRegisterNewBuilding(this)
    private val callBack : CallBack = callBack

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view : View)
    {
        tilName = view.findViewById(R.id.til_registerNewBuilding_name)
        tilAddress = view.findViewById(R.id.til_registerNewBuilding_address)
        tilUnitCount = view.findViewById(R.id.til_registerNewBuilding_unitCount)

        etName = view.findViewById(R.id.et_registerNewBuilding_name)
        etAddress = view.findViewById(R.id.et_registerNewBuilding_address)
        etUnitCount = view.findViewById(R.id.et_registerNewBuilding_unitCount)

        btnSubmit = view.findViewById(R.id.btn_registerNewBuilding_submit)
        btnSubmit.setOnClickListener(this)
    }

    override fun onClick(view : View?)
    {
        when(view)
        {
            btnSubmit ->
            {
                if(checkName() && checkAddress() && checkUnitCount())
                {
                    val name : String = etName.text.toString()
                    val address : String = etAddress.text.toString()
                    val unit_count : Int = etUnitCount.text.toString().toInt()

                    val building : Building = Building(name , address , unit_count)
                    presenter.registerBuilding(context!! , building)
                }
            }
        }
    }

    private fun checkName():Boolean
    {
        if(etName.text.isEmpty())
        {
            tilName.error = context?.getString(R.string.fill_field)
            etName.requestFocus()
            return false
        }
        tilName.isErrorEnabled = false
        return true
    }

    private fun checkAddress():Boolean
    {
        if(etAddress.text.isEmpty())
        {
            tilAddress.error = context?.getString(R.string.fill_field)
            etAddress.requestFocus()
            return false
        }

        tilAddress.isErrorEnabled = false
        return true
    }

    private fun checkUnitCount():Boolean
    {
        if(etUnitCount.text.isEmpty())
        {
            tilUnitCount.error = context?.getString(R.string.fill_field)
            etUnitCount.requestFocus()
            return false
        }

        tilUnitCount.isErrorEnabled = false
        return true
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun buildingRegistered(building: Building)
    {
        callBack.onBuildingRegistred(building)
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
    }

    //callback interface for when new building registered
    interface CallBack
    {
        fun onBuildingRegistred(building:Building)
    }
}