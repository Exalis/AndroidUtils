package com.exalis.androidutils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;

public class CustomToast {

    public static void makeSuccessToast(Context context, String message){
        Toast toast = getToast(context);

        setMessage(toast, message);
        setBackgroundColor(toast, context.getResources().getColor(R.color.colorSuccess));
        setIcon(toast, context.getResources().getDrawable(R.drawable.check));

        toast.show();
    }

    public static void makeFailureToast(Context context, String message){
        Toast toast = getToast(context);

        setMessage(toast, message);
        setBackgroundColor(toast, context.getResources().getColor(R.color.colorFailure));
        setIcon(toast, context.getResources().getDrawable(R.drawable.cross));

        toast.show();
    }

    public static void makeInformativeToast(Context context, String message){
        Toast toast = getToast(context);

        setMessage(toast, message);
        setBackgroundColor(toast, context.getResources().getColor(R.color.white));
        setMessageColor(toast, R.color.black);
        removeIcon(toast);

        toast.show();
    }

    private static void setMessageColor(Toast toast, int color) {
        ((TextView)toast.getView().findViewById(R.id.message)).setTextColor(color);
    }

    private static void removeIcon(Toast toast) {
        ((ImageView)toast.getView().findViewById(R.id.icon)).setVisibility(View.GONE);
    }

    public static Toast getToast(Context context){
        Toast toast = new Toast(context);
        toast.setView(LayoutInflater.from(context).inflate(R.layout.toast_icon_text, null));
        toast.setDuration(Toast.LENGTH_LONG);

        return toast;
    }

    public static void setMessage(Toast toast, String message){
        ((TextView)toast.getView().findViewById(R.id.message)).setText(message);
    }

    public static void setBackgroundColor(Toast toast, int color){
        ((CardView)toast.getView().findViewById(R.id.parent_view)).setCardBackgroundColor(color);
    }

    public static void setIcon(Toast toast, Drawable drawable){
        ((ImageView)toast.getView().findViewById(R.id.icon)).setImageDrawable(drawable);
    }
}
