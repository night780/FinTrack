package com.example.finapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finapp.MainActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The ExpenseHistoryActivity class is responsible for displaying and managing the expense history.
 * It loads the expense list from SharedPreferences and allows the user to delete expenses.
 * @Author Jacob jonas
 * @Date 6/10/23
 */
public class ExpenseHistoryActivity extends AppCompatActivity {

    private ListView expenseHistoryListView; // ListView to display the expense history
    private ArrayAdapter<String> expenseListAdapter; // Adapter for the expense history ListView
    private ArrayList<String> expenseList; // List of expenses
    private ImageButton backButton; // ImageButton to go back

    private static final String SHARED_PREFS_FILE = "ExpenseHistorySharedPrefs"; // File name for SharedPreferences
    private static final String EXPENSE_LIST_KEY = "ExpenseList"; // Key for storing the expense list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_history);

        expenseHistoryListView = findViewById(R.id.expenseHistoryListView); // Initialize the expense history ListView
        backButton = findViewById(R.id.backButton); // Initialize the back button

        // Initialize the expense list
        expenseList = new ArrayList<>();

        // Create the adapter and set it to the ListView
        expenseListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseList);
        expenseHistoryListView.setAdapter(expenseListAdapter);

        // Load the expense list from SharedPreferences
        loadExpenseListFromSharedPreferences();

        // Register the ListView for a context menu
        registerForContextMenu(expenseHistoryListView);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Call the method to go back
            }
        });
    }

    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_expense_history, menu);

        // Assign a unique ID to the delete menu item
        MenuItem deleteItem = menu.findItem(R.id.deleteExpense);
        deleteItem.setOnMenuItemClickListener(item -> {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            int position = info.position;

            // Delete the selected expense from the list
            expenseList.remove(position);
            expenseListAdapter.notifyDataSetChanged();
            // Update the shared preferences with the updated expense list
            saveExpenseListToSharedPreferences();

            return true;
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Handle only the delete menu item selection
        if (item.getItemId() == R.id.deleteExpense) {
            return false; // Let the context menu callback handle it
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Loads the expense list from SharedPreferences and updates the ListView.
     */
    private void loadExpenseListFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        Set<String> expenseSet = sharedPreferences.getStringSet(EXPENSE_LIST_KEY, null);
        if (expenseSet != null) {
            expenseList.clear(); // Clear the list before adding new entries
            expenseList.addAll(expenseSet);
            if (expenseListAdapter != null) {
                expenseListAdapter.notifyDataSetChanged(); // Notify the adapter of the data change
            }
        }
    }

    /**
     * Saves the expense list to SharedPreferences.
     */
    private void saveExpenseListToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> expenseSet = new HashSet<>(expenseList);
        editor.putStringSet(EXPENSE_LIST_KEY, expenseSet); // Put the expense list in SharedPreferences
        editor.apply(); // Apply the changes
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Call the method to go back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Go back to the home screen
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Finish the current activity
    }
}
