package com.example.easychem.ui.tests;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.easychem.AchievementsDBHelper;
import com.example.easychem.R;

import java.util.ArrayList;

public class TestsDetailActivity extends AppCompatActivity {

    AchievementsDBHelper achievementsDBHelper;

    ArrayList<String> tasks_array = new ArrayList<>();
    ArrayList<String> vars_array_1 = new ArrayList<>();
    ArrayList<String> vars_array_2 = new ArrayList<>();
    ArrayList<String> vars_array_3 = new ArrayList<>();
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
    public void finish(){
        super.finish();

        SQLiteDatabase database = achievementsDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(AchievementsDBHelper.KEY_PROGRESS, "1");

        int updCount = database.update(AchievementsDBHelper.TABLE_ACHIEVEMENTS, contentValues, AchievementsDBHelper.KEY_ID + " = ?", new String[]{"1"});
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_detail);
        getSupportActionBar().hide();

        current_task = 0;

        achievementsDBHelper = new AchievementsDBHelper(this);

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



        tasks_array.add("Об азоте как о простом веществе сказано в следующем предложении.");
        tasks_array.add("О железе как о простом веществе говорится в следующем предложении.");
        tasks_array.add("Выберите два высказывания, в которых говорится о фосфоре как о химическом элементе:");

        text_task.setText(tasks_array.get(current_task));

        vars_array_1.add("1) Растениям нужен азот для построения молекул белков.");
        vars_array_1.add("2) Молекула аммиака состоит из атомов азота и водорода.");
        vars_array_1.add("3) С минеральными удобрениями азот вносится в почву.");
        vars_array_1.add("4) Азотом наполняют электролампы.");
        vars_array_2.add("1) В кожуре яблок содержится железо.");
        vars_array_2.add("2) Для получения железа  оксид железа(III) нагревают с углем.");
        vars_array_2.add("3) Железо входит в состав хлорида железа(III).");
        vars_array_2.add("4) При малокровии употребляют лекарства, содержащие железо.");
        vars_array_3.add("1) Молекула фосфина состоит из трёх атомов водорода и одного атома фосфора");
        vars_array_3.add("2) Фосфор входит в состав смеси, наносимой на стенку спичечной коробки");
        vars_array_3.add("3) Фосфор имеет несколько аллотропных модификаций");
        vars_array_3.add("4) Фосфор входит в состав растительных и животных белков");
        vars_array_3.add("5) Чёрный фосфор обладает полупроводниковыми свойствами");

        answer_array.add("4");
        answer_array.add("2");
        answer_array.add("14");

        ArrayAdapter<String> adapter_1 = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, vars_array_1);
        ArrayAdapter<String> adapter_2 = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, vars_array_2);
        ArrayAdapter<String> adapter_3 = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, vars_array_3);

        test_list.setAdapter(adapter_1);

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

                    switch (current_task){
                        case 1:
                            test_list.setAdapter(adapter_2);
                            break;
                        case 2:
                            test_list.setAdapter(adapter_3);
                            break;
                    }

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
