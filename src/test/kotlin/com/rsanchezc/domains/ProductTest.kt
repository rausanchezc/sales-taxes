package com.rsanchezc.domains

import com.rsanchezc.domains.Category
import com.rsanchezc.domains.Product
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProductTest {
    @Test
    fun `product name is empty`() {
        assertThrows<IllegalArgumentException> {
            Product(id = 1, name = "", price = 1.3, category = Category.FOOD)
        }
    }
}