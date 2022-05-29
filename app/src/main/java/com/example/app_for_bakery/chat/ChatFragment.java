package com.example.app_for_bakery.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.ChatPageBinding;
import com.example.app_for_bakery.model.MChat;
import com.example.app_for_bakery.vm_about_order.AboutOrderViewModel;
import com.example.app_for_bakery.vm_baker_profile.BakerProfileFragment;
import com.example.app_for_bakery.vm_baker_profile.BakerProfileViewModel;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    private ChatPageBinding binding;

    private AboutOrderViewModel aboutOrderViewModel;
    private BakerProfileViewModel bakerProfileViewModel;

    private String message = "";
    private ArrayList<MChat> chatArrayList = new ArrayList<>();


    public ChatFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.chat_page, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        aboutOrderViewModel = new ViewModelProvider(requireActivity()).get(AboutOrderViewModel.class);
        bakerProfileViewModel = new ViewModelProvider(requireActivity()).get(BakerProfileViewModel.class);

        aboutOrderViewModel.getCurrent_order().observe(getViewLifecycleOwner(), mOrderData -> {
            binding.orderNumber.setText(mOrderData.getNumber());
            binding.orderDate.setText(mOrderData.getData());
        });

        bakerProfileViewModel.getBaker_name().observe(getViewLifecycleOwner(), s -> {
            binding.bakerName.setText(s);
        });

        binding.bakerProfile.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new BakerProfileFragment(), "BakerProfileFragment");
        });

        RVAdapterChat adapter = new RVAdapterChat(chatArrayList);
        binding.chatRecyclerview.setAdapter(adapter);

        binding.btnSend.setOnClickListener(view -> {
            message = binding.sendMessage.getText().toString();
            chatArrayList.add(new MChat(message, 1));
            chatArrayList.add(new MChat(message, 2));
            adapter.notifyItemInserted(chatArrayList.size() - 1);
            binding.sendMessage.getText().clear();
        });



        return binding.getRoot();
    }
}
