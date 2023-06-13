package com.example.finapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * The type Expense list adapter.
 * @Author Jacob jonas
 * @Date 6/10/23
 */
public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ExpenseViewHolder> {

    private List<String> expenseList;

    /**
     * Instantiates a new Expense list adapter.
     *
     * @param expenseList the expense list
     */
    public ExpenseListAdapter(List<String> expenseList) {
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        String expense = expenseList.get(position);
        holder.expenseTextView.setText(expense);
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    /**
     * The type Expense view holder.
     */
    public class ExpenseViewHolder extends RecyclerView.ViewHolder {

        /**
         * The Expense text view.
         */
        TextView expenseTextView;

        /**
         * Instantiates a new Expense view holder.
         *
         * @param itemView the item view
         */
        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            expenseTextView = itemView.findViewById(R.id.expenseTextView);
        }
    }
}
