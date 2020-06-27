package vn.edu.ntu.dinhtuyen.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import vn.edu.ntu.dinhtuyen.dao.CustomerDao;
import vn.edu.ntu.dinhtuyen.model.CustomerEntity;

@Database(entities = CustomerEntity.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CustomerDao customerDao();
}
