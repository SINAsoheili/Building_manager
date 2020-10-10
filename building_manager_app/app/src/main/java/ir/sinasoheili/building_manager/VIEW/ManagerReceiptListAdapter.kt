package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.MODEL.ReceiptType
import ir.sinasoheili.building_manager.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ManagerReceiptListAdapter constructor(context:Context ,val items:List<Receipt>) : ArrayAdapter<Receipt>(context , R.layout.manager_receipt_list_item , items)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var view : View? = convertView
        var viewHolder : ViewHolder? = null

        if(view == null)
        {
            val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.manager_receipt_list_item , parent , false)
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
            tvType = view.findViewById(R.id.tv_managerReceiptList_type)
            tvAmount = view.findViewById(R.id.tv_managerReceiptList_amount)
            tvIssueDate = view.findViewById(R.id.tv_managerReceiptList_issueDate)
            tvPayDate = view.findViewById(R.id.tv_managerReceiptList_payDate)
        }

        fun fill(receipt:Receipt)
        {
            tvType?.text = ReceiptType.getReceipt(receipt.type).toString()
            tvAmount?.text = receipt.amount.toString()

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                val issueDateStr : String = "${receipt.issue_date.split(" ").get(1)} ${receipt.issue_date.split(" ").get(2)} ${receipt.issue_date.split(" ").get(3)}"
                val payDateStr : String = "${receipt.pay_date.split(" ").get(1)} ${receipt.pay_date.split(" ").get(2)} ${receipt.pay_date.split(" ").get(3)}"

                val format : DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

                var localDat : LocalDate = LocalDate.parse(issueDateStr , format)
                tvIssueDate?.text = " تاریخ صدور ${localDat.toString().replace("-" , "/")}"

                localDat = LocalDate.parse(payDateStr , format)
                tvPayDate?.text =  " تاریخ پرداخت ${localDat.toString().replace("-" , "/")}"
            }
        }
    }
}