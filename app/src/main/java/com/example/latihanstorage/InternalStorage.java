package com.example.latihanstorage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalStorage extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME ="namafile.txt";
    Button buatFile, ubahFile, bacaFile, deleteFile;
    TextView textBaca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        buatFile = findViewById(R.id.btn_create);
        ubahFile = findViewById(R.id.btn_edit);
        bacaFile = findViewById(R.id.btn_read);
        deleteFile = findViewById(R.id.btn_delete);
        textBaca = findViewById(R.id.textBaca);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        deleteFile.setOnClickListener(this);
    }

    void buatFile(){
        String isiFile = "Coba Isi Data File Text";
        File file = new File(getFilesDir(),FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void ubahFile(){
        String ubah = "Update Isi Data File Text";
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void bacaFile() {
        File sdCard= getFilesDir();
        File file=new File(sdCard,FILENAME);

        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line!= null){
                    text.append(line);
                    line=br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
            textBaca.setText(text.toString());
        }else{
        textBaca.setText("File Not Found !!");
        }
    }

    void hapusFile(){
        File file = new File(getFilesDir(),FILENAME);
        if (file.exists()){
            file.delete();
            Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        runningProgram(v.getId());
    }

    private void runningProgram(int id) {
        switch(id){
            case R.id.btn_create:
                buatFile();
                break;
            case R.id.btn_edit:
                ubahFile();
                break;
            case R.id.btn_read:
                bacaFile();
                break;
            case R.id.btn_delete:
                hapusFile();
                break;
        }
    }
}