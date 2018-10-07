package com.akash.healthcontroller;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import com.akash.healthcontroller.Foods.FoodItem;

public class FoodItem_Activity extends AppCompatActivity {

    ListView foodsListView;

    ArrayList<FoodItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_);

//            foodsListView = (ListView) findViewById(R.id.list_food);
//            foodsListView.setAdapter(new FoodAdapter(this));

        initialize_all();

    }

    private void initialize_all() {

        try {
            BaseAdapter adapter;
            arrayList = new ArrayList<FoodItem>();

            foodsListView = (ListView) findViewById(R.id.list_food);
            adapter = new BaseAdapter() {

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                @Override
                public int getCount() {
                    return arrayList.size();
                }

                @Override
                public Object getItem(int position) {
                    return arrayList.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View view, ViewGroup viewGroup) {

                    if(view == null){
                        view = inflater.inflate(R.layout.food_item_list, null);
                    }

                    TextView foodNameTextView = (TextView) view.findViewById(R.id.text_food_name);
                    TextView foodCalorieTextView = (TextView) view.findViewById(R.id.text_food_calorie);

                  foodNameTextView.setText(arrayList.get(position).getFood_name());
                    foodCalorieTextView.setText(arrayList.get(position).getFood_calorie());

                    return view;
                }
            };

            //set adapter
            foodsListView.setAdapter(adapter);

                Context context = getApplicationContext();
                Resources res = context.getResources();
                String[] foodNames = res.getStringArray(R.array.food_name);
                String[] foodCalories = res.getStringArray(R.array.food_calorie);

                for(int i = 0; i < foodNames.length; i++){
                    String foodName = foodNames[i];
                    String foodCalorie = foodCalories[i];
                    FoodItem item = new FoodItem(foodName, foodCalorie);
                    arrayList.add(item);
                }

            foodsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });


        }catch (Exception e){
            Toast.makeText(FoodItem_Activity.this, "Error is : "+e.getMessage(), Toast.LENGTH_LONG).show();
        }






    }

/*

    class FoodItem{
        String foodName;
        String foodCalorie;

        public FoodItem(String foodName, String foodCalorie) {
            super();
            this.foodName = foodName;
            this.foodCalorie = foodCalorie;
        }
    }
    class FoodAdapter extends BaseAdapter{
        ArrayList<FoodItem> arrayList;
        Context context;

        FoodAdapter(Context c) {
            context = c;
            arrayList = new ArrayList<FoodItem>();
            Resources res = context.getResources();
            String[] foodNames = res.getStringArray(R.array.food_name);
            String[] foodCalories = res.getStringArray(R.array.food_calorie);

            for (int i = 0; i < foodNames.length; i++) {
                String foodName = foodNames[i];
                String foodCalorie = foodCalories[i]+" Calories";
                arrayList.add(new FoodItem(foodName, foodCalorie));
            }


        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.food_item_list, viewGroup, false);

            TextView foodName = (TextView) row.findViewById(R.id.text_food_name);
            TextView foodCalorie = (TextView) row.findViewById(R.id.text_food_calorie);

            FoodItem temp = arrayList.get(position);

            foodName.setText(temp.foodName);
            foodCalorie.setText(temp.foodCalorie);

            return row;
        }
    }

*/

}
