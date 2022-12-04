package com.example.easychem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.easychem.ui.themes.ThemesFragment;

public class ThemeDetailActivity extends Activity {
    private TextView textViewTitle;
    private TextView textViewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_detail);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewContent = findViewById(R.id.textViewContent);

        Intent intent = getIntent();
        if (intent.hasExtra("title") && intent.hasExtra("content")) {
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");

            textViewTitle.setText(title);
            textViewContent.setText(content);
        } else {
            Intent backToThemes = new Intent(this, ThemesFragment.class);
            startActivity(backToThemes);
        }
    }
}
