package com.example.easychem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class AchievementsDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "AchievementsDB";
    public static final String TABLE_ACHIEVEMENTS = "Achievements";
    public static final int DB_VERSION = 1;

    public static final String KEY_ID = "_id";
    public static final String KEY_ACHIEVEMENT_NAME = "Achievement_name";
    public static final String KEY_PROGRESS = "Progress";
    public static final String KEY_GOAL = "Goal";

    //Конструктор
    public AchievementsDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + TABLE_ACHIEVEMENTS + "(" + KEY_ID + " integer primary key," + KEY_ACHIEVEMENT_NAME + " text," + KEY_PROGRESS + " integer," + KEY_GOAL + " integer" + ") ");
        insertAchievement(db, "Первые шаги", 0, 1);
        insertAchievement(db, "Молекулы - моё всё", 0, 3);
        insertAchievement(db, "Знаток строения атома", 0, 5);
        insertAchievement(db, "Служитель периодического закона", 0, 5);
        insertAchievement(db, "Какова моя степень окисления?", 0, 5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + TABLE_ACHIEVEMENTS);

        onCreate(db);
    }

    private static void insertAchievement(SQLiteDatabase db, String achievement_name, int progress, int goal){
        ContentValues contentValues = new ContentValues();

        contentValues.put(AchievementsDBHelper.KEY_ACHIEVEMENT_NAME, achievement_name);
        contentValues.put(AchievementsDBHelper.KEY_PROGRESS, progress);
        contentValues.put(AchievementsDBHelper.KEY_GOAL, goal);

        db.insert(AchievementsDBHelper.TABLE_ACHIEVEMENTS, null, contentValues);
        contentValues.clear();
    }

}
