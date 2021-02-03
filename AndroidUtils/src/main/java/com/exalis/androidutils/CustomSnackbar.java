package com.exalis.androidutils;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.google.android.material.snackbar.Snackbar;

public class CustomSnackbar {

    public static void makeSuccessSnackbar(String message, Activity activity, View parentView,
                                           Integer... offsetValues) {
        Snackbar snackbar = getSnackBar(activity, parentView);

        setSnackbarIcon(activity, snackbar, R.drawable.check);
        setSnackbarText(message, snackbar);
        setSnackbarBackgroundColor(activity, snackbar, R.color.colorSuccess);

        if(offsetValues.length > 0)
            addOffset(offsetValues, snackbar);

        snackbar.show();
    }

    public static void makeFailureSnackbar(String message, Activity activity, View parentView,
                                           Integer... offsetValues) {
        Snackbar snackbar = getSnackBar(activity, parentView);

        setSnackbarIcon(activity, snackbar, R.drawable.cross);
        setSnackbarText(message, snackbar);
        setSnackbarBackgroundColor(activity, snackbar, R.color.colorFailure);

        if(offsetValues.length > 0)
            addOffset(offsetValues, snackbar);

        snackbar.show();
    }

    public static void makeInformativeSnackbar(String message, Activity activity, View parentView,
                                           Integer... offsetValues) {
        Snackbar snackbar = getSnackBar(activity, parentView);

        setSnackbarText(message, snackbar);
        setSnackbarBackgroundColor(activity, snackbar, R.color.white);
        removeIconFromSnackbar(snackbar);

        if(offsetValues.length > 0)
            addOffset(offsetValues, snackbar);

        snackbar.show();
    }

    private static void removeIconFromSnackbar(Snackbar snackbar) {
        ((ImageView) snackbar.getView().findViewById(R.id.icon))
                .setVisibility(View.GONE);
    }

    public static void setSnackbarBackgroundColor(Activity activity, Snackbar snackbar, int color) {
        ((CardView) snackbar.getView().findViewById(R.id.parent_view))
                .setCardBackgroundColor(activity.getResources().getColor(color));
    }

    public static void setSnackbarIcon(Activity activity, Snackbar snackbar, int drawable) {
        ((ImageView) snackbar.getView().findViewById(R.id.icon))
                .setImageDrawable(activity.getResources().getDrawable(drawable));
    }

    public static void setSnackbarText(String text, Snackbar snackbar) {
        ((TextView) snackbar.getView().findViewById(R.id.message)).setText(text);
    }

    public static void addOffset(Integer[] offsetValues, Snackbar snackbar) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)
                ((FrameLayout)snackbar.getView()).getChildAt(0).getLayoutParams();

        params.setMargins(params.leftMargin + getOffset(offsetValues, 0),
                params.topMargin + getOffset(offsetValues, 1) ,
                params.rightMargin+ getOffset(offsetValues, 2),
                params.bottomMargin+ getOffset(offsetValues, 3));

        ((FrameLayout)snackbar.getView()).getChildAt(0).setLayoutParams(params);
    }

    private static int getOffset(Integer[] offsetValues, int value){
        if(offsetValues.length >= value)
            return offsetValues[value];

        return 0;
    }

    public static Snackbar getSnackBar(Activity activity, View parentView) {
        final Snackbar snackbar = Snackbar.make(parentView, "", Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);

        View custom_view = activity.getLayoutInflater().inflate(R.layout.toast_icon_text, null);

        final FrameLayout snackBarView = (FrameLayout) snackbar.getView();
        snackBarView.addView(custom_view, 0);

        return snackbar;
    }

    public static void setSnackBarGravity(Snackbar snackbar, int gravity){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)
                ((FrameLayout)snackbar.getView()).getChildAt(0).getLayoutParams();
        params.gravity = gravity;
        snackbar.getView().setLayoutParams(params);

    }

}
