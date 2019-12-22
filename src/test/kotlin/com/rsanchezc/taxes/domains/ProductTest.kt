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

    @Test
    fun `product price lower than 0` () {
        assertThrows<java.lang.IllegalArgumentException> {
            Product(
                id = 1,
                name = "sandwich",
                price = -1.0,
                category = Category.FOOD
            )
        }
    }

    @Test
    fun `product id negative number` () {
        assertThrows<java.lang.IllegalArgumentException> {
            Product(
                id = -1,
                name = "sandwich",
                price = 12.56,
                category = Category.FOOD
            )
        }
    }
}