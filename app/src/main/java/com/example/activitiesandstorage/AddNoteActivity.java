package com.example.activitiesandstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {

    Spinner spselectionforDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        spselectionforDelete = findViewById(R.id.spselectionforDelete);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ArrayList<String> notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));

        ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, notesList);
        spselectionforDelete.setAdapter(listAdapter);
    }

    public void onAddNoteClick(View view) {
        EditText note = findViewById(R.id.text);

        //https://stackoverflow.com/questions/14034803/misbehavior-when-trying-to-store-a-string-set-using-sharedpreferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEdit = sp.edit();

        Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());
        Set<String> newSet = new HashSet<String>();

        newSet.add(note.getText().toString());
        newSet.addAll(oldSet);
        spEdit.putStringSet("notes",newSet);
        spEdit.apply();


        finish();
    }

}
