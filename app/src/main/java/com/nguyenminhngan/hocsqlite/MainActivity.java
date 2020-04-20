package com.nguyenminhngan.hocsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    String DATABASE_NAME ="CSDL_DanhBa.db";
    String DB_PATH_SUFFIX ="/database/";
    SQLiteDatabase database=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processCopy();
    }
    private  void  processCopy(){
        File dbFile = getDatabasePath("CSDL_DanhBa.db");
        if (!dbFile.exists()){
            try {
                CopyDataBaseFromAssets();
                Toast.makeText(this, "Copying success from Asset folder",
                        Toast.LENGTH_LONG).show();
            }
            catch (Exception ex){
                Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDataBaseFromAssets() {
        try {
            InputStream myInput;
            myInput = getAssets().open("CSDL_DanhBa.db");
            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir, DB_PATH_SUFFIX);
            if ( (!f.exists())){
                f.mkdir();
                OutputStream myOutput= new FileOutputStream(outFileName);
                byte[] buffer = new  byte[1024];
                int length;
                while ((length = myInput.read(buffer))>0){
                    myOutput.write(buffer,0,length);
                }
                myOutput.flush();
                myOutput.close();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private String getDatabasePath(){
        return getApplicationInfo().dataDir+DB_PATH_SUFFIX+DATABASE_NAME;
    }
}
