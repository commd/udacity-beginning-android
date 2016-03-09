package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 2;
    int cupPrice = 5;
    boolean hasWhippedCream = false;
    boolean hasCoco = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        int price = quantity * cupPrice;
        return price;
    }

    /**
     * This method is called when the '+' button is clicked.
     */
    public void increment (View view) {
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the '-' button is clicked.
     */
    public void decrement (View view) {
        quantity--;
        displayQuantity(quantity);
    }

    /**
     * Returns  order summary
     * input parameter is the total price of order.
     */
    private String createOrderSummary (int totalPrice, boolean hasWhippedCream, boolean hasCoco) {
        return ("Name: Coffee Customer\n" +
                "Add whipped cream? " + hasWhippedCream + "\n" +
                "Add chocolate? " + hasCoco + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: $" + totalPrice + "\n" +
                "Thank you!");
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int total = calculatePrice();
        String orderSummary = createOrderSummary(total, hasWhippedCream, hasCoco);
        displayMessage(orderSummary);
    }

    /**
     * This method is called when the checkBox button is clicked.
     */
    public void onCheckBoxClicked (View view) {
        hasWhippedCream = ((CheckBox) view).isChecked();
    }

    /**
     * This method is called when the chocolate checkBox button is clicked.
     */
    public void onCocoCheckBoxClicked (View view) {
        hasCoco = ((CheckBox) view).isChecked();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberX) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberX);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}