package com.rsanchezc.taxes.domains

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.assertAll

class OrderLineTest {

    @Test
    fun `single product subtotal` () {
        val product = Product(
            id = 1,
            name = "music CD",
            price = 14.99,
            category = Category.OTHERS
        )
        val orderLine = OrderLine(product = product, taxes = 10.0F)

        assertEquals(16.49, orderLine.calculateSubTotal())
    }

    @Test
    fun `single product subtotal taxes` () {
        val product = Product(
            id = 1,
            name = "music CD",
            price = 14.99,
            category = Category.OTHERS
        )
        val orderLine = OrderLine(product = product, taxes = 10.0F)

        assertEquals(1.50, orderLine.calculateSubTotalTaxes())
    }

    @Test
    fun `print single product` () {
        val product = Product(
            id = 1,
            name = "music CD",
            price = 14.99,
            category = Category.OTHERS
        )
        val orderLine = OrderLine(product = product, taxes = 10.0F)

        assertEquals("1 music CD: 16.49", "$orderLine")
    }

    @Test
    fun `print 2 products` () {
        val product = Product(
            id = 1,
            name = "music CD",
            price = 14.99,
            category = Category.OTHERS
        )
        val orderLine = OrderLine(product = product, amount = 2, taxes = 10.0F)

        assertEquals("2 music CD: 32.98", "$orderLine")
    }

    @Test
    fun `set negative amount` () {
        val orderLine = OrderLine(
            product = Product(
                id = 1,
                name = "music CD",
                price = 14.99,
                category = Category.OTHERS
            ),
            taxes = 10.0F
        )

        assertThrows<IllegalArgumentException>{
            orderLine.setAmountTo(-1)
        }
    }

    @Test
    fun `set amount to 3`() {
        val orderLine = OrderLine(
            product = Product(
                id = 1,
                name = "music CD",
                price = 14.99,
                category = Category.OTHERS
            ),
            taxes = 10.0F
        )

        orderLine.setAmountTo(3)

        assertAll("order line",
            { assertEquals(49.47, orderLine.subTotal) },
            { assertEquals(4.5 , orderLine.subTotalTaxes) }
        )
    }
}