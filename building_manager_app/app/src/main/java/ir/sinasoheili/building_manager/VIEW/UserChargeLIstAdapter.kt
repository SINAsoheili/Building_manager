package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.ChargeStatus
import ir.sinasoheili.building_manager.R

class UserChargeLIstAdapter constructor(context: Context, val items:List<Charge>): ArrayAdapter<Charge>(context , R.layout.user_charge_list_item , items)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var view : View? = convertView
        var viewHolder : ViewHolder

        if(view == null)
        {
            val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.user_charge_list_item , parent , false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }
        else
        {
            viewHolder = view.tag as ViewHolder
        }


        viewHolder.fill(items.get(position))
        return view!!
    }

    private class ViewHolder constructor(val view:View)
    {
        private var tvAmount : TextView? = null
        private var tvStatus : TextView? = null
        private var tvIssueDate : TextView? = null
        private var tvPayDate : TextView? = null

        init
        {
            tvAmount = view.findViewById(R.id.tv_UserChargeListItem_amount)
            tvStatus = view.findViewById(R.id.tv_UserChargeListItem_status)
            tvIssueDate = view.findViewById(R.id.tv_UserChargeListItem_issueDate)
            tvPayDate = view.findViewById(R.id.tv_UserChargeListItem_payDate)
        }

        fun fill(charge:Charge)
        {
            tvAmount!!.text = view.context.getString(R.string.manager_chargeListItem_amount , charge.amount.toString())
            tvStatus!!.text = view.context.getString(R.string.manager_chargeListItem_status , ChargeStatus.getChargeStatus(charge.status).toString())
            tvIssueDate!!.text = view.context.getString(R.string.manager_chargeListItem_issueDate , Charge.convertDate(charge.issue_date))

            if(charge.pay_date == null)
            {
                tvPayDate!!.text = view.context.getString(R.string.manager_chargeListItem_payDate , view.context.getString(R.string.undefined))
            }
            else
            {
                tvPayDate!!.text = view.context.getString(R.string.manager_chargeListItem_payDate , Charge.convertDate(charge.pay_date!!))
            }
        }

    }
}