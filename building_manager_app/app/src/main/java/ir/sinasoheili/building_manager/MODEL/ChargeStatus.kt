package ir.sinasoheili.building_manager.MODEL

enum class ChargeStatus constructor(val text:String)
{
    unpaid("پرداخت نشده"),
    paid("پرداخت شده") ;

    override fun toString(): String
    {
        return text
    }
}