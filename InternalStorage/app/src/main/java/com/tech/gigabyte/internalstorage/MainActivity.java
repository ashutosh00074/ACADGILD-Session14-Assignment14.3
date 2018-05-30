package com.tech.gigabyte.internalstorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText data;
    Button save, check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = (EditText) findViewById(R.id.edt);
        save = (Button) findViewById(R.id.btn_save);
        check = (Button) findViewById(R.id.btn_load);
    }

    public void save(View view) {
        try {

            //A file output stream is an output stream for writing data to a File or to a FileDescriptor
            FileOutputStream fileOutputStream = openFileOutput("InternalStorage.txt", MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);

            //Saving String into a .txt File
            writer.write(data.getText().toString());
            writer.close();
            Toast.makeText(getApplicationContext(), "File Saved Successfully", Toast.LENGTH_LONG).show();

            //If any exceptions produced by failed or interrupted I/O operations
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Getting Saved File Data or Checking wather file exists or not
    public void check(View view) {
        File folder = getFilesDir();
        String data = "";
        String buffer = "";
        File file = new File(folder + "/" + "InternalStorage.txt");
        try {

            //An input bytes from a file in a file system
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((data = br.readLine()) != null) {
                buffer += data + "\n";
            }
            Toast.makeText(getApplicationContext(), "File " + file + " exists with contents : " + buffer, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "File not found", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        //If any exceptions produced by failed or interrupted I/O operations
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}