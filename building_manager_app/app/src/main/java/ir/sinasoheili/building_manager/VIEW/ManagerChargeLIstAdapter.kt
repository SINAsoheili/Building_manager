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

class ManagerChargeLIstAdapter constructor(context: Context, val items:List<Charge>): ArrayAdapter<Charge>(context , R.layout.manager_charge_list_item , items)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var view : View? = convertView
        var viewHolder : ViewHolder

        if(view == null)
        {
            val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.manager_charge_list_item , parent , false)
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
            tvAmount = view.findViewById(R.id.tv_ManagerchargeListItem_amount)
            tvStatus = view.findViewById(R.id.tv_ManagerchargeListItem_status)
            tvIssueDate = view.findViewById(R.id.tv_ManagerchargeListItem_issueDate)
            tvPayDate = view.findViewById(R.id.tv_ManagerchargeListItem_payDate)
        }

        fun fill(charge:Charge)
        {
            tvAmount!!.text = view.context.getString(R.string.chargeListItem_amount , charge.amount.toString())
            tvStatus!!.text = " "+ChargeStatus.getChargeStatus(charge.status).toString()
            if(ChargeStatus.getChargeStatus(charge.status) == ChargeStatus.paid)
            {
                tvStatus!!.setTextColor(view.context.getColor(R.color.success))
            }
            else
            {
                tvStatus!!.setTextColor(view.context.getColor(R.color.unSuccess))
            }
            tvIssueDate!!.text = view.context.getString(R.string.chargeListItem_issueDate , Charge.convertDate(charge.issue_date))

            if(charge.pay_date == null)
            {
                tvPayDate!!.text = view.context.getString(R.string.chargeListItem_payDate , view.context.getString(R.string.undefined))
            }
            else
            {
                tvPayDate!!.text = view.context.getString(R.string.chargeListItem_payDate , Charge.convertDate(charge.pay_date!!))
            }
        }

    }
}