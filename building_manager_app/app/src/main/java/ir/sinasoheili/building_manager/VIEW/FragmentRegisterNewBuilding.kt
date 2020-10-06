package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.sinasoheili.building_manager.R

class FragmentRegisterNewBuilding() : Fragment(R.layout.fragment_register_new_building) , View.OnClickListener
{
    var tilName : TextInputLayout? = null
    var tilCash : TextInputLayout? = null
    var tilAddress : TextInputLayout? = null
    var tilUnitCount : TextInputLayout? = null
    var etName : EditText? = null
    var etAddress : EditText? = null
    var etCash : EditText? = null
    var etUnitCount : EditText? = null
    var btnSubmit : Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view : View)
    {
        tilName = view.findViewById(R.id.til_registerNewBuilding_name)
        tilCash = view.findViewById(R.id.til_registerNewBuilding_cash)
        tilAddress = view.findViewById(R.id.til_registerNewBuilding_address)
        tilUnitCount = view.findViewById(R.id.til_registerNewBuilding_unitCount)

        etName = view.findViewById(R.id.et_registerNewBuilding_name)
        etAddress = view.findViewById(R.id.et_registerNewBuilding_address)
        etCash = view.findViewById(R.id.et_registerNewBuilding_cash)
        etUnitCount = view.findViewById(R.id.et_registerNewBuilding_unitCount)

        btnSubmit = view.findViewById(R.id.btn_registerNewBuilding_submit)
        btnSubmit!!.setOnClickListener(this)
    }

    override fun onClick(view : View?)
    {
        when(view)
        {
            btnSubmit ->
            {
                Toast.makeText(context , "click" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}