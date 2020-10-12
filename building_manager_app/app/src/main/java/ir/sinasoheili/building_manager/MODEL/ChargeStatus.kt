package ir.sinasoheili.building_manager.MODEL

enum class ChargeStatus constructor(val text:String)
{
    unpaid("پرداخت نشده"),
    paid("پرداخت شده") ;

    companion object
    {
        fun getChargeStatus(id:Int):ChargeStatus?
        {
            return when (id)
            {
                0 -> ChargeStatus.unpaid
                1 -> ChargeStatus.paid
                else -> null
            }
        }
    }

    override fun toString(): String
    {
        return text
    }
}