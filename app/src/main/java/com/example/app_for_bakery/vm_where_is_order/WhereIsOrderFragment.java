package com.example.app_for_bakery.vm_where_is_order;

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

import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.ThanksForOrderFragment;
import com.example.app_for_bakery.databinding.WhereIsOrderPageBinding;
import com.example.app_for_bakery.vm_about_order.AboutOrderViewModel;
import com.example.app_for_bakery.vm_about_order.GalleryFragment;
import com.example.app_for_bakery.vm_list_of_orders.ListOfOrdersViewModel;
import com.example.app_for_bakery.vm_profile.ProfileFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class WhereIsOrderFragment extends Fragment {
    private WhereIsOrderPageBinding binding;
    static int PAGE_COUNT;
    static ArrayList<Integer> images = new ArrayList<>();
    PagerAdapter pagerAdapter;

    private AboutOrderViewModel aboutOrderViewModel;
    private ListOfOrdersViewModel listOfOrdersViewModel;

    public String tag = "WhereIsOrderFragment";

    public WhereIsOrderFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.where_is_order_page, container, false);

        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        binding.galleryWhereIsOrder.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();

        Date currentDate = new Date();
        // Форматирование времени как "часы:минуты:секунды"
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);

        aboutOrderViewModel = new ViewModelProvider(requireActivity()).get(AboutOrderViewModel.class);
        listOfOrdersViewModel = new ViewModelProvider(requireActivity()).get(ListOfOrdersViewModel.class);

        aboutOrderViewModel.getCurrent_order().observe(getViewLifecycleOwner(), mOrderData -> {
            PAGE_COUNT = mOrderData.getPositionDataList().size();

            images = new ArrayList<>(PAGE_COUNT);
            for (int i = 0; i < PAGE_COUNT; i++){
                images.add(mOrderData.getPositionDataList().get(i).getImg());
            }
            pagerAdapter.notifyDataSetChanged();

            binding.orderNumber.setText(mOrderData.getNumber());
            binding.orderDate.setText(mOrderData.getData());
            binding.timePay.setText(mOrderData.getTime());
            binding.timeProcess.setText(timeText);

            if (mOrderData.isIs_confirmed()) {
                binding.btnOrderGive.setVisibility(View.INVISIBLE);
            }

            binding.btnOrderGive.setOnClickListener(view -> {
                mOrderData.setIs_confirmed(true);
                binding.btnOrderGive.setVisibility(View.INVISIBLE);
                binding.timeCook.setText(timeText);
                binding.timeDelivery.setText(timeText);
                binding.timeReady.setText(timeText);
                new ThanksForOrderFragment().show(getParentFragmentManager(), "ThanksForOrderFragment");
            });
        });

        binding.btnProfile.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new ProfileFragment(), "ProfileFragment");
            MainActivity.bottomNavigationView.setSelectedItemId(R.id.page_4);
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