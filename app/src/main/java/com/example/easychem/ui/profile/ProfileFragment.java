package com.example.easychem.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easychem.MainActivity;
import com.example.easychem.R;


public class ProfileFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tvUserEmail = view.findViewById(R.id.tvUserEmail);
        TextView tvUserPass = view.findViewById(R.id.tvUserPass);

        tvUserEmail.setText(MainActivity.user.getEmail());
        tvUserPass.setText(MainActivity.user.getPassword());


        return view;
    }
}