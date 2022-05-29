package com.example.app_for_bakery.vm_neworder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.NewOrderBinding;
import com.example.app_for_bakery.databinding.PayDialogBinding;
import com.example.app_for_bakery.model.MOrderData;
import com.example.app_for_bakery.model.MPositionData;
import com.example.app_for_bakery.vm_about_order.GalleryFragment;
import com.example.app_for_bakery.vm_assortment.AssortmentViewModel;
import com.example.app_for_bakery.vm_basket.BasketFragment;
import com.example.app_for_bakery.vm_basket.BasketViewModel;
import com.example.app_for_bakery.vm_login.LoginPageViewModel;
import com.example.app_for_bakery.vm_profile.ProfileFragment;
import com.example.app_for_bakery.vm_register.RegisterViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class NewOrderFragment extends Fragment {
    private NewOrderBinding binding;
    static int PAGE_COUNT;
    static ArrayList<Integer> images = new ArrayList<>();

    private LoginPageViewModel loginPageViewModel;
    private RegisterViewModel registerViewModel;
    private BasketViewModel basketViewModel;
    private NewOrderViewModel newOrderViewModel;
    private BasketFragment basketFragment = new BasketFragment();

    private PagerAdapter pagerAdapter;
    public String tag = "NewOrderFragment";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private BottomSheetDialog bottomSheetDialog;

    public NewOrderFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.new_order, container, false);

        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        binding.galleryNewOrder.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();

        mDisplayDate = binding.fldDataOrder;
        mDisplayDate.setOnClickListener(view -> {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(requireActivity(),
                        android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
        });

        mDateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = day + "." + month + "." + year;
            mDisplayDate.setText(date);
        };

        binding.btnProfile.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new ProfileFragment(), "ProfileFragment");
            MainActivity.bottomNavigationView.setSelectedItemId(R.id.page_4);
        });

        Date currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        // Форматирование времени как "часы:минуты:секунды"
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);

        loginPageViewModel = new ViewModelProvider(requireActivity()).get(LoginPageViewModel.class);
        registerViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
        basketViewModel = new ViewModelProvider(requireActivity()).get(BasketViewModel.class);
        newOrderViewModel = new ViewModelProvider(requireActivity()).get(NewOrderViewModel.class);

        loginPageViewModel.getUser().observe(getViewLifecycleOwner(), mUserProfile -> { //заполняеи имя и мейл
            binding.fldNameFioOrder.setText(mUserProfile.getName());
            binding.fldPhoneEmailOrder.setText(mUserProfile.getEmail());
        });
        registerViewModel.getUser().observe(getViewLifecycleOwner(), mUserProfile -> { //заполняеи имя и мейл
            binding.fldNameFioOrder.setText(mUserProfile.getName());
            binding.fldPhoneEmailOrder.setText(mUserProfile.getEmail());
        });

        basketViewModel.getPositionLiveData().observe(getViewLifecycleOwner(), mPositionData -> { //считаем сумму
            binding.sumBtn.setText("Итого " + basketViewModel.countPrice(mPositionData) + " руб");
            binding.sumBtn.setOnClickListener(view -> {
                showBottomDialog();
                if (mPositionData.size() != 0) {
                    List<MPositionData> mPositionDataList = new ArrayList<>(mPositionData);
                    MOrderData mOrderData = new MOrderData(newOrderViewModel.count_orders + 1, dateText);
                    mOrderData.setTime(timeText);
                    newOrderViewModel.setCurrent_order(mOrderData, mPositionDataList);
                }
            });

            PAGE_COUNT = mPositionData.size();
            images = new ArrayList<>(PAGE_COUNT);
            for (int i = 0; i < PAGE_COUNT; i++){
                images.add(mPositionData.get(i).getImg());
            }
            pagerAdapter.notifyDataSetChanged();
        });
        return binding.getRoot();
    }

    private void showBottomDialog() {
        bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(R.layout.pay_dialog);

        LinearLayout cardLife = bottomSheetDialog.findViewById(R.id.card_life);
        LinearLayout cardOnline = bottomSheetDialog.findViewById(R.id.card_online);
        LinearLayout cashLife = bottomSheetDialog.findViewById(R.id.cash_life);

        cardLife.setOnClickListener(view -> {
            finish_pay();
        });
        cardOnline.setOnClickListener(view -> {
            finish_pay();
        });
        cashLife.setOnClickListener(view -> {
            finish_pay();
        });
        bottomSheetDialog.show();
    }

    private void finish_pay(){
        Toast.makeText(requireActivity(), "Заказ оплачен!", Toast.LENGTH_SHORT).show();
        bottomSheetDialog.dismiss();

        basketViewModel.deleteAll_positions();

        requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        ((MainActivity)requireActivity()).replaceFragment(basketFragment, "BasketFragment");
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