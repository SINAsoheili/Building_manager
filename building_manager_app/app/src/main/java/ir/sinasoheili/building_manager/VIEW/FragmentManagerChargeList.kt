package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.PRESENTER.ContractManagerChargeList
import ir.sinasoheili.building_manager.PRESENTER.PresenterManagerChargeList
import ir.sinasoheili.building_manager.R

class FragmentManagerChargeList constructor(val unit:Unit): Fragment(R.layout.fragment_charge_list) , ContractManagerChargeList.ContractManagerChargeListView, View.OnClickListener
{
    private var listView : ListView? = null
    private var ivRefresh : ImageView? = null
    private var tvEmptyList : TextView? = null
    private var progressBar : ProgressBar? = null

    private var presenter : ContractManagerChargeList.ContractManagerChargeListPresenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)
        visibleProgressBar()
        presenter!!.getChargeList(unit.building_id , unit.unit_number)
    }

    private fun initObj(view:View)
    {
        presenter = PresenterManagerChargeList(view.context , this)

        listView = view.findViewById(R.id.lv_fragmentChargeList)

        ivRefresh = view.findViewById(R.id.iv_fragmentChargeList_refresh)
        ivRefresh!!.setOnClickListener(this)

        progressBar = view.findViewById(R.id.pb_fragmentChargeList_progress)

        tvEmptyList = view.findViewById(R.id.tv_fragmentChargeList_emptyList)
    }

    override fun showRefreshButton()
    {
        invisibleList()
        invisibleTvEmptyList()
        invisibleProgressBar()
        visibleRefreshImage()
    }

    override fun showEmptyListAlert()
    {
        invisibleRefreshImage()
        invisibleList()
        invisibleProgressBar()
        visibleTvEmptyList()
    }

    override fun showToast(text: String)
    {
        invisibleProgressBar()
        invisibleTvEmptyList()
        invisibleList()
        visibleRefreshImage()
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun showChargeList(items: List<Charge>)
    {
        invisibleProgressBar()
        invisibleRefreshImage()
        invisibleTvEmptyList()
        visibleList()

        val adapterManager : ManagerChargeLIstAdapter = ManagerChargeLIstAdapter(context!! , items.reversed())
        listView!!.adapter = adapterManager

        listView!!.setOnItemClickListener(object:AdapterView.OnItemClickListener
        {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
            {
                val dialogFragment : DialogFragmentManagerEditCharge = DialogFragmentManagerEditCharge(items.get(p2) , object:DialogFragmentManagerEditCharge.CallBack
                {
                    override fun onChargeDeleted()
                    {
                        presenter!!.getChargeList(unit.building_id , unit.unit_number)
                    }

                    override fun onChargeUpdated()
                    {
                        presenter!!.getChargeList(unit.building_id , unit.unit_number)
                    }

                })
                dialogFragment.show(fragmentManager!! , null)
            }
        })
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            ivRefresh ->
            {
                invisibleRefreshImage()
                invisibleTvEmptyList()
                invisibleList()
                visibleProgressBar()

                presenter!!.getChargeList(unit.building_id , unit.unit_number)
            }
        }
    }

    private fun visibleRefreshImage()
    {
        ivRefresh!!.visibility = View.VISIBLE
    }

    private fun invisibleRefreshImage()
    {
        ivRefresh!!.visibility = View.GONE
    }

    private fun visibleList()
    {
        listView!!.visibility = View.VISIBLE
    }

    private fun invisibleList()
    {
        listView!!.visibility = View.GONE
    }

    private fun visibleTvEmptyList()
    {
        tvEmptyList!!.visibility = View.VISIBLE
    }

    private fun invisibleTvEmptyList()
    {
        tvEmptyList!!.visibility = View.GONE
    }

    private fun visibleProgressBar()
    {
        progressBar!!.visibility = View.VISIBLE
    }

    private fun invisibleProgressBar()
    {
        progressBar!!.visibility = View.GONE
    }
}