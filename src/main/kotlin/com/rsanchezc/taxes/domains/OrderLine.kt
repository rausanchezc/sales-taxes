package com.rsanchezc.taxes.domains

import java.math.RoundingMode

class OrderLine(private val product: Product, var amount: Int = 1, private val taxes: Float) {
    var subTotal: Double
    var subTotalTaxes: Double

    init {
        require(amount > 0) { "amount has to be a positive number" }
        require(taxes >= 0.0F) { "taxes can not be negative number" }
        subTotal = calculateSubTotal()
        subTotalTaxes = calculateSubTotalTaxes()
    }

    fun setAmountTo(amount: Int) {
        require(amount > 0) { "amount has to be a positive number" }
        this.amount = amount
        calculateSubTotal()
        calculateSubTotalTaxes()
    }

    fun calculateSubTotal(): Double {
        val priceWithTaxes = (product.price + calculateProductTaxes()).round()
        subTotal = priceWithTaxes * amount
        return subTotal
    }

    fun calculateSubTotalTaxes(): Double {
        subTotalTaxes = (calculateProductTaxes() * amount)
        return subTotalTaxes
    }

    override fun toString(): String {
        return "$amount ${product.name}: $subTotal"
    }

    private fun calculateProductTaxes() = ((product.price * taxes) / 100).roundTaxes()
}

fun Double.round() = this.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
fun Double.roundTaxes() = Math.ceil(this*20.0)/20.0