package com.example.yungpakhongpatrick.mapd726_project

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListViewModelTest {

    // This rule makes LiveData run synchronously on the test thread
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ListViewModel

    private fun makeList(id: Long, name: String, items: List<SavedItem> = emptyList()) =
        SavedList(id, "backend_$id", name, "Apr 9, 2026", items)

    private fun makeItem(name: String, price: Double = 1.0) =
        SavedItem(name, price, "Walmart", false)

    @Before
    fun setup() {
        viewModel = ListViewModel()
    }

    @Test
    fun initialState_listsAreEmpty() {
        assertTrue(viewModel.allShoppingLists.value?.isEmpty() == true)
    }

    @Test
    fun addList_appearsInLists() {
        viewModel.addList(makeList(1L, "Weekly Shop"))
        assertEquals(1, viewModel.allShoppingLists.value?.size)
    }

    @Test
    fun addList_correctNameStored() {
        viewModel.addList(makeList(1L, "Weekly Shop"))
        assertEquals("Weekly Shop", viewModel.allShoppingLists.value?.first()?.name)
    }

    @Test
    fun addDuplicateList_doesNotDouble() {
        viewModel.addList(makeList(1L, "Weekly Shop"))
        viewModel.addList(makeList(1L, "Weekly Shop Updated"))
        assertEquals(1, viewModel.allShoppingLists.value?.size)
    }

    @Test
    fun removeList_removesCorrectly() {
        viewModel.addList(makeList(1L, "List A"))
        viewModel.addList(makeList(2L, "List B"))
        viewModel.removeList(1L)
        assertEquals(1, viewModel.allShoppingLists.value?.size)
        assertEquals("List B", viewModel.allShoppingLists.value?.first()?.name)
    }

    @Test
    fun updateItemChecked_updatesCorrectItem() {
        val items = listOf(makeItem("Milk"), makeItem("Bread"))
        viewModel.addList(makeList(1L, "Groceries", items))
        viewModel.updateItemChecked(1L, 0, true)
        val updated = viewModel.allShoppingLists.value?.first()?.items?.get(0)
        assertTrue(updated?.isChecked == true)
    }

    @Test
    fun getListById_returnsCorrectList() {
        viewModel.addList(makeList(1L, "List A"))
        viewModel.addList(makeList(2L, "List B"))
        val result = viewModel.getListById(2L)
        assertEquals("List B", result?.name)
    }

    @Test
    fun getListById_returnsNullForMissingId() {
        assertNull(viewModel.getListById(999L))
    }

    @Test
    fun clearAllLists_emptiesEverything() {
        viewModel.addList(makeList(1L, "List A"))
        viewModel.addList(makeList(2L, "List B"))
        viewModel.clearAllLists()
        assertTrue(viewModel.allShoppingLists.value?.isEmpty() == true)
    }
}