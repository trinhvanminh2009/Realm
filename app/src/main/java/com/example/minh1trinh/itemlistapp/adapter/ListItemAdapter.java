package com.example.minh1trinh.itemlistapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.minh1trinh.itemlistapp.R;
import com.example.minh1trinh.itemlistapp.model.ItemList;
import com.example.minh1trinh.itemlistapp.realm.DMSAimOrder;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by minh1.trinh on 12/22/2017.
 * Use for test Realm in DMSpro
 */

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {
    private Context context;
    private Realm realm;
    private List<ItemList> itemList;
    private ArrayList<ItemChanged>listChanged = new ArrayList<>();

    public ListItemAdapter(Context context, List<ItemList> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    private void initRealm() {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public ListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, parent, false);
        Realm.init(context);
        initRealm();
        return new ViewHolder(view, new EditTextNumberChangedListener());
    }

    @Override
    public void onBindViewHolder(ListItemAdapter.ViewHolder holder, int position) {
        if (itemList != null) {
            if (itemList.size() > 0) {
                ItemList item = itemList.get(position);
                holder.editTextNumberChangedListener.updatePosition(position);
                if ((item.getProductCD() != null) &&
                        (item.getNumberAvailableProduct() > -1) && (item.getProductName() != null)
                        && (item.getProductPrice() > 0)) {
                    holder.txtProductCD.setText(item.getProductCD());
                    holder.txtProductName.setText(item.getProductName());
                    holder.txtProductPrice.setText(String.valueOf(item.getProductPrice()));
                    holder.txtAvailableProduct.setText(String.valueOf(item.getNumberAvailableProduct()) );
                    holder.editNumberOrder.setText(String.valueOf(item.getNumberOrder()));
                }

            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    public void updateOrderNumber(){

        for(int i = 0 ; i < listChanged.size(); i++){
            realm.beginTransaction();
            int numberChanged = itemList.get(listChanged.get(i).getPosition()).getNumberOrder();
            DMSAimOrder dmsAimOrder = realm.where(DMSAimOrder.class).beginGroup()
                    .equalTo("inventoryCD",listChanged.get(i).getId()).endGroup().findFirst();
            dmsAimOrder.setNumberOrder(numberChanged);
            realm.commitTransaction();

        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtProductCD;
        private TextView txtProductName;
        private TextView txtProductPrice;
        private TextView txtAvailableProduct;
        private EditText editNumberOrder;
        EditTextNumberChangedListener editTextNumberChangedListener;

        ViewHolder(View itemView, EditTextNumberChangedListener editTextNumberChangedListener) {
            super(itemView);
            txtProductCD = itemView.findViewById(R.id.txtProductCD);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtPrice);
            txtAvailableProduct = itemView.findViewById(R.id.txtAvailQty);
            editNumberOrder = itemView.findViewById(R.id.editNumberOrder);
            this.editTextNumberChangedListener = editTextNumberChangedListener;
            editNumberOrder.addTextChangedListener(editTextNumberChangedListener);
        }

    }

    private class EditTextNumberChangedListener implements TextWatcher{

        private int position;
        private void updatePosition(int position){
            this.position = position;

        }
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @SuppressWarnings("ConstantConditions")
        @Override
        public void afterTextChanged(Editable editable) {

                if(TextUtils.isEmpty(editable.toString())){
                    itemList.get(position).setNumberOrder(0);
                }else{
                    itemList.get(position).setNumberOrder(Integer.valueOf(editable.toString()));
                    listChanged.add(new ItemChanged(itemList.get(position).getProductCD(), position));
                }
        }
    }

    private class ItemChanged{
        private String id;
        private int position;

        public ItemChanged(String id, int position) {
            this.id = id;
            this.position = position;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
