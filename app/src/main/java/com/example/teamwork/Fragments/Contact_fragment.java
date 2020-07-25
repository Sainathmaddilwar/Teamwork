package com.example.teamwork.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.teamwork.MainActivity;
import com.example.teamwork.R;

public class Contact_fragment extends Fragment {
    final int RequestCall=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.contact,container,false);
        TextView phone=(TextView)view.findViewById(R.id.phone);
        TextView email=(TextView)view.findViewById(R.id.frag1mail);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail="abcde@xyz.com";
                Sendmail(mail);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number="+91 9123456789";
                Makephonecall(number);
            }
        });
      return view;
    }

    private void Sendmail(String mail) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, mail);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RequestCall) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String number="+91 9123456789";
                Makephonecall(number);
            } else {
                Toast.makeText(getContext(), "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void Makephonecall(String number) {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE}, RequestCall);
        } else {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }

    }
}
