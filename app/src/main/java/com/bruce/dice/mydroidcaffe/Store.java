package com.bruce.dice.mydroidcaffe;

public class Store {
    // Declare private member variables

    private final int storeImage;
    private final String storeTitle;
    private final String storeDescription;

    /*  create a constructor for the recipe data model
        pass the parameters storeImage, storeTitle and storeDescription
     */
    Store(int storeImage,String storeTitle, String storeDescription) {
        this.storeImage = storeImage;
        this.storeTitle = storeTitle;
        this.storeDescription = storeDescription;

    }

    /* create the getters and return the specific object
    */

    public int getStoreImage(){
        return storeImage;
    }
    public String getStoreTitle(){
        return storeTitle;
    }

    public String getStoreDescription(){
        return storeDescription;
    }

}
