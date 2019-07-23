package arpitha.pauseforcause;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NewTask extends AppCompatActivity {

    private DatabaseReference database;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),tasks.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        database = FirebaseDatabase.getInstance().getReference().child("Tasks");
    }




    public void buttonPressAdd(View v){
        TextInputEditText txt;
        txt=findViewById(R.id.TaskName);
        String key=txt.getText().toString();
        txt=findViewById(R.id.Description);
        String value= txt.getText().toString();
//        System.out.println(key+" a "+value);
        final DatabaseReference newnote = database.push();

        final Map notestructure = new HashMap();
        notestructure.put("Title", key);
        notestructure.put("Data", value);

        Thread mainthread = new Thread(new Runnable() {
            @Override
            public void run() {

                newnote.setValue(notestructure).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Task has been saved", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), tasks.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error in saving notes", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mainthread.start();

//
//        Map map = new HashMap();
//        map.put(key,value);
//        database.child("Tasks").updateChildren(map);
//        Intent i = new Intent(getApplicationContext(),tasks.class);
//        startActivity(i);
//        finish();
//        Log.d("Test", key);
    }
}
