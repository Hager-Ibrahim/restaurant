package com.example.restaurant.ui.fragment.homeCategories;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.restaurant.R;
import com.example.restaurant.data.model.api.homeCycle.Category;
import com.example.restaurant.databinding.ItemHomeBinding;
import com.example.restaurant.ui.fragment.foodItem.FoodItemFragment;
import com.example.restaurant.utils.HelperMethod;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CategoryViewHolder> {
    private Activity activity;
    private List<Category> categories;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public HomeAdapter(Activity activity, List<Category> categories) {
        this.activity = activity;
        this.categories = categories;
        viewBinderHelper.setOpenOnlyOne(true);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemHomeBinding itemBinding =
                ItemHomeBinding.inflate(layoutInflater, parent, false);
        return new CategoryViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category item = categories.get(position);
        holder.bind(item);
        viewBinderHelper.bind(holder.binding.swipe, String.valueOf(item.getId()));

        holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodItemFragment frag = new FoodItemFragment();

                Bundle i = new Bundle();
                i.putString("categoryId", String.valueOf(item.getId()));

                frag.setArguments(i);
                //frag.categoryId = item.getId();
                HelperMethod.openFragment((AppCompatActivity) activity,
                        R.id.home_fragment_container,
                        frag);

                Toast.makeText(view.getContext(), String.valueOf(item.getId()), Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeBinding binding;

        public CategoryViewHolder(ItemHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Category item) {
            binding.setCategory(item);
            binding.executePendingBindings();
        }
    }



}


