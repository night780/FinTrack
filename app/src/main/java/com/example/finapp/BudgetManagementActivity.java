package com.example.finapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The BudgetManagementActivity class is responsible for managing the budget in the application.
 * It allows the user to set a budget and save it to the SharedPreferences.
 * @Author Jacob jonas
 * @Date 6/10/23
 */
public class BudgetManagementActivity extends AppCompatActivity {

    private EditText budgetEditText; // EditText to enter the budget amount
    private Button setBudgetButton; // Button to set the budget
    private ImageButton backButton; // ImageButton to go back
    private SharedPreferences sharedPreferences; // SharedPreferences to store the budget

    private static final String SHARED_PREFS_FILE = "ExpenseTrackerPrefs"; // File name for SharedPreferences
    private static final String TOTAL_BUDGET_KEY = "totalBudget"; // Key for storing the total budget

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_management);

        budgetEditText = findViewById(R.id.budgetEditText); // Initialize the budget EditText
        setBudgetButton = findViewById(R.id.setBudgetButton); // Initialize the set budget button
        backButton = findViewById(R.id.backButton); // Initialize the back button
        sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, MODE_PRIVATE); // Initialize the SharedPreferences

        setBudgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBudget(); // Call the method to set the budget
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Call the method to go back
            }
        });
    }

    /**
     * Sets the budget based on the value entered in the budgetEditText.
     * Saves the budget to the SharedPreferences.
     */
    private void setBudget() {
        String budgetString = budgetEditText.getText().toString().trim(); // Get the budget value from the EditText
        if (!budgetString.isEmpty()) {
            float totalBudget = Float.parseFloat(budgetString); // Convert the budget value to float
            saveBudgetToSharedPreferences(totalBudget); // Save the budget to the SharedPreferences
            Toast.makeText(BudgetManagementActivity.this, "Budget set successfully!", Toast.LENGTH_SHORT).show(); // Show a toast message
            Intent intent = new Intent(BudgetManagementActivity.this, MainActivity.class);
            startActivity(intent); // Start the MainActivity
            finish(); // Finish the current activity
        } else {
            Toast.makeText(BudgetManagementActivity.this, "Please enter a valid budget value.", Toast.LENGTH_SHORT).show(); // Show a toast message
        }
    }

    /**
     * Saves the total budget to the SharedPreferences.
     *
     * @param totalBudget the total budget to be saved
     */
    private void saveBudgetToSharedPreferences(float totalBudget) {
        SharedPreferences.Editor editor = sharedPreferences.edit(); // Get the SharedPreferences editor
        editor.putFloat(TOTAL_BUDGET_KEY, totalBudget); // Put the total budget in SharedPreferences
        editor.apply(); // Apply the changes
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
