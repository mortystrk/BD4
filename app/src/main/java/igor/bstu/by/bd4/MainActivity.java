package igor.bstu.by.bd4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MainActivity extends AppCompatActivity {

    private final String fileName = "Base_Lab.txt";
    private File file;
    private BufferedWriter bufWritter;
    EditText surname, name;
    FileOutputStream outputStream;

    public String getFileName() {
        return fileName;
    }

    private boolean ExistBase(String fileName){
        boolean flag = false;
        File file = new File(super.getFilesDir(), fileName);
        if(flag = file.exists()){
            Log.d("Log_04", "Файл " + fileName + " существует");
        }
        else{
            Log.d("Log_04", "Файл " + fileName + " не найден");
        }
        return flag;
    }

    private AlertDialog CreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_content)
                .setPositiveButton(R.string.understood, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Log_04", "Создание файла Base_lab.txt");
                    }
                });
        return builder.create();
    }

    private void CreateFile(){
        try{
            file.createNewFile();
            Log.d("Log_04", "Файл " + fileName + " создан");
        } catch (IOException e) {
            Log.d("Log_04", "Файл " + fileName + " не создан");
        }
    }

    private void CreateWritter(){
        try{
            FileWriter fw = new FileWriter(file, true);
            bufWritter = new BufferedWriter(fw);
            WriteLine(surname.getText().toString(), name.getText().toString());
        } catch (IOException e) {
            Log.d("Log_04", "Файл " + fileName + " не открыт " + e.getMessage());
        }
    }

    private void WriteLine(String surname, String name){
        String str = surname + "; " + name + ";" + "\r\n";
        try{
            //outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
           // outputStream.write(str.getBytes());
           // outputStream.close();
            RandomAccessFile out = new RandomAccessFile(file, "rw");
            out.seek(file.length());
            out.writeChars(str + "\r\n");
            out.close();
        } catch (IOException e) {
            Log.d("Log_04", "Данные не записаны");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        file = new File(super.getFilesDir(), fileName);

        if(!ExistBase(fileName)){
            AlertDialog dialog = CreateDialog();
            dialog.show();
            CreateFile();
        }

        surname = (EditText) findViewById(R.id.editTextSurname);
        name = (EditText) findViewById(R.id.editTextName);
    }

    public void onSetInfo(View v){
        CreateWritter();
        surname.setText("");
        name.setText("");
    }

    public void onGetInfo(View v){
        Intent intent = new Intent(this, FileContent.class);
        startActivity(intent);
    }
}
