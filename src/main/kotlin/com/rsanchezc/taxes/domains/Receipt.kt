package com.rsanchezc.taxes.domains

import com.rsanchezc.taxes.helpers.format

class Receipt {
    private val lines = hashMapOf<Int, OrderLine>()
    var total: Double = 0.0
    var totalTaxes: Double = 0.0

    fun addProduct(product: Product, taxes: Float) {
        if (lines.contains(product.id)) {
            val line = lines[product.id]
            line?.setAmountTo(line.amount+1)
            lines[product.id] = line!!
        } else {
            lines[product.id] = OrderLine(product = product, taxes = taxes)
        }

        calculateTotal()
        calculateTotalTaxes()
    }

    override fun toString(): String {
        return lines.values.map { "$it" }.reduce { acc, s ->  "$acc\n$s"} +
                "\nSales Taxes: ${totalTaxes.format()}" +
                "\nTotal: ${total.format()}"
    }

    private fun calculateTotal() {
        total = lines.values
            .map { line -> line.subTotal }
            .reduce { totalAcc, subtotal -> totalAcc + subtotal }
            .round()
    }

    private fun calculateTotalTaxes() {
        totalTaxes = lines.values
            .map { line -> line.subTotalTaxes }
            .reduce { totalAcc, subtotal -> totalAcc + subtotal }
            .round()
    }
}