package com.example.minh1trinh.itemlistapp.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by minh1.trinh on 12/22/2017.
 * Use for test Realm in DMSpro
 */

public class DMSAimInventoryItem extends RealmObject {
    @PrimaryKey
    private String inventoryCD;
    private String inventoryName;
    private String shortName;
    private int unitPerCase;
    private String salesUnit;
    private int allowSales;

    public String getInventoryCD() {
        return inventoryCD;
    }

    public void setInventoryCD(String inventoryCD) {
        this.inventoryCD = inventoryCD;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getUnitPerCase() {
        return unitPerCase;
    }

    public void setUnitPerCase(int unitPerCase) {
        this.unitPerCase = unitPerCase;
    }

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public int getAllowSales() {
        return allowSales;
    }

    public void setAllowSales(int allowSales) {
        this.allowSales = allowSales;
    }
}
