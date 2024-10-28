package birthdaygreetings.birthdayframe.greetingswishes.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataBaseHelperNamePoem extends SQLiteOpenHelper {
    private static String DB_NAME = "bdaypoem.db";
    private static String DB_PATH = "";
    File dbFile;
    private final Context mContext;
    private SQLiteDatabase mDataBase;

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public DataBaseHelperNamePoem(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
        String str = "/databases/";
        if (VERSION.SDK_INT >= 17) {
            StringBuilder sb = new StringBuilder();
            sb.append(context.getApplicationInfo().dataDir);
            sb.append(str);
            DB_PATH = sb.toString();
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("/data/data/");
        sb2.append(context.getPackageName());
        sb2.append(str);
        DB_PATH = sb2.toString();
    }

    public void createDatabse() throws IOException {
        if (!checkDataBase()) {
            getReadableDatabase();
            close();
            try {
                copyDataBase();
            } catch (Exception unused) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase() {
        StringBuilder sb = new StringBuilder();
        sb.append(DB_PATH);
        sb.append(DB_NAME);
        this.dbFile = new File(sb.toString());
        this.dbFile.exists();
        this.dbFile.delete();
        return this.dbFile.exists();
    }

    private void copyDataBase() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(DB_PATH);
        sb.append(DB_NAME);
        FileOutputStream fileOutputStream = new FileOutputStream(sb.toString());
        byte[] bArr = new byte[1024];
        InputStream open = this.mContext.getAssets().open("bdaypoem.db");
        while (open.read(bArr) > 0) {
            fileOutputStream.write(bArr);
        }
        open.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public String getMessage(char c) {
        this.mDataBase = getReadableDatabase();
        SQLiteDatabase sQLiteDatabase = this.mDataBase;
        StringBuilder sb = new StringBuilder();
        sb.append("select message from messages where alphabet=\"");
        sb.append(c);
        sb.append("\" ORDER BY RANDOM() LIMIT 1");
        Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
        String string = rawQuery.moveToFirst() ? rawQuery.getString(0) : "";
        rawQuery.close();
        return string;
    }

    public String getLines() {
        this.mDataBase = getReadableDatabase();
        Cursor rawQuery = this.mDataBase.rawQuery("select line from lines ORDER BY RANDOM() LIMIT 1", null);
        String string = rawQuery.moveToFirst() ? rawQuery.getString(0) : "";
        rawQuery.close();
        return string;
    }
}
