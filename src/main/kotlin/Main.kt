fun main() {

    val transferToKopecks = 100
    println("Комиссия по переводу: "+getCommission(100000 * transferToKopecks)+" коп.")
    println("Комиссия по переводу: "+getCommission(200000 * transferToKopecks, 2000000 * transferToKopecks)+" коп.")
    println("Комиссия по переводу: "+getCommission(100000 * transferToKopecks, cardType = CardType.Mastercard)+" коп.")
    println("Комиссия по переводу: "+getCommission(200000 * transferToKopecks, 2000000 * transferToKopecks, cardType = CardType.Mastercard)+" коп.")
    println("Комиссия по переводу: "+getCommission(100000 * transferToKopecks, cardType = CardType.Maestro)+" коп.")
    println("Комиссия по переводу: "+getCommission(200000 * transferToKopecks, 2000000 * transferToKopecks, cardType = CardType.Maestro)+" коп.")
    println("Комиссия по переводу: "+getCommission(100000 * transferToKopecks, cardType = CardType.Visa)+" коп.")
    println("Комиссия по переводу: "+getCommission(200000 * transferToKopecks, 2000000 * transferToKopecks, cardType = CardType.Visa)+" коп.")
    println("Комиссия по переводу: "+getCommission(100000 * transferToKopecks, cardType = CardType.Mir)+" коп.")
    println("Комиссия по переводу: "+getCommission(200000 * transferToKopecks, 2000000 * transferToKopecks, cardType = CardType.Mir)+" коп.")
    println("Комиссия по переводу: "+getCommission(100000 * transferToKopecks, cardType = CardType.VkPay)+" коп.")
    println("Комиссия по переводу: "+getCommission(200000 * transferToKopecks, 2000000 * transferToKopecks, cardType = CardType.VkPay)+" коп.")

}

private fun getCommission(currentAmount: Int, previousAmount: Int = 0, cardType: CardType = CardType.VkPay): Int =
    when (cardType) {
        CardType.Mastercard, CardType.Maestro -> {
            val commissionMultiplier = 0.006
            val additionalCommission = 2_000
            val commissionAmountLimit = 7_500_000
            val wholeMonthAmount = currentAmount + previousAmount
            if (wholeMonthAmount < commissionAmountLimit) 0 else (currentAmount * commissionMultiplier).toInt() + additionalCommission
        }
        CardType.Visa, CardType.Mir -> {
            val commissionMultiplier = 0.0075
            val minCommissionAmount = 3_500
            val commissionFromAmount = (currentAmount * commissionMultiplier).toInt()
            if (commissionFromAmount >= minCommissionAmount) commissionFromAmount else minCommissionAmount
        }
        CardType.VkPay -> 0
    }

private enum class CardType {
    Mastercard, Maestro, Visa, Mir, VkPay
}