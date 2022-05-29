package com.example.app_for_bakery.vm_discus_order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_for_bakery.chat.ChatFragment;
import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.DiscusOrderPageBinding;
import com.example.app_for_bakery.vm_about_order.AboutOrderViewModel;
import com.example.app_for_bakery.vm_about_order.GalleryFragment;
import com.example.app_for_bakery.vm_baker_profile.BakerProfileFragment;
import com.example.app_for_bakery.vm_baker_profile.BakerProfileViewModel;
import com.example.app_for_bakery.vm_profile.ProfileFragment;

import java.util.ArrayList;

public class DiscusOrderFragment extends Fragment {
    private DiscusOrderPageBinding binding;
    static int PAGE_COUNT;
    static ArrayList<Integer> images = new ArrayList<>();

    private AboutOrderViewModel aboutOrderViewModel;
    private BakerProfileViewModel bakerProfileViewModel;

    PagerAdapter pagerAdapter;
    public String tag = "DiscusOrderFragment";

    public DiscusOrderFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.discus_order_page, container, false);
        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        binding.galleryDiscusOrder.setAdapter(pagerAdapter);

        pagerAdapter.notifyDataSetChanged();

        aboutOrderViewModel = new ViewModelProvider(requireActivity()).get(AboutOrderViewModel.class);
        bakerProfileViewModel = new ViewModelProvider(requireActivity()).get(BakerProfileViewModel.class);

        aboutOrderViewModel.getCurrent_order().observe(getViewLifecycleOwner(), mOrderData -> {
            PAGE_COUNT = mOrderData.getPositionDataList().size();

            images = new ArrayList<>(PAGE_COUNT);
            for (int i = 0; i < PAGE_COUNT; i++){
                images.add(mOrderData.getPositionDataList().get(i).getImg());
            }
            pagerAdapter.notifyDataSetChanged();
            binding.orderNumber.setText(mOrderData.getNumber());
            binding.orderDate.setText(mOrderData.getData());
        });

        binding.btnProfile.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new ProfileFragment(), "ProfileFragment");
            MainActivity.bottomNavigationView.setSelectedItemId(R.id.page_4);
        });

        bakerProfileViewModel.setBaker_name("Елена");
        bakerProfileViewModel.setBaker_post("Главный кондитер");

        binding.bakerProfile.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new BakerProfileFragment(), "BakerProfileFragment");
        });

        binding.btnChat.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new ChatFragment(), "ChatFragment");
        });

        return binding.getRoot();
    }

    private static class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return GalleryFragment.newInstance(position, images.get(position));
        }
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }
}