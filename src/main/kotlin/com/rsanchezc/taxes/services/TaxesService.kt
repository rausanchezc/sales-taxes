package com.rsanchezc.taxes.services

import com.rsanchezc.taxes.domains.Category
import com.rsanchezc.taxes.domains.Product
import com.rsanchezc.taxes.services.interfaces.ITaxesService

class TaxesService: ITaxesService {
    override fun calculateTaxes(product: Product):Float {
        return when(product.category) {
            Category.MEDICINES, Category.FOOD, Category.BOOKS -> 0.0F
            Category.OTHERS -> 10.0F
        } + if (product.isImported) {  5.0F } else { 0.0F }
    }
}