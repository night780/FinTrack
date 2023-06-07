package com.example.finapp;

/**
 * The type Expense entry.
 */
public class ExpenseEntry {
    private String category;
    private double amount;

    /**
     * Instantiates a new Expense entry.
     *
     * @param category the category
     * @param amount   the amount
     */
    public ExpenseEntry(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }
}
