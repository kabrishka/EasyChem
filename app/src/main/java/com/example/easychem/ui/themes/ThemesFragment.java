package com.example.easychem.ui.themes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.easychem.R;
import com.example.easychem.ThemeDetailActivity;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class ThemesFragment extends Fragment {
    String TAG = "ThemesFragment";

    private TextView textViewTitle;
    private ListView listViewThemes;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Раздуваем макет для этого фрагмента
        View view;
        view = inflater.inflate(R.layout.fragment_themes, container, false);

        ArrayList<Theme> themes = new ArrayList<>();

        textViewTitle = view.findViewById(R.id.textViewTitle);
        listViewThemes = view.findViewById(R.id.listViewThemes);

        XmlPullParser xpp = getResources().getXml(R.xml.themes);
        ThemeResourceParser parser = new ThemeResourceParser();
        if(parser.parse(xpp))
        {
            textViewTitle.setText(parser.getTitle());
            for(Theme theme : parser.getThemes()) {
                themes.add(theme);
            }
        }

        ArrayAdapter<Theme> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, themes);
        listViewThemes.setAdapter(adapter);

        listViewThemes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Theme theme = themes.get(position);
                Intent intent = new Intent(getContext(), ThemeDetailActivity.class);
                intent.putExtra("title", theme.getTitle());
                intent.putExtra("content", theme.getContent());
                startActivity(intent);
            }
        });
        return view;
    }
}