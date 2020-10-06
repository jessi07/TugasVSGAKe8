package com.example.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ExternalStorage extends AppCompatActivity implements View.OnClickListener {

    public static final String FILE ="namafile.txt";
    Button buat, ubah, baca, delete;
    TextView textBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        buat = findViewById(R.id.btn_create);
        ubah = findViewById(R.id.btn_edit);
        baca = findViewById(R.id.btn_read);
        delete = findViewById(R.id.btn_delete);
        textBaca = findViewById(R.id.textBaca);

        buat.setOnClickListener(this);
        ubah.setOnClickListener(this);
        baca.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    void buat(){
        String isiFile = "Coba Isi Data File Text";
        File file = new File(getExternalFilesDir(null), FILE);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    void ubah() {
        String edit = "Update Isi Data File Text";
        File file = new File(getExternalFilesDir(null), FILE);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(edit.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void baca() {
        File sdcard = getExternalFilesDir("");
        File file = new File(sdcard, FILE);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }
            textBaca.setText(text.toString());
        }
    }

    void hapus() {
        File file = new File(getExternalFilesDir(null),FILE);
        if (file.exists()) {
            file.delete();
            Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        runProg(v.getId());
    }

    private void runProg(int id) {
        switch (id) {
            case R.id.btn_create:
                buat();
                break;
            case R.id.btn_edit:
                ubah();
                break;
            case R.id.btn_read:
                baca();
                break;
            case R.id.btn_delete:
                hapus();
                break;
        }
    }
}