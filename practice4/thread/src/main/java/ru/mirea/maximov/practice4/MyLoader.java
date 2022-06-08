package ru.mirea.maximov.practice4;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class MyLoader extends AsyncTaskLoader<String> {
    int lessonsCount;
    int daysCount;

    public static final String ARG_LESSONS_COUNT = "lessons";
    public static final String ARG_DAYS_COUNT = "days";

    public MyLoader(@NonNull Context context, Bundle args)
    {
        super(context);
        if(args != null){
            lessonsCount = args.getInt(ARG_LESSONS_COUNT);
            daysCount = args.getInt(ARG_DAYS_COUNT);
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        Log.i("MyLoader","Entry");

        SystemClock.sleep(3*1000);

        int averageLessonsPerDay = lessonsCount/daysCount;

        Log.i("MyLoader","Среднее количество пар в день = " + averageLessonsPerDay );

        return Integer.toString(averageLessonsPerDay);
    }
}
