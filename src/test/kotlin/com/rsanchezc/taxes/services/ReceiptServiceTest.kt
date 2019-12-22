package com.rsanchezc.taxes.services

import com.rsanchezc.taxes.domains.Category
import com.rsanchezc.taxes.domains.Product
import com.rsanchezc.taxes.domains.Receipt
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ReceiptServiceTest {
    private val receiptService: ReceiptService = ReceiptService(TaxesService())
    private var receipt = Receipt()

    @BeforeEach
    fun init() {
        receipt = Receipt()
    }

    @Test
    fun `INPUT 1`() {
        val book = Product(id = 1, name = "book", price = 12.49, category = Category.BOOKS)
        val musicCD = Product(id = 2, name = "music CD", price = 14.99, category = Category.OTHERS)
        val chocolateBar = Product(id = 3, name = "chocolate bar", price = 0.85, category = Category.FOOD)

        receiptService.addProduct(receipt, book)
        receiptService.addProduct(receipt, musicCD)
        receiptService.addProduct(receipt, chocolateBar)

        assertAll("receipt total and total taxes", {
            assertEquals(29.83, receipt.total)
            assertEquals(1.50, receipt.totalTaxes)
        })
    }

    @Test
    fun `INPUT 2` () {
        val importedBoxOfChocolates = Product(
            id = 1,
            name = "imported box of chocolates",
            price = 10.00,
            category = Category.FOOD,
            isImported = true
        )
        val importedBottleOfPerfume = Product(
            id = 2,
            name = "imported bottle of perfume",
            price = 47.50,
            category = Category.OTHERS,
            isImported = true
        )

        receiptService.addProduct(receipt, importedBoxOfChocolates)
        receiptService.addProduct(receipt, importedBottleOfPerfume)

        assertAll("receipt total and total taxes", {
            assertEquals(7.65, receipt.totalTaxes)
            assertEquals(65.15, receipt.total)
        })
    }

    @Test
    fun `INPUT 3` () {
        val importedBottleOfPerfume = Product(
            id = 1,
            name = "imported bottle of perfume",
            price = 27.99,
            category = Category.OTHERS,
            isImported = true
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
            category = Category.FOOD,
            isImported = true
        )

        receiptService.addProduct(receipt, importedBottleOfPerfume)
        receiptService.addProduct(receipt, bottleOfPerfume)
        receiptService.addProduct(receipt, pills)
        receiptService.addProduct(receipt, importedBoxOfChocolates)

        assertAll("receipt total and total taxes", {
            assertEquals(6.70, receipt.totalTaxes)
            assertEquals(74.68, receipt.total)
        })
    }

}