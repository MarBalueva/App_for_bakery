package com.example.app_for_bakery.chat;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.ItemTextInBinding;
import com.example.app_for_bakery.databinding.ItemTextOutBinding;
import com.example.app_for_bakery.model.MChat;

import java.text.DateFormat;
import java.util.ArrayList;

public class RVAdapterChat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<MChat> list;
    public static final int MESSAGE_TYPE_IN = 2;
    public static final int MESSAGE_TYPE_OUT = 1;

    public RVAdapterChat(ArrayList<MChat> list) {
        this.list = list;
    }

    private class MessageInViewHolder extends RecyclerView.ViewHolder {
        private ItemTextInBinding binding;

        MessageInViewHolder(ItemTextInBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(int position) {
            MChat messageModel = list.get(position);
            binding.messageText.setText(messageModel.message);
            binding.dateText.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(messageModel.messageTime));
        }
    }

    private class MessageOutViewHolder extends RecyclerView.ViewHolder {
        private ItemTextOutBinding binding;

        MessageOutViewHolder(ItemTextOutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(int position) {
            MChat messageModel = list.get(position);
            binding.messageText.setText(messageModel.message);
            binding.dateText.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(messageModel.messageTime));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == MESSAGE_TYPE_IN) {
            ItemTextInBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_text_in, parent, false);
            return new MessageInViewHolder(binding);
        }
        ItemTextOutBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_text_out, parent, false);
        return new MessageOutViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (list.get(position).messageType == MESSAGE_TYPE_IN) {
            ((MessageInViewHolder) holder).bind(position);
        } else {
            ((MessageOutViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).messageType;
    }
}
