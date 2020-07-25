package com.example.teamwork.Fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.teamwork.R;

import java.io.IOException;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class Images_fragment extends Fragment {
    private static final int PICK_IMAGE = 1;
    ImageView imageView;
    Uri imageUri;
    Button Open;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image, container, false);
        Open = (Button) view.findViewById(R.id.Gallery);
        imageView = (ImageView) view.findViewById(R.id.Gimage);
        Open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Sellect Picture"), PICK_IMAGE);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),imageUri);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                Open.setVisibility(View.INVISIBLE);
           } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
