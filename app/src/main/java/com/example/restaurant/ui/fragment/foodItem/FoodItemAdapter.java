package com.example.restaurant.ui.fragment.foodItem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant.data.model.api.homeCycle.Item;
import com.example.restaurant.databinding.ItemFoodProductBinding;
import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ItemViewHolder> {
    private Activity activity;
    private List<Item> items;


    public FoodItemAdapter(Activity activity, List<Item> items) {
        this.activity = activity;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemFoodProductBinding itemBinding =
                ItemFoodProductBinding.inflate(layoutInflater, parent, false);
        return new ItemViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);

        holder.bind(item);

        //viewBinderHelper.bind(holder.binding.swipe, String.valueOf(item.getId()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemFoodProductBinding binding;

        public ItemViewHolder(ItemFoodProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Item item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }


}


