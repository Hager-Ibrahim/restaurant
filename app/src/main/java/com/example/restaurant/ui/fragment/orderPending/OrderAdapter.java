package com.example.restaurant.ui.fragment.orderPending;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant.R;
import com.example.restaurant.data.model.api.homeCycle.Order;
import com.example.restaurant.data.model.dataBinding.OrderBinding;
import com.example.restaurant.databinding.ItemOrderPendingBinding;
import com.squareup.picasso.Picasso;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Activity activity;
    private List<Order> orders;
    String tabNum;
    OrderBinding orderBinding = new OrderBinding();

    public OrderAdapter(Activity activity, List<Order> orders, String tabNum) {
        this.activity = activity;
        this.orders = orders;
        this.tabNum = tabNum;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemOrderPendingBinding itemBinding =
                ItemOrderPendingBinding.inflate(layoutInflater, parent, false);
        return new OrderAdapter.OrderViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.bind(order);

    }

    @Override
    public int getItemCount() {

        return orders.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private final ItemOrderPendingBinding binding;

        public OrderViewHolder(ItemOrderPendingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            if (tabNum == "pending"){
                orderBinding.setPending(true);
                binding.setOrderBinding(orderBinding);
            }

            else if(tabNum == "current") {
                orderBinding.setCurrent(true);
                binding.setOrderBinding(orderBinding);
            }

            else if(tabNum == "completed"){
                orderBinding.setCompleted(true);
                binding.setOrderBinding(orderBinding);            }
        }

        public void bind(Order order) {
            binding.setOrder(order);
            binding.executePendingBindings();
        }
    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView view, String imageUrl){

        Picasso.get().load(imageUrl).into(view);

    }

    @BindingAdapter("state")
    public static void setOrderStateButtonBackground(Button view, String state){
        if(state.equals("rejected") ){
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.red_button));

        }
        else if(state.equals("delivered")){
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.colorAccent));
        }
    }

}
