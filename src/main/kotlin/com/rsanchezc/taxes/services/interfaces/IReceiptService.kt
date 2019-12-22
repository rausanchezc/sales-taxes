package com.rsanchezc.taxes.services.interfaces

import com.rsanchezc.taxes.domains.Product
import com.rsanchezc.taxes.domains.Receipt

interface IReceiptService {
    fun addProduct(receipt: Receipt, product: Product)
}