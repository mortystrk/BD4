package igor.bstu.by.bd4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileContent extends AppCompatActivity {

    private final String fileName = "Base_Lab.txt";
    TextView info;
    File file;

    private void ReadText(){
        FileInputStream fin = null;
        try{
            fin = openFileInput(fileName);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            info.setText(text);
        } catch (FileNotFoundException e) {
            Log.d("Log_04", "Файл " + fileName + " не найден");
        } catch (IOException e) {
            Log.d("Log_04", "Чтение не удалось");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_content);

        file = new File(super.getFilesDir(), fileName);

        info = (TextView) findViewById(R.id.fileViewInfo);
        ReadText();
    }

    public void onReturn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
