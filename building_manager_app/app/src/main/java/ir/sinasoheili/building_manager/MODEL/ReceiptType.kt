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
            when(item)
            {
                0 -> return ReceiptType.gas
                1 -> return ReceiptType.water
                else -> return ReceiptType.power
            }
        }
    }

    override fun toString():String
    {
        return persianName
    }
}