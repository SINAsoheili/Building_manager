package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.MODEL.ReceiptType
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRegisterNewReceipt
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerRegisterNewReceipt
import ir.sinasoheili.building_manager.R

class FragmentManagerRegisterNewReceipt constructor(buildingId:Int, callback:CallBack): Fragment(R.layout.fragment_register_new_receipt), ContractManagerRegisterNewReceipt.ContractManagerRegisterNewReceiptView, View.OnClickListener
{
    private var spinnerReceiptType : Spinner? = null
    private var etPayDate : EditText? = null
    private var etIssueDate : EditText? = null
    private var etAmount : EditText? = null
    private var etIdReceipt : EditText? = null
    private var etIdPayment : EditText? = null
    private var btnSubmit : Button? = null
    private var tilIdReceipt : TextInputLayout? = null
    private var tilIdPayment : TextInputLayout? = null
    private var tilPayDate : TextInputLayout? = null
    private var tilIssueDate : TextInputLayout? = null
    private var tilAmount : TextInputLayout? = null

    private val callback : CallBack = callback
    private var presenter : PresenterManagerRegisterNewReceipt? = null
    private val buildingId : Int = buildingId
    private val listReceiptType : Array<ReceiptType> = arrayOf(ReceiptType.water , ReceiptType.power , ReceiptType.gas)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
    }

    private fun initObj(view:View)
    {
        presenter =  PresenterManagerRegisterNewReceipt(view.context , this)

        spinnerReceiptType = view.findViewById(R.id.spinner_fragment_registerNewReceipt_receiptType)
        showSpinner(view.context)

        tilPayDate= view.findViewById(R.id.til_fragment_registerNewReceipt_payDate)
        etPayDate = view.findViewById(R.id.et_fragment_registerNewReceipt_payDate)

        tilIssueDate = view.findViewById(R.id.til_fragment_registerNewReceipt_issueDate)
        etIssueDate = view.findViewById(R.id.et_fragment_registerNewReceipt_issueDate)

        tilAmount = view.findViewById(R.id.til_fragment_registerNewReceipt_amount)
        etAmount = view.findViewById(R.id.et_fragment_registerNewReceipt_amount)

        tilIdReceipt = view.findViewById(R.id.til_fragment_registerNewReceipt_idReceipt)
        etIdReceipt = view.findViewById(R.id.et_fragment_registerNewReceipt_idReceipt)

        tilIdPayment = view.findViewById(R.id.til_fragment_registerNewReceipt_idPayment)
        etIdPayment = view.findViewById(R.id.et_fragment_registerNewReceipt_idPayment)

        btnSubmit = view.findViewById(R.id.btn_fragment_registerNewReceipt_submit)
        btnSubmit!!.setOnClickListener(this)
    }

    private fun showSpinner(context: Context)
    {
        val adapter : ArrayAdapter<ReceiptType> = ArrayAdapter(context , R.layout.support_simple_spinner_dropdown_item , listReceiptType)
        spinnerReceiptType!!.adapter =  adapter
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            btnSubmit ->
            {
                if(checkIdReceipt() && checkIdPayment() && checkPayDate() && checkIssueDate() && checkAmount())
                {
                    val idReceipt : String = etIdReceipt!!.text.toString()
                    val idPayment : String = etIdPayment!!.text.toString()
                    val payDate : String = etPayDate!!.text.toString()
                    val issueDate : String = etIssueDate!!.text.toString()
                    val amount : Double = etAmount!!.text.toString().toDouble()
                    var receiptType : ReceiptType = listReceiptType[spinnerReceiptType!!.selectedItemPosition]

                    val receipt : Receipt = Receipt(receiptType , payDate , issueDate , amount , idReceipt , idPayment , buildingId)
                    presenter!!.registerReceipt(receipt)
                }
            }
        }
    }

    private fun checkIdReceipt():Boolean
    {
        if(etIdReceipt!!.text.isEmpty())
        {
            tilIdReceipt?.error = context?.getString(R.string.fill_field)
            etIdReceipt?.requestFocus()
            return false
        }
        tilIdReceipt?.isErrorEnabled = false
        return true
    }

    private fun checkIdPayment():Boolean
    {
        if(etIdPayment!!.text.isEmpty())
        {
            tilIdPayment?.error = context?.getString(R.string.fill_field)
            etIdPayment?.requestFocus()
            return false
        }
        tilIdPayment?.isErrorEnabled = false
        return true
    }

    private fun checkPayDate():Boolean
    {
        if(etPayDate!!.text.isEmpty())
        {
            tilPayDate?.error = context?.getString(R.string.fill_field)
            etPayDate?.requestFocus()
            return false
        }
        tilPayDate?.isErrorEnabled = false
        return true
    }

    private fun checkIssueDate():Boolean
    {
        if(etIssueDate!!.text.isEmpty())
        {
            tilIssueDate?.error = context?.getString(R.string.fill_field)
            etIssueDate?.requestFocus()
            return false
        }
        tilIssueDate?.isErrorEnabled = false
        return true
    }

    private fun checkAmount():Boolean
    {
        if(etAmount!!.text.isEmpty())
        {
            tilAmount?.error = context?.getString(R.string.fill_field)
            etAmount?.requestFocus()
            return false
        }
        tilAmount?.isErrorEnabled = false
        return true
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun registeredReceipt()
    {
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        callback.onRegisteredReceipt()
    }

    interface CallBack
    {
        fun onRegisteredReceipt()
    }
}
