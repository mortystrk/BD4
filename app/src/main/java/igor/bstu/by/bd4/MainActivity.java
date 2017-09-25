package igor.bstu.by.bd4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String fileName = "Base_Lab.txt";
    private File file;
    EditText surname, name;

    private boolean ExistBase(String fileName){
        boolean flag;
        File file = new File(super.getFilesDir(), fileName);
        if(flag = file.exists()){
            Log.d("Log_04", "Файл " + fileName + " существует");
        }
        else{
            Log.d("Log_04", "Файл " + fileName + " не найден");
        }
        return flag;
    }

    private AlertDialog CreateDialog(int message, int textOnPositiveButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton(textOnPositiveButton, new DialogInterface.OnClickListener() {
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

    private void WriteLine(String surname, String name){
        String str = surname + "; " + name + ";" + "\r\n";
        try{
            FileWriter fw = new FileWriter(file, true);
            fw.write(str);
            fw.flush();
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
            AlertDialog dialog = CreateDialog(R.string.dialog_content, R.string.understood);
            dialog.show();
            CreateFile();
        }

        surname = (EditText) findViewById(R.id.editTextSurname);
        name = (EditText) findViewById(R.id.editTextName);
    }

    public void onSetInfo(View v){
        if(!ExistBase(fileName)){
            AlertDialog dialog = CreateDialog(R.string.dialog_content, R.string.understood);
            dialog.show();
            CreateFile();
        }
        WriteLine(surname.getText().toString(), name.getText().toString());
        surname.setText("");
        name.setText("");
    }

    public void onGetInfo(View v){
        Intent intent = new Intent(this, FileContent.class);
        startActivity(intent);
    }

    public void onDeleteFile(View view) {
        file.delete();
        AlertDialog dialog = CreateDialog(R.string.delete_file, R.string.understood);
        dialog.show();

    }
}
