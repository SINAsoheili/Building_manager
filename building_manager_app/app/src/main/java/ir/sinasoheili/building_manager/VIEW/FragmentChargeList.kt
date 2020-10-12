package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.PRESENTER.ContractChargeList
import ir.sinasoheili.building_manager.PRESENTER.PresenterChargeList
import ir.sinasoheili.building_manager.R

class FragmentChargeList constructor(val unit:Unit): Fragment(R.layout.fragment_charge_list) , ContractChargeList.ContractChargeListView, View.OnClickListener
{
    private var listView : ListView? = null
    private var ivRefresh : ImageView? = null

    private var presenter : ContractChargeList.ContractChargeListPresenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        presenter!!.getChargeList(unit.building_id , unit.unit_number)
    }

    private fun initObj(view:View)
    {
        presenter = PresenterChargeList(view.context , this)

        listView = view.findViewById(R.id.lv_fragmentChargeList)

        ivRefresh = view.findViewById(R.id.iv_fragmentChargeList_refresh)
        ivRefresh!!.setOnClickListener(this)
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
        visibleRefreshButton()
    }

    override fun showChargeList(items: List<Charge>)
    {
        visibleListView()

        val adapterManager : ManagerChargeLIstAdapter = ManagerChargeLIstAdapter(context!! , items)
        listView!!.adapter = adapterManager
    }

    private fun visibleRefreshButton()
    {
        listView!!.visibility = View.GONE
        ivRefresh!!.visibility = View.VISIBLE
    }

    private fun visibleListView()
    {
        listView!!.visibility = View.VISIBLE
        ivRefresh!!.visibility = View.GONE
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            ivRefresh ->
            {
                presenter!!.getChargeList(unit.building_id , unit.unit_number)
            }
        }
    }
}