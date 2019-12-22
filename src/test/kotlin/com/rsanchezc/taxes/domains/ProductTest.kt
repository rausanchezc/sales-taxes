package com.rsanchezc.taxes.domains

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProductTest {
    @Test
    fun `product name is empty`() {
        assertThrows<IllegalArgumentException> {
            Product(
                id = 1,
                name = "",
                price = 1.3,
                category = Category.FOOD
            )
        }
    }
}