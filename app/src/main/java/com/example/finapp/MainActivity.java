package com.example.finapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is the main menu of the app.
 *
 *
 * @Author Jacob jonas
 * @Date 6/10/23
 */
public class MainActivity extends AppCompatActivity {

    private Button expenseTrackerButton;
    private Button budgetManagementButton;
    private Button financialInsightsButton;
    private Button expenseHistoryButton;
    private TextView totalBudgetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseTrackerButton = findViewById(R.id.expenseTrackerButton);
        budgetManagementButton = findViewById(R.id.budgetManagementButton);
        financialInsightsButton = findViewById(R.id.financialInsightsButton);
        expenseHistoryButton = findViewById(R.id.expenseHistoryButton);
        totalBudgetTextView = findViewById(R.id.totalBudgetTextView);


        expenseTrackerButton.setTextSize(22);
        budgetManagementButton.setTextSize(22);
        financialInsightsButton.setTextSize(22);
        expenseHistoryButton.setTextSize(22);




        expenseTrackerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExpenseTrackingActivity.class);
                startActivity(intent);
            }
        });

        budgetManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BudgetManagementActivity.class);
                startActivity(intent);
            }
        });

        financialInsightsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FinancialInsightsActivity.class);
                startActivity(intent);
            }
        });

        expenseHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExpenseHistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
