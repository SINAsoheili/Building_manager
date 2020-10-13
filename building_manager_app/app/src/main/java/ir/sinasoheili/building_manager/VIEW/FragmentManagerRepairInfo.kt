package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRepairInfo
import ir.sinasoheili.building_manager.R

class FragmentManagerRepairInfo constructor(val repair:Repair): Fragment(R.layout.fragment_repair_info) , ContractManagerRepairInfo.ContractManagerRepairInfoView , View.OnClickListener
{
    private var ivDelete : ImageView? = null
    private var tvTitle : TextView? = null
    private var tvComment : TextView? = null
    private var tvAmount : TextView? = null
    private var tvDate : TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initView(view)

        fillField()
    }

    private fun initView(view:View)
    {
        ivDelete = view.findViewById(R.id.iv_fragmentRepairInfo_delete)
        ivDelete!!.setOnClickListener(this)

        tvTitle = view.findViewById(R.id.tv_fragmentRepairInfo_title)
        tvComment = view.findViewById(R.id.tv_fragmentRepairInfo_comment)
        tvAmount = view.findViewById(R.id.tv_fragmentRepairInfo_amount)
        tvDate = view.findViewById(R.id.tv_fragmentRepairInfo_date)
    }

    private fun fillField()
    {
        tvTitle!!.text = repair.title
        tvComment!!.text = repair.comment
        tvDate!!.text = Repair.convertDate(repair.date)
        tvAmount!!.text = repair.amount.toString()
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            ivDelete ->
            {
                Toast.makeText(context , "click" , Toast.LENGTH_SHORT).show()
            }

        }
    }
}