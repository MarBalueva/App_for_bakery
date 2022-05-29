package com.example.app_for_bakery.vm_assortment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.PositionCardAssortmentBinding;
import com.example.app_for_bakery.model.MPositionData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RVAdapterAssortmentCard extends RecyclerView.Adapter<RVAdapterAssortmentCard.CardViewHolder> implements Filterable {
    private final List<MPositionData> cards; //список карточек
    private List<MPositionData> cards_full; //копия списка карточек

    private OnPositionClickListener listener; //обработчик нажатия
    private AssortmentViewModel viewModel;

    private MPositionData mPositionData;

    public interface OnPositionClickListener{ //интерфейс для обработки клика
        void onClick(View view, int position);
    }

    public void setListener(OnPositionClickListener listener) {  //обработчик нажатия
        this.listener = listener;
    }

    public MPositionData getPositionAt(int position){ //геттер нажатой позиции
        return cards.get(position);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, LifecycleOwner {

        private final PositionCardAssortmentBinding binding;
        private LifecycleRegistry lifecycleRegistry= new LifecycleRegistry(this);

        public CardViewHolder(@NonNull PositionCardAssortmentBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
            binding.addBasket.setOnClickListener(view -> {
                Toast.makeText(view.getContext(), "Добавлено в корзину", Toast.LENGTH_SHORT).show();
                mPositionData = cards.get(getAdapterPosition());
                viewModel.addToBasket(mPositionData);
            });
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }

        @NonNull
        @Override
        public Lifecycle getLifecycle() {
            return lifecycleRegistry;
        }
    }

    public RVAdapterAssortmentCard(List<MPositionData> cards, AssortmentViewModel viewModel){
        this.viewModel = viewModel;
        this.cards = cards;
        cards_full = new ArrayList<>(cards);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //разметка при создании
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PositionCardAssortmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.position_card_assortment, parent, false);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {
        cardViewHolder.binding.positionCardAssortmentTitle.setText(cards.get(position).getName());
        cardViewHolder.binding.imgCardPositionAssortment.setImageResource(cards.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<MPositionData> filtered_list = new ArrayList<>();
            if (charSequence == null  || charSequence.length() == 0)
                filtered_list.addAll(cards_full);
            else {
                String filter_pattern = charSequence.toString().toLowerCase(Locale.ROOT).trim();
                for (MPositionData item : cards_full)
                    if (item.getName() != null && item.getName().toLowerCase(Locale.ROOT).contains(filter_pattern))
                        filtered_list.add(item);
            }
            FilterResults results = new FilterResults();
            results.values = filtered_list;
            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            cards.clear();
            cards.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };
}
