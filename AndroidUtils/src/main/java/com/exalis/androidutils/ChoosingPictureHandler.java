package com.exalis.androidutils;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Button;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class ChoosingPictureHandler {

    private Activity activity;
    public static final int PICK_IMAGE_REQUEST = 700;
    public static final int REQUEST_IMAGE_CAPTURE = 600;
    private Uri imageUri;

    public ChoosingPictureHandler(Activity activity){
        this.activity = activity;
    }

    public Dialog getAskingDialog(){
        Dialog dialog = new Dialog(activity);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.choose_way_of_taking_picture);

        ((Button)dialog.findViewById(R.id.addByCamera)).setOnClickListener(view ->{
            addByCamera();
        });

        ((Button)dialog.findViewById(R.id.addByGalerie)).setOnClickListener(view ->{
            addByGalerie();
        });

        return dialog;
    }

    private void addByGalerie() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void addByCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = activity.getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    public Bitmap getBitmapResult(int requestCode, int resultCode, Intent data) throws IOException {
        switch (requestCode){
            case PICK_IMAGE_REQUEST:
                return onPickImageResult(resultCode, data);
            case REQUEST_IMAGE_CAPTURE:
                return onImageCaptureResult(resultCode, data);
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }

    private Bitmap onImageCaptureResult(int resultCode, Intent data)
            throws IllegalStateException, IOException {
        if(resultCode == RESULT_OK){
            return decodeBitmapFromUri(activity, imageUri);
        }else{
            throw new IllegalStateException("Result is null");
        }
    }

    private Bitmap onPickImageResult(int resultCode, Intent data) throws IOException {
        if(resultCode == RESULT_OK){
            imageUri = data.getData();

            return decodeBitmapFromUri(activity, imageUri);
        }else{
            throw new IOException();
        }
    }

    public static Bitmap decodeBitmapFromUri(Activity activity, Uri uri) throws IOException {
        ImageDecoder.Source source =
                ImageDecoder.createSource(activity.getContentResolver(), uri);
        Bitmap bitmap = ImageDecoder.decodeBitmap(source);
        return bitmap;
    }

    public Uri getImageUri() {
        return imageUri;
    }
}
