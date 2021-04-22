package com.exalis.customandroidutils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.exalis.androidutils.Interface.IBannerMessaging;
import com.exalis.androidutils.Messaging.BannerMessage;
import com.exalis.androidutils.PictureHandling.ChoosingPictureHandler;
import com.exalis.customandroidutils.databinding.MainActivityBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements IBannerMessaging {

    private MainActivityBinding binding;
    private ChoosingPictureHandler choosingPictureHandler;
    private BannerMessage bannerMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        choosingPictureHandler = new ChoosingPictureHandler(this);
        binding.btnPictureChoosing.setOnClickListener(v -> {
            choosingPictureHandler.getAskingDialog().show();
        });

        binding.bannerError.setOnClickListener(v -> {
            bannerMessage = new BannerMessage(this, binding.getRoot());
            bannerMessage.setMessage("Error");
            bannerMessage.setTitle("Message d'erreur");
            bannerMessage.setTheme(BannerMessage.BannerMessageTheme.ERROR);
            bannerMessage.setPermanent(true);
            bannerMessage.showBanner();
        });

        binding.bannerSuccess.setOnClickListener(v -> {
            bannerMessage = new BannerMessage(this, binding.getRoot());
            bannerMessage.setMessage("Success");
            bannerMessage.setTitle("Message de succès");
            bannerMessage.setTheme(BannerMessage.BannerMessageTheme.SUCCESS);
            bannerMessage.setPermanent(true);
            bannerMessage.showBanner();
        });

        binding.bannerWarning.setOnClickListener(v -> {
            bannerMessage = new BannerMessage(this, binding.getRoot());
            bannerMessage.setMessage("Warning");
            bannerMessage.setTitle("Message de warning");
            bannerMessage.setTheme(BannerMessage.BannerMessageTheme.WARNING);
            bannerMessage.setPermanent(true);
            bannerMessage.showBanner();
        });

        binding.bannerInformation.setOnClickListener(v -> {
            bannerMessage = new BannerMessage(this, binding.getRoot());
            bannerMessage.setMessage("Information");
            bannerMessage.setTitle("Message d'information");
            bannerMessage.setTheme(BannerMessage.BannerMessageTheme.INFORMATION);
            bannerMessage.setPermanent(true);
            bannerMessage.showBanner();
        });

        binding.bannerErrorBottom.setOnClickListener(v -> {
            bannerMessage = new BannerMessage(this, binding.getRoot());
            bannerMessage.setMessage("Error");
            bannerMessage.setTitle("Message d'erreur");
            bannerMessage.setTheme(BannerMessage.BannerMessageTheme.ERROR);
            bannerMessage.setPermanent(true);
            bannerMessage.setComesFromBottom(true, 20);
            bannerMessage.showBanner();
        });

        binding.bannerSuccessBottom.setOnClickListener(v -> {
            bannerMessage = new BannerMessage(this, binding.getRoot());
            bannerMessage.setMessage("Success");
            bannerMessage.setTitle("Message de succès");
            bannerMessage.setTheme(BannerMessage.BannerMessageTheme.SUCCESS);
            bannerMessage.setPermanent(true);
            bannerMessage.setComesFromBottom(true, 20);
            bannerMessage.showBanner();
        });

        binding.bannerWarningBottom.setOnClickListener(v -> {
            bannerMessage = new BannerMessage(this, binding.getRoot());
            bannerMessage.setMessage("Warning");
            bannerMessage.setTitle("Message de warning");
            bannerMessage.setTheme(BannerMessage.BannerMessageTheme.WARNING);
            bannerMessage.setPermanent(true);
            bannerMessage.setComesFromBottom(true, 20);
            bannerMessage.showBanner();
        });

        binding.bannerInformationBottom.setOnClickListener(v -> {
            bannerMessage = new BannerMessage(this, binding.getRoot());
            bannerMessage.setMessage("Information");
            bannerMessage.setTitle("Message d'information");
            bannerMessage.setTheme(BannerMessage.BannerMessageTheme.INFORMATION);
            bannerMessage.setPermanent(true);
            bannerMessage.setComesFromBottom(true, 20);
            bannerMessage.showBanner();
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


    @Override
    public void onBannerActionClicked(View view) {
        bannerMessage.dissmissBanner();
    }



}