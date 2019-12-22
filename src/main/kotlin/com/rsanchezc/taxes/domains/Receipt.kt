package com.rsanchezc.taxes.domains

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