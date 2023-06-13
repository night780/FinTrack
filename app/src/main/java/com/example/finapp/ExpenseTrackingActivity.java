package com.example.finapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Expense tracking activity.
 * @Author Jacob jonas
 * @Date 6/10/23
 */
public class ExpenseTrackingActivity extends AppCompatActivity {

    private EditText amountEditText;
    private EditText dateEditText;
    private Spinner categorySpinner;
    private Button addExpenseButton;

    private ImageButton backButton;

    private ArrayList<String> expenseList;

    private static final String SHARED_PREFS_FILE = "ExpenseHistorySharedPrefs";
    private static final String EXPENSE_LIST_KEY = "ExpenseList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_tracking);

        amountEditText = findViewById(R.id.amountEditText);
        dateEditText = findViewById(R.id.dateEditText);
        categorySpinner = findViewById(R.id.categorySpinner);
        addExpenseButton = findViewById(R.id.addExpenseButton);
        backButton = findViewById(R.id.backButton);

        // Set up the category spinner with sample categories
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getSampleCategories());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpense();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        expenseList = new ArrayList<>();
    }


    private void addExpense() {
        String amount = amountEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String category = categorySpinner.getSelectedItem().toString();

        // Validate the expense amount
        if (amount.isEmpty()) {
            amountEditText.setError("Please enter an amount");
            return;
        }

        // Add the expense to the list
        String expense = "Amount: " + amount + " | Date: " + date + " | Category: " + category;
        expenseList.add(expense);

        // Save the updated expense list to shared preferences
        saveExpenseListToSharedPreferences(Float.parseFloat(amount));

        // Clear the input fields
        amountEditText.getText().clear();
        dateEditText.getText().clear();

        // Show a success message
        showToast("Expense added successfully");

        // Start FinancialInsightsActivity with the new expense value
        Intent intent = new Intent(ExpenseTrackingActivity.this, FinancialInsightsActivity.class);
        intent.putExtra("newValue", Float.parseFloat(amount));
        startActivity(intent);


        finish();
    }

    private void saveExpenseListToSharedPreferences(float newExpenseValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> expenseSet = new HashSet<>(expenseList);
        editor.putStringSet(EXPENSE_LIST_KEY, expenseSet);
        editor.putFloat("NewExpenseValue", newExpenseValue);
        editor.apply();
    }


    private ArrayList<String> getSampleCategories() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Food");
        categories.add("Transportation");
        categories.add("Shopping");
        categories.add("Entertainment");
        return categories;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}