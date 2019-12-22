package com.rsanchezc.taxes.domains

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ReceiptTest {
    private var receipt = Receipt()

    @BeforeEach
    fun init() {
        receipt = Receipt()
    }

    @Test
    fun `add product in empty receipt`() {
        receipt.addProduct(
            product = Product(
                id = 1,
                name = "music CD",
                price = 14.99,
                category = Category.OTHERS
            ),
            taxes = 10.0F
        )

        assertEquals(16.49, receipt.total)
    }

    @Test
    fun `add same product twice in empty receipt`() {
        for(i in 1..2) {
            receipt.addProduct(
                product = Product(
                    id = 1,
                    name = "music CD",
                    price = 14.99,
                    category = Category.OTHERS
                ),
                taxes = 10.0F
            )
        }

        assertAll("receipt add 2 products", {
            assertEquals(32.98, receipt.total)
            assertEquals(3.0, receipt.totalTaxes)
        })
    }

    @Test
    fun `complex receipt INPUT 1` () {
        val book = Product(
            id = 1,
            name = "book",
            price = 12.49,
            category = Category.BOOKS
        )
        val musicCD = Product(
            id = 2,
            name = "music CD",
            price = 14.99,
            category = Category.OTHERS
        )
        val chocolateBar = Product(
            id = 3,
            name = "chocolate bar",
            price = 0.85,
            category = Category.FOOD
        )

        receipt.addProduct(product = book, taxes = 0.0F)
        receipt.addProduct(product = musicCD, taxes = 10.0F)
        receipt.addProduct(product = chocolateBar, taxes = 0.0F)

        assertAll("receipt total and total taxes", {
            assertEquals(29.83, receipt.total)
            assertEquals(1.50, receipt.totalTaxes)
        })
    }

    @Test
    fun `complex receipt INPUT 2` () {
        val importedBoxOfChocolates = Product(
            id = 1,
            name = "imported box of chocolates",
            price = 10.00,
            category = Category.FOOD
        )
        val importedBottleOfPerfume = Product(
            id = 2,
            name = "imported bottle of perfume",
            price = 47.50,
            category = Category.OTHERS
        )

        receipt.addProduct(importedBoxOfChocolates, 5.0F)
        receipt.addProduct(importedBottleOfPerfume, 15.0F)

        assertAll("receipt total and total taxes", {
            assertEquals(7.65, receipt.totalTaxes)
            assertEquals(65.15, receipt.total)
        })
    }

    @Test
    fun `complex receipt INPUT 3` () {
        val importedBottleOfPerfume = Product(
            id = 1,
            name = "imported bottle of perfume",
            price = 27.99,
            category = Category.OTHERS
        )
        val bottleOfPerfume = Product(
            id = 2,
            name = "bottle of perfume",
            price = 18.99,
            category = Category.OTHERS
        )
        val pills = Product(
            id = 3,
            name = "packet of headache pills",
            price = 9.75,
            category = Category.MEDICINES
        )
        val importedBoxOfChocolates = Product(
            id = 4,
            name = "box of imported chocolates",
            price = 11.25,
            category = Category.FOOD
        )

        receipt.addProduct(importedBottleOfPerfume, 15.0F)
        receipt.addProduct(bottleOfPerfume, 10.0F)
        receipt.addProduct(pills, 0.0F)
        receipt.addProduct(importedBoxOfChocolates, 5.0F)

        assertAll("receipt total and total taxes", {
            assertEquals(6.70, receipt.totalTaxes)
            assertEquals(74.68, receipt.total)
        })
    }
}