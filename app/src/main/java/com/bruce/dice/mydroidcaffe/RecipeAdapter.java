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
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    //Step 3.0 Declare the private member variables Recipe Data and the context
    private final ArrayList<Recipe> recipeData;
    private final Context myContext;

    /* Step 3.1
    - Create a constructor to pass in the recipe data and the context
     */
    RecipeAdapter(ArrayList<Recipe> mRecipeData, Context context) {
        //Initialize the objects
        this.myContext = context;
        this.recipeData = mRecipeData;
    }

    /*Step 1.1 Required
   - Implement the method onCreateViewHolder for creating objects
   - @param parent the view group of which the view object will be added after it is bound to the adapter position
   - @param viewType viewType of the new view
   - @return the newly created viewHolder
    */

    @NonNull
    @Override

    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Step 4.1 Create a new viewHolder
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.recipe_list_item, parent, false));
    }
    /*Step 1.2 Required for binding the view to the data
    - @param viewHolder which the data should be put
    - @position the adapter position
     */

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        // Step 5: Get the current view object using its position and populate it with data
        Recipe currentRecipe = recipeData.get(position);

        //Step 5.1: Populate the current view with data
        holder.bindTo(currentRecipe);
    }
    /*Step 1.3 Required for getting the size of the data set
    - @return the size of the data set
     */

    @Override
    public int getItemCount() {
        // Step 4.0
        return recipeData.size();
    }
    /*Step 2 Create the ViewHolder class that represents each and every row in the recycler view

     */

    public class ViewHolder extends RecyclerView.ViewHolder {
        /*Step 2.1
        - Create a constructor for the ViewHolder used in onCreateViewHolder() method
        - @param itemView rootView of the recipe_list_item layout
         */

        //Step 2.2 Declare the private member variable that the viewHolder will hold
        private final ImageView myRecipeImage;
        private final TextView myRecipeTitle;
        private final TextView myRecipeDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myRecipeImage = itemView.findViewById(R.id.recipe_image);
            myRecipeTitle = itemView.findViewById(R.id.recipe_title);
            myRecipeDescription = itemView.findViewById(R.id.recipe_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int dessertPosition = getAdapterPosition();
                    Recipe currentDessert = recipeData.get(dessertPosition);
                    if (dessertPosition == 0) {
                        Intent donutIntent = new Intent(myContext, DonutActivity.class);
                        donutIntent.putExtra("dTitle", currentDessert.getRecipeTitle());
                        donutIntent.putExtra("dImage", currentDessert.getRecipeImage());
                        donutIntent.putExtra("dDescription", currentDessert.getRecipeDescription());
                        myContext.startActivity(donutIntent);
                    } else {
                        Toast.makeText(myContext, "Create an activity for the dessert", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        /*Step 6: Create a method to bind the current view with dataImage, title and description

         */
        public void bindTo(Recipe currentRecipe) {
            /*Step 6.1: Populate the view with the data
            - for loading the image use the Glide library so as to prevent problems of app crashing as a result of loading many images of different resolution
            - Implement the Glide library first in your Glide Module(App) level

             */
            Glide.with(myContext).load(currentRecipe.getRecipeImage()).into(myRecipeImage);
            myRecipeTitle.setText(currentRecipe.getRecipeTitle());
            myRecipeDescription.setText(currentRecipe.getRecipeDescription());
        }
    }
}

