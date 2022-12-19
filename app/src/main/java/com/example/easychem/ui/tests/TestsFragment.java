package com.example.easychem.ui.tests;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.easychem.R;

import java.util.ArrayList;


public class TestsFragment extends Fragment {

    ArrayList<String> tests_title = new ArrayList<>();
    ArrayAdapter adapter_test;
    ListView tests_list;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tests, container, false);

        tests_title.add("test 1");
        tests_title.add("test 2");
        tests_title.add("test 3");
        tests_title.add("test 4");

        tests_list = view.findViewById(R.id.listViewTests);

        adapter_test = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, tests_title);
        tests_list.setAdapter(adapter_test);

        tests_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                String selectedItem = tests_title.get(position);
                Intent intent = new Intent(getContext(), TestsDetailActivity.class);
                intent.putExtra("text_of_title", selectedItem);
                startActivity(intent);
            }
        });

        return view;
    }
}