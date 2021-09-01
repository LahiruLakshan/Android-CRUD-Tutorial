package com.example.crud_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewRecords extends AppCompatActivity {


    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE, null);

            lst1 = findViewById(R.id.lst1);
            final Cursor c = db.rawQuery("select * from records", null);

            int id = c.getColumnIndex("id");
            int name = c.getColumnIndex("name");
            int course = c.getColumnIndex("course");
            int fee = c.getColumnIndex("fee");
            titles.clear();

            arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, titles);
            lst1.setAdapter(arrayAdapter);

            final ArrayList<Student> studentArrayList = new ArrayList<Student>();

            if (c.moveToFirst()){
                do {

                    Student studentDetail = new Student();
                    studentDetail.id = c.getString(id);
                    studentDetail.name = c.getString(name);
                    studentDetail.course = c.getString(course);
                    studentDetail.fee = c.getString(fee);

                    studentArrayList.add(studentDetail);

                    titles.add("\t" + c.getString(id) + "\t" + c.getString(name) + "\t" + c.getString(course) + "\t" + c.getString(fee));


                }while (c.moveToNext());
                arrayAdapter.notifyDataSetChanged();
                lst1.invalidateViews();
            }

    }
}