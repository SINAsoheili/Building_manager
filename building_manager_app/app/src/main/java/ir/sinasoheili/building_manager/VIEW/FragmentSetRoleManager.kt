package ir.sinasoheili.building_manager.VIEW

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ir.sinasoheili.building_manager.PRESENTER.ContractSetRoleManager
import ir.sinasoheili.building_manager.PRESENTER.PresenterSetRoleManager
import ir.sinasoheili.building_manager.R

class FragmentSetRoleManager : Fragment() , ContractSetRoleManager.ContractSetRoleManagerView , View.OnClickListener
{
    private var etPhone : EditText? = null
    private var tilPhone : TextInputLayout? = null
    private var etPasswd : EditText? = null
    private var tilPasswd : TextInputLayout? = null
    private var etPasswdConfirm : EditText? = null
    private var tilPasswdConfirm : TextInputLayout? = null
    private var btnSubmit : Button? = null
    private var progressBar : ProgressBar? = null

    private var presenter : PresenterSetRoleManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_setrole_manager , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        initObj(view)
    }

    private fun initObj(view:View)
    {
        etPhone = view.findViewById(R.id.et_fragment_setRole_manager_phone)
        tilPhone = view.findViewById(R.id.til_fragment_setRole_manager_phone)

        etPasswd = view.findViewById(R.id.et_fragment_setRole_manager_passwd)
        tilPasswd = view.findViewById(R.id.til_fragment_setRole_manager_passwd)

        etPasswdConfirm = view.findViewById(R.id.et_fragment_setRole_manager_passwd_confirm)
        tilPasswdConfirm = view.findViewById(R.id.til_fragment_setRole_manager_passwd_confirm)

        btnSubmit = view.findViewById(R.id.btn_fragment_setRole_submit)
        btnSubmit!!.setOnClickListener(this)

        presenter = PresenterSetRoleManager(this)

        progressBar = view.findViewById(R.id.pb_fragment_setRole_progressBar)
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            btnSubmit ->
            {
                if(checkPhone() && checkPasswordEqual())
                {
                    val phone : String = etPhone!!.text.toString()
                    val passwd : String = etPasswd!!.text.toString()

                    visibleProgressBar()

                    presenter!!.registerManager(context!! , phone , passwd)
                }
            }
        }
    }

    private fun checkPhone() : Boolean
    {
        val phone:String = etPhone!!.text.toString()
        if((phone.length != 11) || phone.isEmpty())
        {
            tilPhone?.error = getString(R.string.fill_field)
            etPhone?.requestFocus()
            return false
        }
        tilPhone?.isErrorEnabled = false
        return true
    }

    private fun checkPasswd() : Boolean
    {
        val passwd : String = etPasswd!!.text.toString()
        if(passwd.isEmpty())
        {
            tilPasswd?.error = getString(R.string.fill_field)
            etPasswd?.requestFocus()
            return false
        }
        tilPasswd?.isErrorEnabled = false
        return true
    }

    private fun checkPasswdConfirm() : Boolean
    {
        val passwd : String = etPasswdConfirm!!.text.toString()
        if(passwd.isEmpty())
        {
            tilPasswdConfirm?.error = getString(R.string.fill_field)
            etPasswdConfirm?.requestFocus()
            return false
        }
        tilPasswdConfirm?.isErrorEnabled = false
        return true
    }

    private fun checkPasswordEqual() : Boolean
    {
        if(checkPasswd() && checkPasswdConfirm())
        {
            val passwd : String = etPasswd!!.text.toString()
            val passwdConfirm : String = etPasswdConfirm!!.text.toString()

            if(passwd.equals(passwdConfirm))
            {
                tilPasswdConfirm?.isErrorEnabled = false
                return true
            }
            else
            {
                tilPasswdConfirm?.error = getString(R.string.invalid)
                etPasswdConfirm?.requestFocus()
            }
        }

        return false
    }

    override fun showToast(text: String)
    {
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    override fun moveToBuildingListActivity()
    {
        fragmentManager!!.beginTransaction().remove(this).commit()
        val intent : Intent = Intent(context , ManagerBuildingListActivity::class.java)
        startActivity(intent)
    }

    override fun goneProgressBar() {
        progressBar!!.visibility = ProgressBar.GONE
    }

    private fun visibleProgressBar() {
        progressBar!!.visibility = ProgressBar.VISIBLE
    }
}