package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.PRESENTER.ContractRegisterNewReceipt
import ir.sinasoheili.building_manager.R

class FragmentRegisterNewReceipt : Fragment(R.layout.fragment_register_new_receipt), ContractRegisterNewReceipt.ContractRegisterNewReceiptView, View.OnClickListener
{
    private var spinnerReceiptType : Spinner? = null
    private var etPayDate : EditText? = null
    private var etIssueDate : EditText? = null
    private var etAmount : EditText? = null
    private var etIdReceipt : EditText? = null
    private var etIdPayment : EditText? = null
    private var btnSubmit : Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view:View)
    {
        spinnerReceiptType = view.findViewById(R.id.spinner_fragment_registerNewReceipt_receiptType)

        etPayDate = view.findViewById(R.id.et_fragment_registerNewReceipt_payDate)
        etIssueDate = view.findViewById(R.id.et_fragment_registerNewReceipt_issueDate)
        etAmount = view.findViewById(R.id.et_fragment_registerNewReceipt_amount)
        etIdReceipt = view.findViewById(R.id.et_fragment_registerNewReceipt_idReceipt)
        etIdPayment = view.findViewById(R.id.et_fragment_registerNewReceipt_idPayment)

        btnSubmit = view.findViewById(R.id.btn_fragment_registerNewReceipt_submit)
        btnSubmit!!.setOnClickListener(this)
    }

    override fun onClick(p0: View?)
    {
        Toast.makeText(context , "click" , Toast.LENGTH_SHORT).show()
    }
}