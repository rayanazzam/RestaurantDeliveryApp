package com.example.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Database.Database;
import com.example.myapplication.Objects.Order;
import com.example.myapplication.Objects.Requests;
import com.example.myapplication.Objects.User;
import com.example.myapplication.viewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;

    FirebaseDatabase database;
    DatabaseReference requestRef;

    TextView txtTotalPrice;
    Button btnPlace;

    double totalPrice;

    List<Order> cart;
    CartAdapter adapter;

    User currentUser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        currentUser = (User) getIntent().getSerializableExtra("currentUser");

        requestRef = database.getInstance().getReference("Requests");

        //Initialize
        recyclerView = findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        txtTotalPrice = findViewById(R.id.total);
        btnPlace = findViewById(R.id.btnPlaceOrder);

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalPrice == 0)
                    Toast.makeText(Cart.this, "Your cart is empty",Toast.LENGTH_SHORT).show();
                else
                    showAlertDialog();
            }
        });

        loadListFood();
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("One last step");
        alertDialog.setMessage("Enter your address");

        final EditText edtAddress = new EditText (Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams (
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress);
        alertDialog.setIcon(R.drawable.ic_cart);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                boolean validAddress = validateAddress(edtAddress.getText().toString());

                if(!validAddress) {
                    Toast.makeText(Cart.this,
                            "Please enter a valid address", Toast.LENGTH_SHORT).show();
                }

                else {
                    Requests request = new Requests(currentUser.getUsername(),
                            currentUser.getFirstName() + " " + currentUser.getLastName(),
                            edtAddress.getText().toString(),
                            txtTotalPrice.getText().toString(),
                            cart);
                    requestRef.child(String.valueOf(System.currentTimeMillis())).setValue(request);

                    //Delete Cart
                    new Database(getBaseContext()).cleanCart();
                    Toast.makeText(Cart.this,
                            "Thank you, your order has been received", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void loadListFood()  {
        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart, this);
        recyclerView.setAdapter(adapter);

        totalPrice = 0;
        for (Order order : cart)
            totalPrice+= (Double.parseDouble(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
        Locale locale = new Locale ("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        txtTotalPrice.setText(fmt.format(totalPrice));
    }
    public boolean validateAddress(String address) {
        if(address.length() < 10)
            return false;
        if(!address.matches("^[a-zA-Z0-9\\.\\s+]*$"))
            return false;
        else
            return true;
    }
}
