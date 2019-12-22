package com.rsanchezc.taxes.services

import com.rsanchezc.taxes.domains.Product
import com.rsanchezc.taxes.domains.Receipt
import com.rsanchezc.taxes.services.interfaces.IReceiptService
import com.rsanchezc.taxes.services.interfaces.ITaxesService

class ReceiptService(private val taxesService: ITaxesService) : IReceiptService {
    override fun addProduct(receipt: Receipt, product: Product) {
        val taxes = taxesService.calculateTaxes(product)
        receipt.addProduct(product, taxes)
    }
}