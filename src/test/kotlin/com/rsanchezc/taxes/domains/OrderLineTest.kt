package com.rsanchezc.taxes.domains

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.assertAll

class OrderLineTest {
    private val musicCD = Product( id = 1, name = "music CD", price = 14.99, category = Category.OTHERS)
    private var orderLine = OrderLine(product = musicCD, taxes = 10.0F)

    @BeforeEach
    fun init() {
        orderLine = OrderLine(product = musicCD, taxes = 10.0F)
    }

    @Test
    fun `single product subtotal` () = assertEquals(16.49, orderLine.calculateSubTotal())

    @Test
    fun `single product subtotal taxes` () = assertEquals(1.50, orderLine.calculateSubTotalTaxes())

    @Test
    fun `print single product` () = assertEquals("1 music CD: 16.49", "$orderLine")

    @Test
    fun `print 2 products` () {
        val orderLine = OrderLine(product = musicCD, amount = 2, taxes = 10.0F)

        assertEquals("2 music CD: 32.98", "$orderLine")
    }

    @Test
    fun `set negative amount` () {
        assertThrows<IllegalArgumentException>{
            orderLine.setAmountTo(-1)
        }
    }

    @Test
    fun `set amount to 3`() {
        orderLine.setAmountTo(3)

        assertAll("order line",
            { assertEquals(49.47, orderLine.subTotal) },
            { assertEquals(4.5 , orderLine.subTotalTaxes) }
        )
    }
}