package ir.sinasoheili.building_manager.VIEW

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerRepairInfo
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerRepairInfo
import ir.sinasoheili.building_manager.R

class FragmentManagerRepairInfo constructor(val repair:Repair , val callback:CallBack): Fragment(R.layout.fragment_repair_info) , ContractManagerRepairInfo.ContractManagerRepairInfoView , View.OnClickListener
{
    private var ivDelete : ImageView? = null
    private var tvTitle : TextView? = null
    private var tvComment : TextView? = null
    private var tvAmount : TextView? = null
    private var tvDate : TextView? = null

    private var presenter:PresenterManagerRepairInfo? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        fillField()
    }

    private fun initObj(view:View)
    {
        presenter = PresenterManagerRepairInfo(view.context , this)

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
                showDeleteConfirmDialog()
            }

        }
    }

    private fun showDeleteConfirmDialog()
    {
        val dialog:AlertDialog.Builder = AlertDialog.Builder(context)
        dialog.setTitle(context?.getString(R.string.Warning))
        dialog.setMessage(context?.getString(R.string.doYouConfirmDeleteRepair))
        dialog.setPositiveButton(context?.getString(R.string.yes) , object:DialogInterface.OnClickListener
        {
            override fun onClick(p0: DialogInterface?, p1: Int)
            {
                presenter!!.deleteRepair(repair)
            }

        })
        dialog.setNegativeButton(context?.getString(R.string.no ) , object:DialogInterface.OnClickListener
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

    override fun onRepairDeleted()
    {
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        callback.onRepirDeleted()
    }

    interface CallBack
    {
        fun onRepirDeleted()
    }
}