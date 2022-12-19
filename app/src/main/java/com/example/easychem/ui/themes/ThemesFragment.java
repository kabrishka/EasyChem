package com.example.easychem.ui.themes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.easychem.R;

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
            themes.addAll(parser.getThemes());
        }

        ArrayAdapter<Theme> adapter = new ArrayAdapter<>(requireContext(), R.layout.theme_item, R.id.tvTheme ,themes);
        listViewThemes.setAdapter(adapter);

        listViewThemes.setOnItemClickListener((adapterView, view1, position, id) -> {
            Theme theme = themes.get(position);
            Intent intent = new Intent(getContext(), ThemeDetailActivity.class);
            intent.putExtra("title", theme.getTitle());
            intent.putExtra("content", theme.getContent());
            startActivity(intent);
        });
        return view;
    }
}