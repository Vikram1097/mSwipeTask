package com.example.mswipetask

object SampleDataSource {
    fun getSampleCategories(): List<Category> {
        val category1 = Category(1, "Category 1", listOf(Item(11, "Item 1.1"), Item(12, "Item 1.2")))
        val category2 = Category(2, "Category 2", listOf(Item(21, "Item 2.1"), Item(22, "Item 2.2")))
        val category3 = Category(3, "Category 3", listOf(Item(31, "Item 3.1"), Item(32, "Item 3.2")))

        return listOf(category1, category2, category3)
    }
}
