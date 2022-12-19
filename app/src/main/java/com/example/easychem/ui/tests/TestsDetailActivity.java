package com.example.easychem.ui.tests;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.easychem.R;

import java.util.ArrayList;

public class TestsDetailActivity extends AppCompatActivity {

    ArrayList<String> tasks_array = new ArrayList<>();
    ArrayList<String> vars_array = new ArrayList<>();
    ArrayList<String> answer_array = new ArrayList<>();
    ArrayAdapter adapter_test;
    TextView title_test;
    TextView text_answer;
    TextView text_task;
    EditText edit_answer;
    ListView test_list;
    Button button_check;
    Button button_next;

    int current_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_detail);

        current_task = 0;

        title_test = findViewById(R.id.textViewTitleTest);
        test_list = findViewById(R.id.listViewTest);
        text_answer = findViewById(R.id.textViewAnswer);
        text_task = findViewById(R.id.textViewNumTask);
        button_check = findViewById(R.id.buttonCheck);
        button_next = findViewById(R.id.buttonNext);
        edit_answer = findViewById(R.id.editTextAnswer);

        Bundle arguments = getIntent().getExtras();
        String current_test = arguments.get("text_of_title").toString();
        title_test.setText(current_test);



        tasks_array.add("task 1");
        tasks_array.add("task 2");
        tasks_array.add("task 3");

        text_task.setText(tasks_array.get(current_task));

        vars_array.add("1) variant 1");
        vars_array.add("2) variant 2");
        vars_array.add("3) variant 3");
        vars_array.add("4) variant 4");

        answer_array.add("12");
        answer_array.add("23");
        answer_array.add("34");

        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, vars_array);
        test_list.setAdapter(adapter);

        text_answer.setVisibility(View.INVISIBLE);
        text_answer.setText(answer_array.get(current_task));
        button_next.setVisibility(View.INVISIBLE);

        button_check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(edit_answer.getText().toString().equals(text_answer.getText().toString())){
                    edit_answer.getBackground()
                            .setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);

                } else {
                    edit_answer.getBackground()
                            .setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_IN);
                }
                button_next.setVisibility(View.VISIBLE);
                text_answer.setVisibility(View.VISIBLE);

                if(current_task == answer_array.size()-1)
                    button_next.setText("finish");

            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(current_task == answer_array.size()-1){
                    finish();
                } else {

                    current_task = current_task + 1;
                    text_answer.setText(answer_array.get(current_task));
                    text_task.setText(tasks_array.get(current_task));

                    edit_answer.setText("");
                    edit_answer.getBackground()
                            .setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    button_next.setVisibility(View.INVISIBLE);
                    text_answer.setVisibility(View.INVISIBLE);
                }
            }
        });

        }



    }
