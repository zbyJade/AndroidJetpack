package com.zc.androidjetpack.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.zc.androidjetpack.bean.Shoe;
import com.zc.androidjetpack.db.dao.ShoeDao;

@Database(entities = {Shoe.class}, version = 1, exportSchema = false)
public abstract class ShoeDatabase extends RoomDatabase {

    private static final String DB_NAME = "ShoeDatabase.db";
    private static volatile ShoeDatabase instance;

    public static synchronized ShoeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static ShoeDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                ShoeDatabase.class,
                DB_NAME).build();
    }

    public abstract ShoeDao getShoeDao();
}
