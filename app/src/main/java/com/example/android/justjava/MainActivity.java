/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * increment
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You can't have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * decrement
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You can't have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox WhippedCreamCheckbox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = WhippedCreamCheckbox.isChecked();

        CheckBox ChocolateCheckbox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckbox.isChecked();

        EditText NameEditText = findViewById(R.id.name_edittext);
        String name = NameEditText.getText().toString();


        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:")); // only email apps should handle this
        email.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
        email.putExtra(Intent.EXTRA_TEXT, createOrderSummary(calculatePrice(quantity, 5, hasWhippedCream, hasChocolate), hasWhippedCream, hasChocolate, name));
        if (email.resolveActivity(getPackageManager()) != null) {
            startActivity(email);
        }

    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity        is the number of cups of coffee ordered
     * @param pricePerCup     cost of each cup of coffee
     * @param addWhippedCream is whether or not the user wants whipped cream toppings
     * @param addChocolate    is whether or not the user wants chocolate toppings
     * @return total price
     */
    private int calculatePrice(int quantity, int pricePerCup, boolean addWhippedCream, boolean addChocolate) {
        //add 1$ if the user wants whipped cream
        if (addWhippedCream) {
            pricePerCup += 1;
            //add 2$ if the user wants chocolate
        }
        if (addChocolate) {
            pricePerCup += 2;
        }
        return quantity * pricePerCup;
    }


    /**
     * this method create an order summery
     *
     * @param price is the cost of each cup of coffee
     */
    @SuppressLint({"StringFormatMatches", "StringFormatInvalid"})
    private String createOrderSummary(int price, boolean addWippedCream, boolean addChocolate, String nameOfClient) {
        @SuppressLint({"StringFormatInvalid", "LocalSuppress"}) String summary = getString(R.string.order_summary_name, nameOfClient);
        summary += getString(R.string.order_summary_whipped_cream, addWippedCream);
        summary += getString(R.string.order_summary_chocolate, addChocolate);
        summary += getString(R.string.order_summary_quantity, quantity);
        summary += getString(R.string.order_summary_price, price);
        summary += getString(R.string.thank_you);
        //displayMessage(summary);
        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }


//    /**
//     * This method displays the given price on the screen.
//     *
//     * @param message
//     */
//    private void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText((message));
//    }

}