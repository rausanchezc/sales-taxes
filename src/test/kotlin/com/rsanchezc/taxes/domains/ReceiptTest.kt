package com.rsanchezc.taxes.domains

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ReceiptTest {
    private var receipt = Receipt()
    private val productsInput1 = listOf(
        Product(id = 1, name = "book", price = 12.49, category = Category.BOOKS),
        Product(id = 2, name = "music CD", price = 14.99, category = Category.OTHERS),
        Product(id = 3, name = "chocolate bar", price = 0.85, category = Category.FOOD)
    )
    private val productsInput2 = listOf(
        Product(id = 4, name = "imported box of chocolates", price = 10.00, category = Category.FOOD, isImported = true),
        Product(id = 5, name = "imported bottle of perfume", price = 47.50, category = Category.OTHERS, isImported = true)
    )
    private val productsInput3 = listOf(
        Product( id = 6, name = "imported bottle of perfume", price = 27.99, category = Category.OTHERS, isImported = true),
        Product( id = 7, name = "bottle of perfume", price = 18.99, category = Category.OTHERS),
        Product( id = 8, name = "packet of headache pills", price = 9.75, category = Category.MEDICINES),
        Product( id = 9, name = "imported box of chocolates", price = 11.25, category = Category.FOOD, isImported = true)
    )

    @BeforeEach
    fun init() {
        receipt = Receipt()
    }

    @Test
    fun `add product in empty receipt`() {
        val musicCD = productsInput1[1]

        receipt.addProduct(musicCD, taxes = 10.0F)

        assertEquals(16.49, receipt.total)
    }

    @Test
    fun `add same product twice in empty receipt`() {
        val musicCD = productsInput1[1]
        for (i in 1..2) {
            receipt.addProduct(product = musicCD, taxes = 10.0F)
        }

        assertAll("receipt add 2 productsInput1", {
            assertEquals(32.98, receipt.total)
            assertEquals(3.0, receipt.totalTaxes)
        })
    }

    @Test
    fun `complex receipt INPUT 1`() {
        val book = productsInput1[0]
        val musicCD = productsInput1[1]
        val chocolateBar = productsInput1[2]

        receipt.addProduct(product = book, taxes = 0.0F)
        receipt.addProduct(product = musicCD, taxes = 10.0F)
        receipt.addProduct(product = chocolateBar, taxes = 0.0F)

        assertAll("receipt total and total taxes", {
            assertEquals(29.83, receipt.total)
            assertEquals(1.50, receipt.totalTaxes)
        })
    }

    @Test
    fun `complex receipt INPUT 1 print`() {
        val book = productsInput1[0]
        val musicCD = productsInput1[1]
        val chocolateBar = productsInput1[2]

        receipt.addProduct(product = book, taxes = 0.0F)
        receipt.addProduct(product = musicCD, taxes = 10.0F)
        receipt.addProduct(product = chocolateBar, taxes = 0.0F)

        val output1 = "1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83"

        assertEquals(output1, "$receipt")
    }

    @Test
    fun `complex receipt INPUT 2`() {
        val importedBoxOfChocolates = productsInput2[0]
        val importedBottleOfPerfume = productsInput2[1]

        receipt.addProduct(importedBoxOfChocolates, 5.0F)
        receipt.addProduct(importedBottleOfPerfume, 15.0F)

        assertAll("receipt total and total taxes", {
            assertEquals(7.65, receipt.totalTaxes)
            assertEquals(65.15, receipt.total)
        })
    }

    @Test
    fun `complex receipt INPUT 2 print`() {
        val importedBoxOfChocolates = productsInput2[0]
        val importedBottleOfPerfume = productsInput2[1]

        receipt.addProduct(importedBoxOfChocolates, 5.0F)
        receipt.addProduct(importedBottleOfPerfume, 15.0F)

        val output2 = "1 imported box of chocolates: 10.50\n1 imported bottle of perfume: 54.65\nSales Taxes: 7.65\nTotal: 65.15"

        assertEquals(output2, "$receipt")
    }

    @Test
    fun `complex receipt INPUT 3`() {
        val importedBottleOfPerfume = productsInput3[0]
        val bottleOfPerfume = productsInput3[1]
        val pills = productsInput3[2]
        val importedBoxOfChocolates = productsInput3[3]

        receipt.addProduct(importedBottleOfPerfume, 15.0F)
        receipt.addProduct(bottleOfPerfume, 10.0F)
        receipt.addProduct(pills, 0.0F)
        receipt.addProduct(importedBoxOfChocolates, 5.0F)

        assertAll("receipt total and total taxes", {
            assertEquals(6.70, receipt.totalTaxes)
            assertEquals(74.68, receipt.total)
        })
    }

     @Test
    fun `complex receipt INPUT 3 print`() {
        val importedBottleOfPerfume = productsInput3[0]
        val bottleOfPerfume = productsInput3[1]
        val pills = productsInput3[2]
        val importedBoxOfChocolates = productsInput3[3]

        receipt.addProduct(importedBottleOfPerfume, 15.0F)
        receipt.addProduct(bottleOfPerfume, 10.0F)
        receipt.addProduct(pills, 0.0F)
        receipt.addProduct(importedBoxOfChocolates, 5.0F)

         val output3 = "1 imported bottle of perfume: 32.19" +
                 "\n1 bottle of perfume: 20.89" +
                 "\n1 packet of headache pills: 9.75" +
                 "\n1 imported box of chocolates: 11.85" +
                 "\nSales Taxes: 6.70" +
                 "\nTotal: 74.68"

         assertEquals(output3, "$receipt")
    }

}