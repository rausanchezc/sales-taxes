package com.rsanchezc.taxes.services.interfaces

import com.rsanchezc.taxes.domains.Product

interface ITaxesService {
    fun calculateTaxes(product: Product): Float
}