package com.example.mswipetask



import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryList: List<Category>
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        categoryList = emptyList()
        categoryAdapter = CategoryAdapter(categoryList)
        recyclerView.adapter = categoryAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        // Observe categories LiveData
        viewModel.categories.observe(this) { categories ->
            categoryList = categories
            categoryAdapter.updateData(categoryList)
        }

        // Observe isLoading LiveData
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                // Show the progress bar
                progressBar.visibility = View.VISIBLE
            } else {
                // Hide the progress bar
                progressBar.visibility = View.GONE
            }
        }

        // Observe errorMessage LiveData
        viewModel.errorMessage.observe(this) { errorMessage ->
            // Display the error message to the user (e.g., show a Snackbar)
            Snackbar.make(recyclerView, errorMessage, Snackbar.LENGTH_LONG).show()
        }

        // Fetch data when needed (e.g., in onCreate or onResume)
        viewModel.fetchCategories()
    }
}
