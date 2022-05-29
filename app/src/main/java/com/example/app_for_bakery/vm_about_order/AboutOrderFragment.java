package com.example.app_for_bakery.vm_about_order;

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
import android.widget.Toast;

import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.ReviewWindowDialogFragment;
import com.example.app_for_bakery.databinding.PageOfOrderBinding;
import com.example.app_for_bakery.model.MPositionData;
import com.example.app_for_bakery.vm_assortment.AssortmentViewModel;
import com.example.app_for_bakery.vm_basket.BasketViewModel;
import com.example.app_for_bakery.vm_discus_order.DiscusOrderFragment;
import com.example.app_for_bakery.vm_list_of_orders.ListOfOrdersViewModel;
import com.example.app_for_bakery.vm_neworder.NewOrderViewModel;
import com.example.app_for_bakery.vm_profile.ChangePasswordDialog;
import com.example.app_for_bakery.vm_profile.ProfileFragment;
import com.example.app_for_bakery.vm_where_is_order.WhereIsOrderFragment;

import java.util.ArrayList;
import java.util.List;

public class AboutOrderFragment extends Fragment {

    static int PAGE_COUNT;
    static ArrayList<Integer> images = new ArrayList<>();
    PagerAdapter pagerAdapter;

    private ListOfOrdersViewModel listOfOrdersViewModel;
    private AboutOrderViewModel aboutOrderViewModel;
    private AssortmentViewModel assortmentViewModel;
    private BasketViewModel basketViewModel;

    private PageOfOrderBinding binding;
    public String tag = "AboutOrderFragment";


    public AboutOrderFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.page_of_order, container, false);

        binding.btnProfile.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new ProfileFragment(), "ProfileFragment");
            MainActivity.bottomNavigationView.setSelectedItemId(R.id.page_4);
        });

        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        binding.galleryPageOrder.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();

        listOfOrdersViewModel = new ViewModelProvider(requireActivity()).get(ListOfOrdersViewModel.class);
        aboutOrderViewModel = new ViewModelProvider(requireActivity()).get(AboutOrderViewModel.class);
        assortmentViewModel = new ViewModelProvider(requireActivity()).get(AssortmentViewModel.class);
        basketViewModel = new ViewModelProvider(requireActivity()).get(BasketViewModel.class);

        listOfOrdersViewModel.getSelected().observe(getViewLifecycleOwner(), mOrderData -> {
            binding.orderNumber.setText(mOrderData.getNumber());
            binding.orderDate.setText(mOrderData.getData());

            PAGE_COUNT = mOrderData.getPositionDataList().size();

            images = new ArrayList<>(PAGE_COUNT);
            for (int i = 0; i < PAGE_COUNT; i++){
                images.add(mOrderData.getPositionDataList().get(i).getImg());
            }
            pagerAdapter.notifyDataSetChanged();

            aboutOrderViewModel.setCurrent_order(mOrderData);
        });

        binding.btnWhereIsMyOrder.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new WhereIsOrderFragment(), "WhereIsOrderFragment");
        });

        binding.btnDiscusMyOrder.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new DiscusOrderFragment(), "DiscusOrderFragment");
        });

        binding.btnGiveReviewMyOrder.setOnClickListener(view -> {
            new ReviewWindowDialogFragment().show(getParentFragmentManager(), "ReviewWindowDialogFragment");
        });

        binding.btnRepeatMyOrder.setOnClickListener(view -> {
            aboutOrderViewModel.getCurrent_order().observe(getViewLifecycleOwner(), mOrderData -> {
                List<MPositionData> mPositionDataList = new ArrayList<>(mOrderData.getPositionDataList());
                for (MPositionData mPositionData : mPositionDataList)
                    assortmentViewModel.addToBasket(mPositionData);
                Toast.makeText(requireActivity(), "Проверьте корзину!", Toast.LENGTH_SHORT).show();
            });
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