package ir.sinasoheili.building_manager.MODEL

enum class ChargeStatus constructor(val text:String)
{
    paid("پرداخت شده") ,
    unpaid("پرداخت نشده");

    override fun toString(): String
    {
        return text
    }
}