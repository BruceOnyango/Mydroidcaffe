package com.bruce.dice.mydroidcaffe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/* Step 1
Create a Recipe Adapter that extends RecyclerView.Adapter and uses the RecyclerView.ViewHolder.class
 */
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
    //Step 3.0 Declare the private member variables Recipe Data and the context
    private final ArrayList<Store> storeData;
    private final Context myContext;

    /* Step 3.1
    - Create a constructor to pass in the recipe data and the context
     */
    StoreAdapter(ArrayList<Store> StoreData, Context context) {
        //Initialize the objects
        this.myContext = context;
        this.storeData = StoreData;
    }

    /*Step 1.1 Required
   - Implement the method onCreateViewHolder for creating objects
   - @param parent the view group of which the view object will be added after it is bound to the adapter position
   - @param viewType viewType of the new view
   - @return the newly created viewHolder
    */

    @NonNull
    @Override

    public StoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Step 4.1 Create a new viewHolder
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.activity_store, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Step 5: Get the current view object using its position and populate it with data
        Store currentStore = storeData.get(position);

        //Step 5.1: Populate the current view with data
        holder.bindTo(currentStore);
    }
    /*Step 1.2 Required for binding the view to the data
    - @param viewHolder which the data should be put
    - @position the adapter position
     */


    /*Step 1.3 Required for getting the size of the data set
    - @return the size of the data set
     */

    @Override
    public int getItemCount() {
        // Step 4.0
        return storeData.size();
    }
    /*Step 2 Create the ViewHolder class that represents each and every row in the recycler view

     */

    public class ViewHolder extends RecyclerView.ViewHolder {
        /*Step 2.1
        - Create a constructor for the ViewHolder used in onCreateViewHolder() method
        - @param itemView rootView of the recipe_list_item layout
         */

        //Step 2.2 Declare the private member variable that the viewHolder will hold
        private final ImageView myStoreImage;
        private final TextView myStoreTitle;
        private final TextView myStoreDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myStoreImage = itemView.findViewById(R.id.galleria);
            myStoreTitle = itemView.findViewById(R.id.area_title);
            myStoreDescription = itemView.findViewById(R.id.area_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int storePosition = getAdapterPosition();
                    Store currentStore = storeData.get(storePosition);
                    if (storePosition == 0) {
                        Intent storeIntent = new Intent(myContext, StoreActivity.class);
                        storeIntent.putExtra("dTitle", currentStore.getStoreTitle());
                        storeIntent.putExtra("dImage", currentStore.getStoreImage());
                        storeIntent.putExtra("dDescription", currentStore.getStoreDescription());
                        myContext.startActivity(storeIntent);
                    } else {
                        Toast.makeText(myContext, "Create an activity for the mall", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        /*Step 6: Create a method to bind the current view with dataImage, title and description

         */
        public void bindTo(Store currentStore) {
            /*Step 6.1: Populate the view with the data
            - for loading the image use the Glide library so as to prevent problems of app crashing as a result of loading many images of different resolution
            - Implement the Glide library first in your Glide Module(App) level

             */
            Glide.with(myContext).load(currentStore.getStoreImage()).into(myStoreImage);
            myStoreTitle.setText(currentStore.getStoreTitle());
            myStoreDescription.setText(currentStore.getStoreDescription());
        }
    }
}

