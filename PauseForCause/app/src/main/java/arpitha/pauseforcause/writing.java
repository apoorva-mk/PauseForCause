package arpitha.pauseforcause;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class writing extends AppCompatActivity {

    String title;
    EditText titlefield,mainnotefield;
    String taskdata;
    private String taskid;
    private DatabaseReference notesdatabase;
    private boolean isExist=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
        try {
            taskid = getIntent().getStringExtra("taskid");
            if (!taskid.trim().equals("")) {
                isExist = true;
            } else {
                isExist = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toolbar toolbar =(Toolbar)findViewById(R.id.my_writingnotes);
        toolbar.setTitle("Complete & Save");
        setSupportActionBar(toolbar);


        notesdatabase = FirebaseDatabase.getInstance().getReference().child("Tasks");
        titlefield = (EditText)findViewById(R.id.notestitle);
        placedata();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),choosetask.class);
        startActivity(i);
    }

    private void placedata() {

        if(isExist){
            notesdatabase.child(taskid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild("Title") && dataSnapshot.hasChild("Data")){
                        titlefield.setText(dataSnapshot.child("Data").getValue().toString());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tasks,menu);

        return true;
    }




}