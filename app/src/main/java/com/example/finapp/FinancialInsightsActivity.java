package com.example.finapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.Map;

public class FinancialInsightsActivity extends AppCompatActivity {

    private TextView totalBudgetTextView;
    private TextView remainingBudgetTextView;
    private TextView expenseTextView;
    private ImageButton backButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_insights);

        totalBudgetTextView = findViewById(R.id.totalBudgetTextView);
        remainingBudgetTextView = findViewById(R.id.remainingBudgetTextView);
        expenseTextView = findViewById(R.id.expenseTextView);
        backButton = findViewById(R.id.backButton);
        sharedPreferences = getSharedPreferences("ExpenseTrackerPrefs", MODE_PRIVATE);

        if (totalBudgetTextView != null && remainingBudgetTextView != null && expenseTextView != null) {
            displayBudgetInsights();
        }

        addPieChart();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Check if there is new data passed from the previous activity
        if (getIntent().hasExtra("newValue")) {
            float newValue = getIntent().getFloatExtra("newValue", 0.0f);
            addNewValueToHistory(newValue);
        }
    }

    private void displayBudgetInsights() {
        float totalBudget = sharedPreferences.getFloat("totalBudget", 0);

        Map<String, ?> expenseMap = sharedPreferences.getAll();

        float totalExpense = 0;
        for (Map.Entry<String, ?> entry : expenseMap.entrySet()) {
            if (entry.getKey().startsWith("expense_")) {
                float expenseAmount = Float.parseFloat(entry.getValue().toString());
                totalExpense += expenseAmount;
            }
        }

        float remainingBudget = totalBudget - totalExpense;

        totalBudgetTextView.setText(String.format("%.2f", totalBudget));
        remainingBudgetTextView.setText(String.format("%.2f", remainingBudget));
        expenseTextView.setText(String.format("%.2f", totalExpense));
    }

    private void addPieChart() {
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.addPieSlice(new PieModel("Remaining Budget", Float.parseFloat(remainingBudgetTextView.getText().toString()), getResources().getColor(R.color.colorPrimaryDark)));
        pieChart.addPieSlice(new PieModel("Expense", Float.parseFloat(expenseTextView.getText().toString()), getResources().getColor(R.color.colorAccent)));
        // Set the background color of the center of the pie chart
        pieChart.setInnerValueColor(getResources().getColor(R.color.colorAccent));
        pieChart.startAnimation();
    }

    private void addNewValueToHistory(float newValue) {
        // Example code to add the new value to the shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int expenseCount = sharedPreferences.getInt("expenseCount", 0);
        editor.putFloat("expense_" + expenseCount, newValue);
        editor.putInt("expenseCount", expenseCount + 1);
        editor.apply();

        // Update the budget insights and pie chart
        displayBudgetInsights();
        addPieChart();
    }
}
