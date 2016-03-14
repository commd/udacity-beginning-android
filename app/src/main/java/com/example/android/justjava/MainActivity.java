package com.example.android.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 98;
    int cupPrice = 5;
    boolean hasWhippedCream = false;
    boolean hasCoco = false;
    EditText mName;
    String name;
    int whippedCreamPrice = 1;
    int chocolatePrice = 2;

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
        int price = cupPrice;

        if (hasWhippedCream) {
            price += whippedCreamPrice;
        }

        if (hasCoco) {
            price += chocolatePrice;
        }
        return (quantity * price);
    }

    /**
     * This method is called when the '+' button is clicked.
     */
    public void increment (View view) {
        Context context = getApplicationContext();
        CharSequence text = "Cannot order more than 100.";
        int duration = Toast.LENGTH_SHORT;

        Log.v("increment", "quantity = " + quantity);
        if (quantity >= 100) {
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        } else {
            quantity++;
            displayQuantity(quantity);
        }

    }

    /**
     * This method is called when the '-' button is clicked.
     */
    public void decrement (View view) {
        Context context = getApplicationContext();
        CharSequence text = "Cannot order less than zero.";
        int duration = Toast.LENGTH_SHORT;

        if (quantity <= 0) {
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        } else {
            quantity--;
            displayQuantity(quantity);
        }
    }

    /**
     * Returns  order summary
     * input parameter is the total price of order.
     */
    private String createOrderSummary (String name, int totalPrice, boolean hasWhippedCream, boolean hasCoco) {
        return ("Name: " + name + "\n" +
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
        mName = (EditText)findViewById(R.id.editText);
        name = mName.getText().toString();
        String orderSummary = createOrderSummary(name, total, hasWhippedCream, hasCoco);
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