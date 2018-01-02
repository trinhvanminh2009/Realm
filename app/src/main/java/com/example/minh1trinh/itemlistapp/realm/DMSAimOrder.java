package com.example.minh1trinh.itemlistapp.realm;

import io.realm.RealmObject;

/**
 * Created by minh1.trinh on 1/2/2018.
 *
 */

public class DMSAimOrder extends RealmObject {
    private String inventoryCD;
    private int numberOrder;

    public String getInventoryCD() {
        return inventoryCD;
    }

    public void setInventoryCD(String inventoryCD) {
        this.inventoryCD = inventoryCD;
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }


}
