package com.example.activitiesandstorage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

    Spinner SpinnerDeleted;
    private ArrayList<String> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        SpinnerDeleted = findViewById(R.id.SpinnerDeleted);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, notesList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerDeleted.setAdapter(arrayAdapter);

    }

    public void onDeleteNoteClick(View view) {

        String selected = SpinnerDeleted.getSelectedItem().toString();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();

        Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());
        Set<String> newSet = new HashSet<String>();

        oldSet.remove(selected);
        newSet.addAll(oldSet);

        spEd.putStringSet("notes",newSet);
        spEd.apply();

        finish();
    }
}

