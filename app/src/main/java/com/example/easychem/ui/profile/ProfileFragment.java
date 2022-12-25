package com.example.easychem.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easychem.MainActivity;
import com.example.easychem.R;
import com.example.easychem.welcome;


public class ProfileFragment extends Fragment {
    //подключаем БД
//    FirebaseAuth auth; //для авторизации
//    DatabaseReference db; //для работы с таблицами внутри бд

    private TextView tvUserName;
    private TextView tvUserEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserEmail = view.findViewById(R.id.tvUserEmail);

//        db = FirebaseDatabase.getInstance().getReference("Users").child();

//        getDataFromDb();

        return view;
    }

//    private void getDataFromDb() {
//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                tvUserName.setText(dataSnapshot.getValue(User.class).getName());
//                tvUserEmail.setText(dataSnapshot.getValue(User.class).getEmail());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        };
//        db.addValueEventListener(valueEventListener);
//    }
}