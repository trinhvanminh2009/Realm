package com.example.minh1trinh.itemlistapp.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by minh1.trinh on 12/22/2017.
 * Use for test Realm in DMSpro
 */

public class DMSAimSalesmanStock extends RealmObject {
    private String userName;
    private String distributorCD;
    private String inventoryCD;
    private int avaiQty;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDistributorCD() {
        return distributorCD;
    }

    public void setDistributorCD(String distributorCD) {
        this.distributorCD = distributorCD;
    }

    public String getInventoryCD() {
        return inventoryCD;
    }

    public void setInventoryCD(String inventoryCD) {
        this.inventoryCD = inventoryCD;
    }

    public int getAvaiQty() {
        return avaiQty;
    }

    public void setAvaiQty(int avaiQty) {
        this.avaiQty = avaiQty;
    }
}
