package com.example.app_for_bakery.vm_about_order;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {
    static final String PAGE_NUMBER_POSITION = "page_number_position";
    static final String PAGE_IMAGE = "page_image";
    int cardNumber;
    int img_address;
    private FragmentGalleryBinding binding;
    public String tag = "GalleryFragment";

    public static GalleryFragment newInstance(int page, int address) {
        GalleryFragment pageFragment = new GalleryFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(PAGE_NUMBER_POSITION, page);
        arguments.putInt(PAGE_IMAGE, address);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardNumber = getArguments() != null ? getArguments().getInt(PAGE_NUMBER_POSITION) : 0;
        img_address = getArguments() != null ? getArguments().getInt(PAGE_IMAGE) : 0;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false);
        binding.imgGalleryDiscusOrder.setAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
        set_image(img_address);
        return binding.getRoot();
    }

    public void set_image(Integer address){
        binding.imgGalleryDiscusOrder.setImageResource(address);
    }
}

