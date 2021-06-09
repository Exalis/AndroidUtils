package com.exalis.customandroidutils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.exalis.androidutils.Messaging.*;
import com.exalis.androidutils.Interface.*;
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

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            Bitmap bitmap = choosingPictureHandler.getBitmapResult(requestCode, resultCode, data);
            binding.image.setVisibility(View.VISIBLE);
            binding.image.setImageBitmap(bitmap);

            Toast.makeText(this, "WIDTH AND HEIGHT : " + bitmap.getWidth() + "/" + bitmap.getHeight(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int calculateInSampleSizes(BitmapFactory.Options options,
                                              int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee a final image
            // with both dimensions larger than or equal to the requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).

            final float totalPixels = width * height;

            // Anything more than 2x the requested pixels we'll sample down further
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }

    @Override
    public void onBannerActionClicked(View view) {
        bannerMessage.dissmissBanner();
    }

}