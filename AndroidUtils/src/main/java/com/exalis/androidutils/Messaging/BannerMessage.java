package com.exalis.androidutils.Messaging;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.exalis.androidutils.R;
import com.exalis.androidutils.ViewAnimation;
import com.exalis.androidutils.databinding.*;

import java.util.HashMap;

public class BannerMessage {

    private static final String TAG = "BannerMessage";
    private ViewGroup parentView;
    private BannerBinding bannerBinding;
    private Context context;
    private int CLOSING_DELAY = 2500;
    private boolean isPermanent = false;
    private boolean comesFromBottom = false;
    int bottomMargin;
    private boolean hasAlreadyAParentView = false;
    boolean hasDefinedCustomTheme = false;

    private int customColorBackground, customColorOnBackground, icon;

    public enum BannerMessageTheme{
        SUCCESS,
        INFORMATION,
        ERROR,
        WARNING,
        CUSTOM
    }

    public BannerMessage(Context context, ViewGroup parentView) {
        this.context = context;
        this.parentView = parentView;
        initView();
    }

    private void initView() {
        bannerBinding = BannerBinding.inflate(LayoutInflater.from(context));
    }

    public void setMessage(String message) {
        bannerBinding.bannerTextContent.setText(message);
    }

    private void createView() {
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                100
        );

        if(comesFromBottom){
            params.gravity = Gravity.BOTTOM;
            params.setMargins(20, 20, 20, bottomMargin);
        }else{
            params.setMargins(20, 20, 20, 20);
        }

        bannerBinding.getRoot().setLayoutParams(params);

        if(hasAlreadyAParentView) return;

        parentView.addView(bannerBinding.getRoot());
        hasAlreadyAParentView = true;
    }

    public void setTitle(String title) {
        bannerBinding.bannerTitleContent.setText(title);
    }

    public void setIcon(Drawable icon) {
        bannerBinding.icon.setImageDrawable(icon);
    }

    public void setBackground(int color) {
        bannerBinding.banner.setCardBackgroundColor(color);
    }

    public void setClosingDelay(int CLOSING_DELAY) {
        this.CLOSING_DELAY = CLOSING_DELAY;
    }

    public void setComesFromBottom(boolean comesFromBottom, int bottomMargin) {
        this.bottomMargin = bottomMargin;
        this.comesFromBottom = comesFromBottom;
    }

    public void setButtonAction(View.OnClickListener clickListener){
        bannerBinding.bannerButton.setOnClickListener(clickListener);
    }

    public void setButtonText(String text){
        bannerBinding.bannerButton.setText(text);
    }

    public void showBanner() {
        createView();
        ViewAnimation.expand(bannerBinding.getRoot());

        if(!isPermanent){
            new Handler(context.getMainLooper()).postDelayed(() ->
                            ViewAnimation.collapse(bannerBinding.getRoot()),
                    CLOSING_DELAY);
        }
    }

    public void dissmissBanner() {
        ViewAnimation.collapse(bannerBinding.getRoot());
    }

    public void setPermanent(boolean permanent) {
        isPermanent = permanent;

        bannerBinding.bannerButton.setVisibility(View.VISIBLE);
    }

    public void addButton(){
        bannerBinding.bannerButton.setVisibility(View.VISIBLE);
    }
    
    public void removeButton(){
        bannerBinding.bannerButton.setVisibility(View.GONE);
    }

    public void setTheme(BannerMessageTheme theme)
    {
        switch (theme){
            case ERROR:
                setBackground(context.getResources().getColor(R.color.colorError));
                //Icon
                setIcon(context.getDrawable(R.drawable.error_icon));
                bannerBinding.icon.setColorFilter(context.getResources().getColor(R.color.colorOnError));
                //Text
                changeTextColors(R.color.colorOnError);
                //Button
                changeButtonColors(R.color.colorOnError, R.color.colorError);
                break;
            case SUCCESS:
                setBackground(context.getResources().getColor(R.color.colorSuccess));
                //Icon
                setIcon(context.getDrawable(R.drawable.success_icon));
                bannerBinding.icon.setColorFilter(context.getResources().getColor(R.color.colorOnSuccess));
                //Text
                changeTextColors(R.color.colorOnSuccess);
                //Button
                changeButtonColors(R.color.colorOnSuccess, R.color.colorSuccess);
                break;
            case WARNING:
                setBackground(context.getResources().getColor(R.color.colorWarning));
                //Icon
                setIcon(context.getDrawable(R.drawable.warning_icon));
                bannerBinding.icon.setColorFilter(context.getResources().getColor(R.color.colorOnWarning));
                //Text
                changeTextColors(R.color.colorOnWarning);
                //Button
                changeButtonColors(R.color.colorOnWarning, R.color.colorWarning);
                break;
            case INFORMATION:
                setBackground(context.getResources().getColor(R.color.colorInformation));
                //Icon
                setIcon(context.getDrawable(R.drawable.information_icon));
                bannerBinding.icon.setColorFilter(context.getResources().getColor(R.color.colorOnInformation));
                //Text
                changeTextColors(R.color.colorOnInformation);
                //Button
                changeButtonColors(R.color.colorOnInformation, R.color.colorInformation);
                break;
            case CUSTOM:
                if(hasDefinedCustomTheme){
                    setBackground(context.getResources().getColor(customColorBackground));
                    //Icon
                    setIcon(context.getDrawable(icon));
                    bannerBinding.icon.setColorFilter(context.getResources().getColor(customColorOnBackground));
                    //Text
                    changeTextColors(customColorOnBackground);
                    //Button
                    changeButtonColors(R.color.colorOnInformation, customColorBackground);
                }else{
                    Log.e(TAG, "setTheme: le theme custom n'a pas été défini" );
                }
                break;
        }
    }

    private void changeButtonColors(int backgroundColor, int colorOnThisBackground) {
        bannerBinding.bannerButton.setBackgroundColor(context.getResources().getColor(backgroundColor));
        bannerBinding.bannerButton.setTextColor(context.getResources().getColor(colorOnThisBackground));
    }

    private void changeTextColors(int color) {
        bannerBinding.bannerTitleContent.setTextColor(context.getResources().getColor(color));
        bannerBinding.bannerTextContent.setTextColor(context.getResources().getColor(color));
    }

    public void setCustomTheme(int customColorBackground, int customColorOnBackground, int icon){
        this.customColorBackground = customColorBackground;
        this.customColorOnBackground = customColorOnBackground;
        this.icon = icon;
        hasDefinedCustomTheme = true;
    }
}
