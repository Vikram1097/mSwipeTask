package com.example.mswipetask

data class Category(val id: Int, val name: String, val items: List<Item>, var isExpanded: Boolean = false)

data class Item(val id: Int, val name: String)

