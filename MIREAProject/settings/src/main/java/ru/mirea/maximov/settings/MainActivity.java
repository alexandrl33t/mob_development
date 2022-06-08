package ru.mirea.maximov.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Postman {

    Fragment fragment1;
    FragmentManager fragmentManager;
    private SharedPreferences preferences;
    ArrayList<State> states = new ArrayList<State>();
    StateAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1 = new Settings();
        preferences = getPreferences(MODE_PRIVATE);
        setInitialData();
        LinearLayoutManager mRecentLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mRecentLayoutManager);
        // создаем адаптер
        adapter = new StateAdapter(this, states);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }
    private void setInitialData(){

        String content = getTextFromFile();
        if(content!=null) {
            String[] keys = content.split(" ");
            for (int i = 0; i < keys.length; i++) {
                states.add(new State(keys[i], preferences.getString(keys[i], "unknown")));
            }
        }
    }
    public void AddSetting(View view)
    {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                fragment1).commit();
    }

    @Override
    public void fragmentMail(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        // Сохранение значения по ключу key
        editor.putString(key, value);
        editor.apply();
        states.add(new State(key,value));
        adapter.notifyItemChanged(0);
        setTextToFile(key);
    }

    public void setTextToFile(String key) {
        String content = getTextFromFile()+key+" ";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("keys.txt", MODE_PRIVATE);
            fos.write(content.getBytes());
            //Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = openFileInput("keys.txt");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            return text;
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }
}