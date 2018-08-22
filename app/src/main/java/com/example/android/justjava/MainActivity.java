/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * decrement
     */
    public void decrement(View view) {
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


        createOrderSummary(calculatePrice(quantity, 5, hasWhippedCream, hasChocolate), hasWhippedCream, hasChocolate, name);

    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity    is the number of cups of coffee ordered
     * @param pricePerCup cost of each cup of coffee
     */
    private int calculatePrice(int quantity, int pricePerCup, boolean addWhippedCream, boolean addChocolate) {

        if (addWhippedCream) {
            pricePerCup += 1;
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
    private String createOrderSummary(int price, boolean addWippedCream, boolean addChocolate, String nameOfClient) {
        String summary = "name: " + nameOfClient + "\nAdd whipped cream? " + addWippedCream + "\nAdd Chocolate? " + addChocolate + "\nquantity: " + quantity + "\nTotal: " + price;
        summary += "\nThank You!";
        displayMessage(summary);
        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }


    /**
     * This method displays the given price on the screen.
     *
     * @param message
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText((message));
    }

}