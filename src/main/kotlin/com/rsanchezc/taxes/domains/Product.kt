package com.rsanchezc.taxes.domains

class Product(val id: Int, val name: String, val price: Double, val category: Category, val isImported: Boolean = false) {
    init {
        require(id > 0) { "id can not be negative or 0" }
        require(name.isNotEmpty()) { "name can not be empty" }
        require(price > 0.0 ) { "price can not be negative or 0" }
    }
}