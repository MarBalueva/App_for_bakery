package com.example.app_for_bakery.vm_basket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.PositionCardBasketBinding;
import com.example.app_for_bakery.model.MPositionData;

import java.util.List;

public class RVAdapterBasketCard extends RecyclerView.Adapter<RVAdapterBasketCard.CardViewHolder>{
    List<MPositionData> cards;
    private OnPositionClickListener listener; //обработчик нажатия

    public interface OnPositionClickListener{ //интерфейс для обработки клика
        void onClick(View view, int position);
    }

    public void setListener(OnPositionClickListener listener) {  //обработчик нажатия
        this.listener = listener;
    }

    public MPositionData getPositionAt(int position){ //геттер нажатой позиции
        return cards.get(position);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final PositionCardBasketBinding binding;

        CardViewHolder(@NonNull PositionCardBasketBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    RVAdapterBasketCard(List<MPositionData> cards){
        this.cards = cards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PositionCardBasketBinding binding = DataBindingUtil.inflate(inflater, R.layout.position_card_basket, parent, false);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {
        cardViewHolder.binding.basketCardTitle.setText(cards.get(position).getName());
        cardViewHolder.binding.basketCardCount.setText(cards.get(position).getCount().toString());
        cardViewHolder.binding.cakeViewBasket.setImageResource(cards.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
