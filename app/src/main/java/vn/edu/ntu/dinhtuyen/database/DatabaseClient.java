package vn.edu.ntu.dinhtuyen.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient
{
    private Context context;
    private static DatabaseClient instance;

    private AppDatabase appDatabase;

    public DatabaseClient(Context context) {
        this.context = context;
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "customer").build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
