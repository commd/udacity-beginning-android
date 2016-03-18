package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.lang.Object;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 2;
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
        return (getString(R.string.label_name) + " " + name + "\n" +
                getString(R.string.add_whipped_cream) + " " + hasWhippedCream + "\n" +
                getString(R.string.add_chocolate) + " " + hasCoco + "\n" +
                getString(R.string.label_quantity) + " " + quantity + "\n" +
                getString(R.string.label_total) + " " + Currency.getInstance(Locale.getDefault()).getSymbol() + totalPrice + "\n" +
                getString(R.string.thankyou));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int total = calculatePrice();
        mName = (EditText)findViewById(R.id.editText);
        name = mName.getText().toString();
        String orderSummary = createOrderSummary(name, total, hasWhippedCream, hasCoco);
//        displayMessage(orderSummary);

        /* Use below code for sending a intent to open a map */
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6, -122.3"));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//            Toast.makeText(this, "Start new intent activity.", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Error creating intent.", Toast.LENGTH_SHORT).show();
//        }

        String[] email_addresses = new String[30];
        email_addresses[0] = "vidscmd@netscape.net";
        email_addresses[1] = "chuie@pacbell.net";
        /* use below code for sending a intent to send an email. */
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, email_addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            Toast.makeText(this, "Start new intent activity.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error creating intent.", Toast.LENGTH_SHORT).show();
        }

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