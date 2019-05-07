package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.example.myapplication.Interface.ItemClickListener;
import com.example.myapplication.Objects.Food;
import com.example.myapplication.Objects.User;
import com.example.myapplication.viewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference foodRef;
    User currentUser;

    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;
    String categoryId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        currentUser = (User) getIntent().getSerializableExtra("currentUser");

        // instantiate database reference
        foodRef = database.getInstance().getReference("Food");

        recyclerView = findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() != null) {
            categoryId = getIntent().getStringExtra("categoryId");

            if( categoryId != null && !categoryId.isEmpty()) {
                Log.i("category id is ", categoryId);
                loadListFood(categoryId);
            }
        }
    }

    private void loadListFood(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(
                Food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodRef.orderByChild("menuId").equalTo(categoryId)
        ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
                viewHolder.foodName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImgSrc())
                        .fit()
                        .into(viewHolder.foodImage);
                final Food local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(FoodList.this, FoodDetail.class);
                        intent.putExtra("foodId", adapter.getRef(position).getKey());
                        intent.putExtra("currentUser", currentUser);
                        startActivity(intent);
                    }
                });
            }
        };

        recyclerView.setAdapter(adapter);
    }
}
