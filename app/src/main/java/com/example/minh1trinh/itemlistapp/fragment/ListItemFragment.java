package com.example.minh1trinh.itemlistapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.minh1trinh.itemlistapp.MainActivity;
import com.example.minh1trinh.itemlistapp.R;
import com.example.minh1trinh.itemlistapp.adapter.ListItemAdapter;
import com.example.minh1trinh.itemlistapp.model.ItemList;
import com.example.minh1trinh.itemlistapp.realm.DMSAimInventoryItem;
import com.example.minh1trinh.itemlistapp.realm.DMSAimOrder;
import com.example.minh1trinh.itemlistapp.realm.DMSAimSalesPrice;
import com.example.minh1trinh.itemlistapp.realm.DMSAimSalesmanStock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by minh1.trinh on 12/22/2017.
 * Use for test in DMSpro
 */

public class ListItemFragment extends Fragment implements View.OnClickListener {
    private Realm realm;
    private List<ItemList>itemList;
    private RecyclerView recyclerView;
    private ListItemAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnSave;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        btnSave = view.findViewById(R.id.btnSave);
        Realm.init(getActivity());
        initRealm();
        btnSave.setOnClickListener(this);
      //  addDataToRealm();
        return view;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadAllList();
    }


    private void initRealm() {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @SuppressWarnings("ConstantConditions")
    private void loadAllList(){
        itemList = new ArrayList<>();
        RealmResults<DMSAimInventoryItem> inventoryItemList = realm.where(DMSAimInventoryItem.class).findAll();
        RealmResults<DMSAimOrder>dmsAimOrders = realm.where(DMSAimOrder.class).findAll();
        realm.beginTransaction();
        for(int i = 0 ; i < inventoryItemList.size(); i++){
            int numberAvailalbeProduct;
            float price;
            DMSAimSalesPrice dmsAimSalesPrice = realm.where(DMSAimSalesPrice.class).beginGroup()
                    .equalTo("inventoryCD",inventoryItemList.get(i).getInventoryCD()).endGroup().findFirst();
            price = dmsAimSalesPrice.getPrice();
            DMSAimSalesmanStock dmsAimSalesmanStock = realm.where(DMSAimSalesmanStock.class).beginGroup().
                    equalTo("inventoryCD", inventoryItemList.get(i).getInventoryCD()).endGroup().findFirst();
            numberAvailalbeProduct = dmsAimSalesmanStock.getAvaiQty();
            ItemList item = new ItemList(inventoryItemList.get(i).getInventoryCD(),
                    inventoryItemList.get(i).getShortName(),  price,numberAvailalbeProduct,dmsAimOrders.get(i).getNumberOrder());
            itemList.add(item);
        }
        realm.commitTransaction();
        adapter = new ListItemAdapter(getContext(), itemList);
        recyclerView.setAdapter(adapter);
    }

    private void addDataToRealm(){
        //Start add DMSAimInventoryItem
        realm.beginTransaction();
        ArrayList<String>listInventoryCD = new ArrayList<>();
        ArrayList<String>listInventoryName = new ArrayList<>();
        ArrayList<String>listShortName = new ArrayList<>();
        ArrayList<Integer>listUnitPerCase = new ArrayList<>();
        ArrayList<Integer>listAvaiQty = new ArrayList<>();
        ArrayList<Float>listPrice = new ArrayList<>();
        for(int i = 0 ; i < 3000; i++){
            listInventoryCD.add("0"+i);
            listInventoryName.add("Dynamite Big Bang 120gx36 position" + i);
            listShortName.add("Kẹo Dynamite BigBang1 120g x 36 position" + i);
            listUnitPerCase.add(i);
            int item = 1 + i;
            listAvaiQty.add(item);
            float currentPrice = (float) 11.0;
            listPrice.add(currentPrice);
        }



        for(int i = 0 ; i < 3000; i++){
            DMSAimOrder dmsAimOrder = realm.createObject(DMSAimOrder.class);
            dmsAimOrder.setInventoryCD(listInventoryCD.get(i));
            dmsAimOrder.setNumberOrder(i);
        }

        for(int i = 0 ; i < 3000; i++){
            DMSAimInventoryItem dmsAimInventoryItem = realm.createObject(DMSAimInventoryItem.class,listInventoryCD.get(i));
            dmsAimInventoryItem.setInventoryName(listInventoryName.get(i));
            dmsAimInventoryItem.setShortName(listShortName.get(i));
            dmsAimInventoryItem.setUnitPerCase(listUnitPerCase.get(i));
            dmsAimInventoryItem.setAllowSales(1);
            dmsAimInventoryItem.setSalesUnit("PCS");
            realm.insert(dmsAimInventoryItem);

        }


        for(int i = 0 ; i  < 3000; i++){
            DMSAimSalesmanStock salesmanStock = realm.createObject(DMSAimSalesmanStock.class);
            salesmanStock.setUserName("SM009");
            salesmanStock.setInventoryCD(listInventoryCD.get(i));
            salesmanStock.setDistributorCD("00001");
            salesmanStock.setAvaiQty(listAvaiQty.get(i));
            realm.insertOrUpdate(salesmanStock);
        }



        for(int i = 0 ; i < 3000; i++){
            DMSAimSalesPrice dmsAimSalesPrice = realm.createObject(DMSAimSalesPrice.class);
            dmsAimSalesPrice.setInventoryCD(listInventoryCD.get(i));
            dmsAimSalesPrice.setPrice(listPrice.get(i));
            realm.insertOrUpdate(dmsAimSalesPrice);
        }
        realm.commitTransaction();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                Toast.makeText(getActivity(), "Update Successfully",Toast.LENGTH_SHORT).show();
                adapter.updateOrderNumber();
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }
    }
}
