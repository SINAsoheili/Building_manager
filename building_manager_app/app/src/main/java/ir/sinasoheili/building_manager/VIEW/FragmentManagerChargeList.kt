package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
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

    private var presenterManager : ContractManagerChargeList.ContractManagerChargeListPresenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        presenterManager!!.getChargeList(unit.building_id , unit.unit_number)
    }

    private fun initObj(view:View)
    {
        presenterManager = PresenterManagerChargeList(view.context , this)

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
                presenterManager!!.getChargeList(unit.building_id , unit.unit_number)
            }
        }
    }
}