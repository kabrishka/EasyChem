package com.example.easychem.ui.achievements;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easychem.AchievementsDBHelper;
import com.example.easychem.R;

import java.util.ArrayList;


public class AchivFragment extends Fragment {

    AchievementsDBHelper achievementsDBHelper;

    AchievementsAdapter achievementsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_achieves, container, false);

        achievementsDBHelper = new AchievementsDBHelper(getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView recyclerAchievements = (RecyclerView) view.findViewById(R.id.id_RV_achievements);
        achievementsAdapter = new AchievementsAdapter();
        recyclerAchievements.setAdapter(achievementsAdapter);
        recyclerAchievements.setLayoutManager(layoutManager);

        return view;
    }

    public class AchievementsAdapter extends RecyclerView.Adapter<AchievementsAdapter.AchievementHolder> {
        ArrayList<AchievementHolder> achievement_holder_array = new ArrayList<>();

        SQLiteDatabase database = achievementsDBHelper.getWritableDatabase();

        @Override
        public AchievementHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            //holder строится на основе rv_item_achievement
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rv_item_achievement, parent, false);

            AchievementHolder holder = new AchievementHolder(v);
            achievement_holder_array.add(holder);

            return holder;
        }

        @Override
        public void onBindViewHolder(AchievementHolder holder, int position) {
            Cursor cursor;
            cursor = database.query(AchievementsDBHelper.TABLE_ACHIEVEMENTS, null, null, null, null, null, AchievementsDBHelper.KEY_ID + " ASC");
            cursor.moveToPosition(position);

            int achievement_name_index = cursor.getColumnIndex(AchievementsDBHelper.KEY_ACHIEVEMENT_NAME);
            int progress_index = cursor.getColumnIndex(AchievementsDBHelper.KEY_PROGRESS);
            int goal_index = cursor.getColumnIndex(AchievementsDBHelper.KEY_GOAL);

            holder.TV_ach_name.setText(cursor.getString(achievement_name_index));
            holder.TV_progress.setText(cursor.getString(progress_index));
            holder.TV_goal.setText(cursor.getString(goal_index));

            // (!) - Проверить, меняется ли цвет
            if (holder.TV_progress.getText().toString().equals(holder.TV_goal.getText().toString()))
                holder.CL_border.setBackgroundColor(getResources().getColor(R.color.green));

            cursor.close();
        }

        @Override
        public int getItemCount() {
            long db_size = 0;

            db_size = DatabaseUtils.queryNumEntries(database, AchievementsDBHelper.TABLE_ACHIEVEMENTS);
            return (int) db_size;
        }

        class AchievementHolder extends RecyclerView.ViewHolder {

            ConstraintLayout CL_border;
            TextView TV_ach_name;
            TextView TV_progress;
            TextView TV_goal;

            AchievementHolder(View itemView) {
                super(itemView);

                CL_border = itemView.findViewById(R.id.id_constraint_layout_border);
                TV_ach_name = itemView.findViewById(R.id.id_achievement_name);
                TV_progress = itemView.findViewById(R.id.id_text_progress);
                TV_goal = itemView.findViewById(R.id.id_text_goal);
            }

        }

    }

}