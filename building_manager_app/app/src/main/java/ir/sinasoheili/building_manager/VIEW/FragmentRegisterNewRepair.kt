package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.sinasoheili.building_manager.PRESENTER.ContractRegisterNewRepair.ContractRegisterNewRepairView
import ir.sinasoheili.building_manager.R

class FragmentRegisterNewRepair : Fragment(R.layout.fragment_register_new_repair) , ContractRegisterNewRepairView, View.OnClickListener
{
    private var etTitle : EditText? = null
    private var etComment : EditText? = null
    private var etAmount : EditText? = null
    private var etDate : EditText? = null
    private var btnSubmit : Button? = null
    private var tilTitle : TextInputLayout? = null
    private var tilComment : TextInputLayout? = null
    private var tilDate : TextInputLayout? = null
    private var tilAmount : TextInputLayout? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view:View)
    {
        tilTitle = view.findViewById(R.id.til_fragment_registerNewRepair_title)
        etTitle = view.findViewById(R.id.et_fragment_registerNewRepair_title)

        tilComment = view.findViewById(R.id.til_fragment_registerNewRepair_comment)
        etComment = view.findViewById(R.id.et_fragment_registerNewRepair_comment)

        tilDate = view.findViewById(R.id.til_fragment_registerNewRepair_date)
        etDate = view.findViewById(R.id.et_fragment_registerNewRepair_date)

        tilAmount = view.findViewById(R.id.til_fragment_registerNewRepair_amount)
        etAmount = view.findViewById(R.id.et_fragment_registerNewRepair_amount)

        btnSubmit = view.findViewById(R.id.btn_fragment_registerNewRepair_submit)
        btnSubmit!!.setOnClickListener(this)
    }

    override fun onClick(view: View?)
    {
        Toast.makeText(view!!.context , "click" , Toast.LENGTH_SHORT).show()
    }
}