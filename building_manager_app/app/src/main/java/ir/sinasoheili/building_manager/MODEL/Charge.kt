package ir.sinasoheili.building_manager.MODEL

class Charge constructor (amount:Double, status: ChargeStatus = ChargeStatus.unpaid, issue_date:String, manager_id:Int, building_id:Int, unit_number:Int)
{
    constructor(amount:Double, status: ChargeStatus = ChargeStatus.unpaid, issue_date:String, pay_date:String, manager_id:Int, building_id:Int, unit_number:Int):this(amount , status , issue_date , manager_id , building_id , unit_number)
    {
        this.pay_date = pay_date
    }

    var id:Int = -1
    get() = field
    set(value)
    {
        field = value
    }

    var amount : Double = amount
    get() = field
    set(value)
    {
        if(value >= 0 )
        {
            field = amount
        }
    }

    var status : Int = status.ordinal
    get() = field
    set(value)
    {
        field = value
    }

    var issue_date : String = issue_date
    get() = field
    set(value)
    {
        field = value
    }

    var manager_id : Int = manager_id
    get() = field
    set(value)
    {
        field = value
    }

    var building_id : Int = building_id
    get() = field
    set(value)
    {
        field = value
    }

    var unit_number : Int = unit_number
    get() = field
    set(value)
    {
        field = value
    }

    var pay_date : String = ""
    get() = field
    set(value)
    {
        field = value
    }

    override fun toString(): String
    {
        return "id:$id | amount:$amount | status:$status | issue_date:$issue_date | manager_id:$manager_id | building_id:$building_id | unit_number:$unit_number | pay_date:$pay_date"
    }
}