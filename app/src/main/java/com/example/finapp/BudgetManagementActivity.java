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

public class BudgetManagementActivity extends AppCompatActivity {

    private EditText budgetEditText;
    private Button setBudgetButton;
    private ImageButton backButton;
    private SharedPreferences sharedPreferences;

    private static final String SHARED_PREFS_FILE = "ExpenseTrackerPrefs";
    private static final String TOTAL_BUDGET_KEY = "totalBudget";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_management);

        budgetEditText = findViewById(R.id.budgetEditText);
        setBudgetButton = findViewById(R.id.setBudgetButton);
        backButton = findViewById(R.id.backButton);
        sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, MODE_PRIVATE);

        setBudgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBudget();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setBudget() {
        String budgetString = budgetEditText.getText().toString().trim();
        if (!budgetString.isEmpty()) {
            float totalBudget = Float.parseFloat(budgetString);
            saveBudgetToSharedPreferences(totalBudget);
            Toast.makeText(BudgetManagementActivity.this, "Budget set successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BudgetManagementActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(BudgetManagementActivity.this, "Please enter a valid budget value.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveBudgetToSharedPreferences(float totalBudget) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(TOTAL_BUDGET_KEY, totalBudget);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
