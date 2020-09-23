package ir.sinasoheili.building_manager.MODEL

enum class ReceiptType constructor(val persianName : String)
{
    gas ("گاز"),
    water ("آب"),
    power ("برق");

    override fun toString():String
    {
        return persianName
    }
}