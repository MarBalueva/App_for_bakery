package com.example.app_for_bakery;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.app_for_bakery.databinding.ReviewWindowBinding;

public class ReviewWindowDialogFragment extends DialogFragment {
    private ReviewWindowBinding binding;
    private AlertDialog.Builder builder;

    public ReviewWindowDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.review_window, null, false);
        builder = new AlertDialog.Builder(getActivity()).setView(binding.getRoot());

        binding.btnReviewPublish.setOnClickListener(view -> {
            if (TextUtils.isEmpty(binding.reviewText.getText())) {
                binding.reviewText.setError("Отзыв не может быть пустым!");
                binding.reviewText.requestFocus();
            }
            else {
                this.dismiss();
                Toast.makeText(getContext(), "Спасибо за отзыв!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> binding.ratingBar.setRating(v));

        return builder.create();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
