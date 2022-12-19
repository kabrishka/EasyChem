package com.example.easychem.ui.themes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easychem.MainActivity;
import com.example.easychem.R;

public class ThemeDetailActivity extends Activity {
    private TextView textViewTitle;
    private TextView textViewContent;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_detail);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewContent = findViewById(R.id.textViewContent);
        btnBack = findViewById(R.id.back_btn);

        Intent intent = getIntent();
        if (intent.hasExtra("title") && intent.hasExtra("content")) {
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");

            textViewTitle.setText(title);
            textViewContent.setText(content);
        } else {
            Intent backToThemes = new Intent(this, MainActivity.class);
            startActivity(backToThemes);
            finish();
        }

        btnBack.setOnClickListener(view -> {
            startActivity(new Intent(ThemeDetailActivity.this,  MainActivity.class));
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ThemeDetailActivity.this,  MainActivity.class));
        finish();
    }
}
