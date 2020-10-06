package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.sinasoheili.building_manager.R

class FragmentRegitsterNewUnit : Fragment(R.layout.fragment_register_new_unit),View.OnClickListener
{
    var tilOwnerName : TextInputLayout? = null
    var tilPhone : TextInputLayout? = null
    var tilUnitNumber : TextInputLayout? = null
    var tilTag : TextInputLayout? = null
    var etOwnerName : EditText? = null
    var etPhone : EditText? = null
    var etUnitNumber : EditText? = null
    var etTag : EditText? = null
    var btnSubmit : Button? = null

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
        btnSubmit!!.setOnClickListener(this)
    }

    override fun onClick(view: View?)
    {
        if(view!!.equals(btnSubmit))
        {
            Toast.makeText(context , "click" , Toast.LENGTH_SHORT).show()
        }
    }
}