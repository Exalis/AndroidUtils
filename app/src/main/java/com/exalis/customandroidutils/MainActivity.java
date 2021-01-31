package com.exalis.customandroidutils;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;
import com.exalis.androidutils.CustomSnackbar;
import com.exalis.androidutils.CustomToast;
import com.exalis.customandroidutils.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         binding.btnFailure.setOnClickListener(view ->{
             CustomSnackbar.makeFailureSnackbar("Echec", this, binding.getRoot());
         });
        binding.btnSucess.setOnClickListener(view ->{
            CustomSnackbar.makeSuccessSnackbar("Bravo", this, binding.getRoot());
        });
        binding.btnSucessToast.setOnClickListener(view ->{
            CustomToast.makeSuccessToast(this, "Youpiiiii");
        });
        binding.btnFailureToast.setOnClickListener(view ->{
            CustomToast.makeFailureToast(this, "Ha miiiiiiiiiiince");
        });

    }
}