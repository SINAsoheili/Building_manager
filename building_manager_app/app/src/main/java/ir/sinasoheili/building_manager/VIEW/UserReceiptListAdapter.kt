package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.MODEL.ReceiptType
import ir.sinasoheili.building_manager.R

class UserReceiptListAdapter constructor(context:Context, val items:List<Receipt>) : ArrayAdapter<Receipt>(context , R.layout.user_receipt_list_item , items)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var view : View? = convertView
        var viewHolder : ViewHolder? = null

        if(view == null)
        {
            val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.user_receipt_list_item , parent , false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }
        else
        {
            viewHolder = convertView?.tag as ViewHolder
        }

        viewHolder!!.fill(items.get(position))
        return view!!
    }

    class ViewHolder constructor(val view:View)
    {
        var tvType:TextView? = null
        var tvAmount:TextView? = null
        var tvIssueDate:TextView? = null
        var tvPayDate:TextView? = null

        init
        {
            tvType = view.findViewById(R.id.tv_userReceiptList_type)
            tvAmount = view.findViewById(R.id.tv_userReceiptList_amount)
            tvIssueDate = view.findViewById(R.id.tv_userReceiptList_issueDate)
            tvPayDate = view.findViewById(R.id.tv_userReceiptList_payDate)
        }

        fun fill(receipt:Receipt)
        {
            tvType?.text = ReceiptType.getReceipt(receipt.type).toString()
            tvAmount?.text = view.context.getString(R.string.receiptListItem_amount , receipt.amount.toString())

            tvIssueDate?.text = view.context.getString(R.string.receiptListItem_IssueDate , Receipt.convertDate(receipt.issue_date))
            tvPayDate?.text = view.context.getString(R.string.receiptListItem_PayDate , Receipt.convertDate(receipt.pay_date))
        }
    }
}