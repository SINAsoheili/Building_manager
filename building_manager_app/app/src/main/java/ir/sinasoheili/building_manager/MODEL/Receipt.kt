package ir.sinasoheili.building_manager.MODEL

class Receipt constructor(type:ReceiptType , pay_date:String , issue_date:String , amount:Double , id_receipt:String , id_payment:String , building_id:Int)
{
    var id : Int = -1
    get() = field
    set(value)
    {
        field = value
    }

    var type : Int = type.ordinal
    get() = field
    set(value)
    {
        field = value
    }

    var pay_date : String = pay_date
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

    var amount : Double = amount
    get() = field
    set(value)
    {
        field = value
    }

    var id_receipt : String = id_receipt
    get() = field
    set(value)
    {
        field = value
    }

    var id_payment : String = id_payment
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

    override fun toString():String
    {
        return "id:$id | type:$type | pay_date:$pay_date | issue_date:$issue_date | amount:$amount | id_receipt:$id_receipt | id_payment:$id_payment | building_id:$building_id"
    }
}