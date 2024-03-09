package com.example.contentresolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String authorities = "com.example.contentapp";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        //load data button
        Uri CONTENT_URI = Uri.parse("content://" + authorities);
        Cursor c = getContentResolver().query(CONTENT_URI, null, null, null, null);
        if (c != null && c.moveToFirst()) { // Check if cursor is not null and move to first
            textView.setText("");
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                int age = c.getInt(2);
                textView.append(id + " " + name + " " + age + "\n");
            } while (c.moveToNext());
            c.close(); // Close the cursor after use
        } else {
            textView.setText("No data available.");
        }
    }
//save data button
    public void saveData(){
          String name="pavan";
          int age=20;
          Uri CONTENT_URI=Uri.parse("content://"+authorities);
        ContentValues values=new ContentValues();
        values.put("person_name",name);
        values.put("person_age",age);
        getContentResolver().insert(CONTENT_URI,values);
        Toast.makeText(this, "Data is saved", Toast.LENGTH_SHORT).show();
        //loadData();
    }
}
