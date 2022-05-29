package com.example.app_for_bakery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.app_for_bakery.databinding.ActivityMainBinding;
import com.example.app_for_bakery.vm_assortment.AssortmentFragment;
import com.example.app_for_bakery.vm_basket.BasketFragment;
import com.example.app_for_bakery.vm_list_of_orders.ListOfOrdersFragment;
import com.example.app_for_bakery.vm_login.LoginFragment;
import com.example.app_for_bakery.vm_profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.yandex.mapkit.MapKitFactory;


public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    private ActivityMainBinding binding;
    public static BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (savedInstanceState == null) { //для первого запуска
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .addToBackStack("LoginFragment").commit();
        }
        bottomNavigationView = binding.bottomNavigation;
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.page_1:
                replaceFragment(new AssortmentFragment(), "AssortmentFragment");
                return true;
            case R.id.page_2:
                replaceFragment(new BasketFragment(), "BasketFragment");
                return true;
            case R.id.page_3:
                replaceFragment(new ListOfOrdersFragment(), "ListOfOrdersFragment");
                return true;
            case R.id.page_4:
                replaceFragment(new ProfileFragment(), "ProfileFragment");
                return true;
        }
        return false;
    }

    //управление фрагментами
    public void replaceFragment(Fragment fragment, String tag) {
        //ставим видимость bottom navigation
        bottomNavigationView.setVisibility(needNavigation(tag) ? View.VISIBLE : View.GONE);

        //получаем текущий фрагмент в контейнере
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);

        //предотвращаем добавление одного и того же фрагмента в контейнер
        if (currentFragment != null && currentFragment.getClass() == fragment.getClass())
            return;

        //если фрагмент уже есть в стеке, возвращаем его оттуда, чтобы предотратить бесконечный рост стека
        if (getSupportFragmentManager().findFragmentByTag(tag) != null)
            getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        //иначе добавляем новый фрагмент
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.container, fragment, tag)
                .commit();
    }

    public boolean needNavigation(String class_name){
        boolean flag = true;
        switch (class_name){
            case "AboutUsFragment":
            case "LoginFragment":
            case "ChatFragment":
            case "RegisterFragment":
                flag = false;
                break;
        }
        return flag;
    }

    public void setPageSelected(String class_name){
        switch (class_name){
            case "AssortmentFragment":
                bottomNavigationView.setSelectedItemId(R.id.page_1);
                break;
            case "BasketFragment":
                bottomNavigationView.setSelectedItemId(R.id.page_2);
                break;
            case "ListOfOrdersFragment":
                bottomNavigationView.setSelectedItemId(R.id.page_3);
                break;
            case "ProfileFragment":
                bottomNavigationView.setSelectedItemId(R.id.page_4);
                break;
        }
    }

    //выход из приложения
    @Override
    public void onBackPressed() {
        int fragmentsInStack = getSupportFragmentManager().getBackStackEntryCount(); //количество фрагментов в стеке
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container); //текущий фрагмент
        if (fragmentsInStack > 1) {  //если у нас более одного фрагмента, очищаем стек
            getSupportFragmentManager().popBackStack();
            if (currentFragment.getTag().equals("AssortmentFragment"))
                finish();
            if (currentFragment.getTag().equals("ChatFragment"))
                bottomNavigationView.setSelectedItemId(R.id.page_3);
            if (needNavigation(currentFragment.getTag()))
                bottomNavigationView.setSelectedItemId(R.id.page_1);
        }
        else if (fragmentsInStack == 1)
            finish();
        else
            super.onBackPressed();
        }

    private boolean initialized_map = false;
    public void initialize_map(String apiKey, Context context){
        if (initialized_map)
            return;
        MapKitFactory.setApiKey(apiKey);
        MapKitFactory.initialize(context);
        initialized_map = true;
    }

    public void hideBottomNavigation() {
        bottomNavigationView.setVisibility(View.GONE);
    }

    public void showBottomNavigation() {
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }
}