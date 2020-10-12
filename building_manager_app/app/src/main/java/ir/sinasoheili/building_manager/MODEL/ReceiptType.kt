package ir.sinasoheili.building_manager.MODEL

enum class ReceiptType constructor(val persianName : String)
{
    gas ("گاز"),
    water ("آب"),
    power ("برق");

    companion object
    {
        fun getReceipt(item:Int):ReceiptType
        {
            return when(item)
            {
                0 -> ReceiptType.gas
                1 -> ReceiptType.water
                else -> ReceiptType.power
            }
        }
    }

    override fun toString():String
    {
        return persianName
    }
}