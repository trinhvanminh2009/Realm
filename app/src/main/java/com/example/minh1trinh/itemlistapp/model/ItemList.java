package com.example.minh1trinh.itemlistapp.model;

/**
 * Created by minh1.trinh on 12/22/2017.
 *  * Use for test Realm in DMSpro
 */

public class ItemList {
    private String productCD;
    private String productName;
    private float productPrice;
    private int numberAvailableProduct;
    private int numberOrder;

    public ItemList(String productCD, String productName, float productPrice, int numberAvailableProduct, int numberOrder) {
        this.productCD = productCD;
        this.productName = productName;
        this.productPrice = productPrice;
        this.numberAvailableProduct = numberAvailableProduct;
        this.numberOrder = numberOrder;
    }

    public String getProductCD() {
        return productCD;
    }

    public void setProductCD(String productCD) {
        this.productCD = productCD;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getNumberAvailableProduct() {
        return numberAvailableProduct;
    }

    public void setNumberAvailableProduct(int numberAvailableProduct) {
        this.numberAvailableProduct = numberAvailableProduct;
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }
}
