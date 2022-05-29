package com.example.app_for_bakery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;

import com.example.app_for_bakery.databinding.MakeOrderBinding;
import com.example.app_for_bakery.model.MPositionData;
import com.example.app_for_bakery.repository.PositionRepository;
import com.example.app_for_bakery.vm_about_order.GalleryFragment;
import com.example.app_for_bakery.vm_assortment.AssortmentFragment;
import com.example.app_for_bakery.vm_assortment.AssortmentViewModel;
import com.example.app_for_bakery.vm_login.LoginPageViewModel;
import com.example.app_for_bakery.vm_neworder.NewOrderFragment;
import com.example.app_for_bakery.vm_profile.ProfileFragment;

import java.util.ArrayList;
import java.util.Locale;

public class EditPositionFragment extends Fragment {
    private MakeOrderBinding binding;
    private PositionRepository position_repository = new PositionRepository();

    private String selected_topping = "";
    private String selected_biscuit = "";
    private String selected_cream = "";
    private String selected_weight = "";

    private String name_position = "";
    private String name_biscuit = "";
    private String name_cream = "";
    private String name_topping = "";

    private int sum = 0;
    int copy_sum = 0;
    private int sum_biscuit = 0;
    private int sum_cream = 0;
    private int sum_topping = 0;

    private AssortmentViewModel assortmentViewModel;
    private AssortmentFragment assortmentFragment = new AssortmentFragment();

    public EditPositionFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.make_order, container, false);

        assortmentViewModel = new ViewModelProvider(requireActivity()).get(AssortmentViewModel.class);

        ArrayAdapter<String> choose_biscuit = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, position_repository.getBiscuits());
        choose_biscuit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.btnBiscuit.setAdapter(choose_biscuit);
        binding.btnBiscuit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                name_position = "";
                String[] choose = position_repository.getBiscuits();
                selected_biscuit = choose[i];
                switch (selected_biscuit) {
                    case "Шоколадный":
                        name_biscuit = "Шок + ";
                        binding.imgBiscuit.setImageResource(R.drawable.img_choc_bisc);
                        sum_biscuit = 700;
                        break;
                    case "Ванильный":
                        name_biscuit = "Ван + ";
                        binding.imgBiscuit.setImageResource(R.drawable.img_van_bisc);
                        sum_biscuit = 500;
                        break;
                }
                name_position = name_biscuit + name_cream + name_topping;
                sum = sum_biscuit + sum_cream + sum_topping;
                binding.summaryOrder.setText(sum + " руб");
                //binding.txtBiscuit.setText(selected_biscuit + " бисквит");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> choose_cream = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, position_repository.getCreams());
        choose_cream.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.creme.setAdapter(choose_cream);
        binding.creme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] choose = position_repository.getCreams();
                selected_cream = choose[i];
                switch (selected_cream){
                    case "Шоколадный":
                        sum_cream = 500;
                        name_cream = "шок + ";
                        binding.imgCream.setImageResource(R.drawable.img_choc_cream);
                        break;
                    case "Ванильный":
                        sum_cream = 300;
                        name_cream = "ван + ";
                        binding.imgCream.setImageResource(R.drawable.img_van_creme);
                        break;
                    case "Фисташковый":
                        sum_cream = 700;
                        name_cream = "фис + ";
                        binding.imgCream.setImageResource(R.drawable.img_phis_cream);
                        break;
                    case "Заварной":
                        sum_cream = 450;
                        name_cream = "зав + ";
                        binding.imgCream.setImageResource(R.drawable.img_cus_cream);
                        break;
                    case "Сливочный":
                        sum_cream = 550;
                        name_cream = "слив + ";
                        binding.imgCream.setImageResource(R.drawable.img_cr_cream);
                        break;
                }
                name_position = name_biscuit + name_cream + name_topping;
                sum = sum_biscuit + sum_cream + sum_topping;
                binding.summaryOrder.setText(sum + " руб");
                //binding.txtCream.setText(selected_cream + " крем");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> choose_topping = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, position_repository.getToppings());
        choose_topping.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.topping.setAdapter(choose_topping);
        binding.topping.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] choose = position_repository.getToppings();
                selected_topping = choose[i];
                switch (selected_topping){
                    case "Ягоды":
                        sum_topping = 700;
                        name_topping = "яг";
                        binding.imgTopping.setImageResource(R.drawable.img_ber_top);
                        //binding.txtTopping.setText("Ягодная начинка");
                        break;
                    case "Фрукты":
                        sum_topping = 500;
                        name_topping = "фр";
                        binding.imgTopping.setImageResource(R.drawable.img_fruit_top);
                        //binding.txtTopping.setText("Фруктовая начинка");
                        break;
                    case "Орехи":
                        sum_topping = 900;
                        name_topping = "ор";
                        binding.imgTopping.setImageResource(R.drawable.img_nuts_top);
                        //binding.txtTopping.setText("Ореховая начинка");
                        break;
                }
                name_position = name_biscuit + name_cream + name_topping;
                sum = sum_biscuit + sum_cream + sum_topping;
                binding.summaryOrder.setText(sum + " руб");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> choose_weight = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, position_repository.getWeights());
        choose_weight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.weight.setAdapter(choose_weight);
        binding.weight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] choose = position_repository.getWeights();
                selected_weight = choose[i];
                switch (selected_weight){
                    case "1 кг":
                        copy_sum = sum;
                        break;
                    case "2 кг":
                        copy_sum = sum * 2;
                        break;
                    case "3 кг":
                        copy_sum = sum * 3;
                        break;
                }
                binding.summaryOrder.setText(copy_sum + " руб");
               // binding.txtWeight.setText("Весом " + selected_weight);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        assortmentViewModel = new ViewModelProvider(requireActivity()).get(AssortmentViewModel.class);

        binding.btnAddToBasket.setOnClickListener(view -> {
            assortmentViewModel.addToBasket(new MPositionData(name_position, 1, "", sum, R.drawable.img_cake_basket));
            Toast.makeText(requireActivity(), "Добавлено в корзину!", Toast.LENGTH_SHORT).show();
            ((MainActivity)requireActivity()).getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
            ((MainActivity) requireActivity()).replaceFragment(assortmentFragment, "AssortmentFragment");
        });


        binding.btnProfile.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new ProfileFragment(), "ProfileFragment");
            MainActivity.bottomNavigationView.setSelectedItemId(R.id.page_4);
        });

        return binding.getRoot();
    }
}
