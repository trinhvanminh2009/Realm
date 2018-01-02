package com.example.minh1trinh.itemlistapp.realm;

import io.realm.RealmObject;

/**
 * Created by minh1.trinh on 12/22/2017.
 * Use for test Realm in DMSpro
 */

public class DMSAimSalesPrice extends RealmObject {
    private String inventoryCD;
    private float price;

    public String getInventoryCD() {
        return inventoryCD;
    }

    public void setInventoryCD(String inventoryCD) {
        this.inventoryCD = inventoryCD;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
