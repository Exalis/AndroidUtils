package com.exalis.androidutils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;

public class CustomToast {

    public static void makeSuccessToast(Activity activity, String message){
        Toast toast = getToast(activity);

        setMessage(toast, message);
        setBackgroundColor(toast, activity.getResources().getColor(R.color.colorSuccess));
        setIcon(toast, activity.getResources().getDrawable(R.drawable.check));

        toast.show();
    }

    public static void makeFailureToast(Activity activity, String message){
        Toast toast = getToast(activity);

        setMessage(toast, message);
        setBackgroundColor(toast, activity.getResources().getColor(R.color.colorFailure));
        setIcon(toast, activity.getResources().getDrawable(R.drawable.cross));

        toast.show();
    }

    public static Toast getToast(Activity activity){
        Toast toast = new Toast(activity);
        toast.setView(activity.getLayoutInflater().inflate(R.layout.toast_icon_text, null));
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
