package ir.sinasoheili.building_manager.VIEW

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.MODEL.ReceiptType
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerReceiptInfo
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerReceiptInfo
import ir.sinasoheili.building_manager.R

class FragmentManagerReceiptInfo constructor(val receipt:Receipt , val callback:CallBack) : Fragment(R.layout.fragment_receipt_info) ,  ContractManagerReceiptInfo.ContractManagerReceiptInfoView, View.OnClickListener
{
    private var ivDelete : ImageView? = null
    private var tvType : TextView? = null
    private var tvPayDate : TextView? = null
    private var tvIssueDate : TextView? = null
    private var tvAmount : TextView? = null
    private var tvIdReceipt : TextView? = null
    private var tvIdPayment : TextView? = null

    private var presenter : PresenterManagerReceiptInfo? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
        fillField()
    }

    private fun initObj(view:View)
    {
        presenter = PresenterManagerReceiptInfo(view.context , this)

        ivDelete = view.findViewById(R.id.iv_fragmentReceiptInfo_delete)
        ivDelete!!.setOnClickListener(this)

        tvType = view.findViewById(R.id.tv_fragmentReceiptInfo_type)
        tvPayDate = view.findViewById(R.id.tv_fragmentReceiptInfo_payDate)
        tvIssueDate = view.findViewById(R.id.tv_fragmentReceiptInfo_IssueDate)
        tvAmount = view.findViewById(R.id.tv_fragmentReceiptInfo_amount)
        tvIdReceipt = view.findViewById(R.id.tv_fragmentReceiptInfo_idReceipt)
        tvIdPayment = view.findViewById(R.id.tv_fragmentReceiptInfo_idPayment)
    }

    private fun fillField()
    {
        tvType!!.text = ReceiptType.getReceipt(receipt.type).toString()
        tvPayDate!!.text = Receipt.convertDate(receipt.pay_date)
        tvIssueDate!!.text = Receipt.convertDate(receipt.issue_date)
        tvAmount!!.text = receipt.amount.toString()
        tvIdReceipt!!.text = receipt.id_receipt
        tvIdPayment!!.text = receipt.id_payment
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            ivDelete ->
            {
                showConfirmDeleteDialog()
            }
        }
    }

    private fun showConfirmDeleteDialog()
    {
        val dialog : AlertDialog.Builder = AlertDialog.Builder(context)
        dialog.setTitle(context?.getString(R.string.Warning))
        dialog.setMessage(context?.getString(R.string.doYouConfirmDeleteReceipt))
        dialog.setPositiveButton(R.string.yes , object:DialogInterface.OnClickListener
        {
            override fun onClick(p0: DialogInterface?, p1: Int)
            {
                presenter!!.deleteReceipt(receipt)
            }

        })
        dialog.setNegativeButton(R.string.no , object:DialogInterface.OnClickListener
        {
            override fun onClick(p0: DialogInterface?, p1: Int)
            {
                p0?.dismiss()
            }

        })

        dialog.show()
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun ReceiptDelete()
    {
        callback.onReceiptDeleted()
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
    }

    interface CallBack
    {
        fun onReceiptDeleted()
    }
}
