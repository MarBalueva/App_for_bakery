package com.example.app_for_bakery.vm_list_of_orders;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.OrderCardBasketBinding;
import com.example.app_for_bakery.model.MOrderData;
import com.example.app_for_bakery.model.MPositionData;

import java.util.List;

public class RVAdapterOrderCard extends RecyclerView.Adapter<RVAdapterOrderCard.CardViewHolder>{
    private List<MOrderData> cards;
    private OnPositionClickListener listener; //обработчик нажатия

    public interface OnPositionClickListener{ //интерфейс для обработки клика
        void onClick(View view, int position);
    }

    public void setListener(OnPositionClickListener listener) {  //обработчик нажатия
        this.listener = listener;
    }

    public MOrderData getPositionAt(int position){ //геттер нажатой позиции
        return cards.get(position);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final OrderCardBasketBinding binding;

        CardViewHolder(@NonNull OrderCardBasketBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    RVAdapterOrderCard(List<MOrderData> cards){
        this.cards = cards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        OrderCardBasketBinding binding = DataBindingUtil.inflate(inflater, R.layout.order_card_basket, parent, false);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {
        cardViewHolder.binding.orderNumberOrderPage.setText(cards.get(position).getNumber());
        cardViewHolder.binding.orderDateOrderPage.setText(cards.get(position).getData());
        if (cards.get(position).isIs_confirmed())
            cardViewHolder.binding.orderConfirm.setText("(завершен)");
        LinearLayout linearLayout = cardViewHolder.binding.horizontalScrollview;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(250, 250);
        for (int i = 0; i < cards.get(position).getPositionDataList().size(); i++) {
            ImageView imageView = new ImageView(linearLayout.getContext());
            imageView.setImageResource(cards.get(position).getPositionDataList().get(i).getImg());
            imageView.setLayoutParams(layoutParams);
            linearLayout.addView(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
