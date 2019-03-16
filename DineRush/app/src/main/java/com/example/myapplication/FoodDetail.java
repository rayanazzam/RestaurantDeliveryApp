package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.myapplication.Database.Database;
import com.example.myapplication.Objects.Food;
import com.example.myapplication.Objects.Order;
import com.example.myapplication.Objects.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {

    TextView foodName, foodPrice, foodDescription;
    ImageView foodImage;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    Button proceedToCartBtn;

    String foodId = "";

    Food currentFood;
    FirebaseDatabase database;
    DatabaseReference foodRef;

    User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //Firebase init
        foodRef = database.getInstance().getReference("Food");

        currentUser= (User) getIntent().getSerializableExtra("currentUser");
        //init view
        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);

        proceedToCartBtn = findViewById(R.id.proceed_to_cart);
        proceedToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodDetail.this, Cart.class);
                intent.putExtra("currentUser", currentUser);
                startActivity(intent);
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        currentFood.getName(),
                        numberButton.getNumber(),
                        currentFood.getPrice()
                        ));
                Toast.makeText(FoodDetail.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
        foodName = findViewById(R.id.food_name);

        foodDescription = findViewById(R.id.food_description);
        foodImage = findViewById(R.id.food_image);
        foodPrice = findViewById(R.id.food_price);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        if(getIntent() != null)
                foodId = getIntent().getStringExtra("foodId");
        if(!foodId.isEmpty() && foodId != null) {
            getDetailFood(foodId);
        }

    }

    private void getDetailFood(String foodId) {
        foodRef.child(foodId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentFood = dataSnapshot.getValue(Food.class);

                Picasso.with(getBaseContext()).load(currentFood.getImgSrc()).fit().into(foodImage);
                collapsingToolbarLayout.setTitle(currentFood.getName());
                foodPrice.setText(currentFood.getPrice());
                foodName.setText(currentFood.getName());
                foodDescription.setText(currentFood.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
