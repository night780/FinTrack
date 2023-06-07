package com.example.finapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The type Budget management activity.
 */
public class BudgetManagementActivity extends AppCompatActivity {

    private EditText budgetEditText;
    private Button saveButton;
    private ImageButton backButton;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_management);

        budgetEditText = findViewById(R.id.budgetEditText);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);

        sharedPreferences = getSharedPreferences("ExpenseTrackerPrefs", MODE_PRIVATE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String budgetString = budgetEditText.getText().toString();
                if (!budgetString.isEmpty()) {
                    float budget = Float.parseFloat(budgetString);
                    updateBudget(budget);
                    Toast.makeText(BudgetManagementActivity.this, "Budget saved successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(BudgetManagementActivity.this, "Please enter a valid budget amount", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void updateBudget(float budget) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("totalBudget", budget);
        editor.apply();
    }
}
