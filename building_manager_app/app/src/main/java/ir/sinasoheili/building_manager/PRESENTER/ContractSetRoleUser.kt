package ir.sinasoheili.building_manager.PRESENTER

interface ContractSetRoleUser
{
    interface ContractSetRoleUserView
    {
        fun showToast(text:String)
        fun userAuthenticateSuccess(unitNumber:Int , buildingId:Int)
    }

    interface ContractSetRoleUserPresenter
    {
        fun authenticateUser(phone:String , buildingId:Int)
    }
}