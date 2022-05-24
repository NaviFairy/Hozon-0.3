package com.alg.hozon_01.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.alg.hozon_01.ECategorias;
import com.alg.hozon_01.CategoriasDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = ECategorias.class, version = 1, exportSchema = false)
abstract public class AppRoomDatabase extends RoomDatabase {
    public abstract CategoriasDao categoriasDao();

    private static volatile AppRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppRoomDatabase.class, "categorias")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(new PopulateCategorias(INSTANCE));
           // databaseWriteExecutor.execute(new PopulateGuisos(INSTANCE));

        }


    };
}
