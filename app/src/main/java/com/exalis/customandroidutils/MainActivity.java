package com.exalis.customandroidutils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.exalis.androidutils.ChoosingPictureHandler;
import com.exalis.androidutils.CustomSnackbar;
import com.exalis.androidutils.CustomToast;
import com.exalis.customandroidutils.databinding.MainActivityBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    private ChoosingPictureHandler choosingPictureHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnFailure.setOnClickListener(view -> {
            CustomSnackbar.makeFailureSnackbar("Echec", this, binding.getRoot());
        });

        binding.btnSucess.setOnClickListener(view -> {
            CustomSnackbar.makeSuccessSnackbar("Bravo", this, binding.getRoot());
        });

        binding.btnSucessToast.setOnClickListener(view -> {
            CustomToast.makeSuccessToast(this, "Youpiiiii");
        });

        binding.btnFailureToast.setOnClickListener(view -> {
            CustomToast.makeFailureToast(this, "Ha miiiiiiiiiiince");
        });

        choosingPictureHandler = new ChoosingPictureHandler(this);
        binding.btnPictureChoosing.setOnClickListener(v -> {
            choosingPictureHandler.getAskingDialog().show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            Bitmap bitmap = choosingPictureHandler.getBitmapResult(requestCode, resultCode, data);

            binding.image.setVisibility(View.VISIBLE);
            binding.image.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}