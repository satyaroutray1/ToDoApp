package com.example.android.todoapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.todoapp.data.Contract;
import com.example.android.todoapp.data.DbHelper;
import com.example.android.todolistapp.R;

public class EditorActivity extends AppCompatActivity{

    private static final String TAG = "EditorActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                finish();
                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //----------------save new task to database---------------------
    public void save(){
//        DbHelper mDbHelper = new DbHelper(this);
  //      SQLiteDatabase sqLiteDatabase = mDbHelper.getWritableDatabase();

        EditText item = (EditText)findViewById(R.id.edit_task);
        String task = item.getText().toString().trim();

        if(!task.isEmpty()) {
            ContentValues cv = new ContentValues();
            cv.put(Contract.Entry.COLUMN_TASK, task);

            //long rID = sqLiteDatabase.insert(Contract.Entry.TABLE_NAME, null, cv);
            Uri newUri = getContentResolver().insert(Contract.Entry.CONTENT_URI, cv);
            long rID = Long.valueOf(newUri.getLastPathSegment());
            Log.d(TAG, task + " is inserted at " + rID );
            Toast.makeText(getApplicationContext(),"Task is saved" , Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Enter task to add ", Toast.LENGTH_SHORT).show();
        }
    }
}