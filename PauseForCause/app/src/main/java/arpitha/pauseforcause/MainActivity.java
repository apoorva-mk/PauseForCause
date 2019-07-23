package arpitha.pauseforcause;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }


    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Write a message to the database
        database = FirebaseDatabase.getInstance().getReference();

    }


    public void buttonPress1(View v){
        Intent myIntent = new Intent(this, Organization.class);
        startActivity(myIntent);
        finish();
    }
    public void buttonPress2(View v){
        Intent myIntent = new Intent(this, Contributor.class);
        startActivity(myIntent);
        finish();
    }

    public void gotourl(View v){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://pauseforcause-forum.freeforums.net/"));
        String u="http://pauseforcause-forum.freeforums.net/";
        startActivity(intent);
    }

}
