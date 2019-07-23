package arpitha.pauseforcause;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PickTask extends AppCompatActivity {

    private DatabaseReference database;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),choosetask.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_task);
        database = FirebaseDatabase.getInstance().getReference().child("Tasks");
    }




    public void deletetask(View v){
        TextInputEditText txt;
        txt=findViewById(R.id.choice);
        String key=txt.getText().toString();
        //final DatabaseReference newnote = database.push();

        //final Map notestructure = new HashMap();
        //notestructure.put("Title", key);
        //notestructure.put("Data", value);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("Tasks").orderByChild("Title").equalTo(key);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });

        Intent i = new Intent(getApplicationContext(), choosetask.class);
        startActivity(i);
    }
}
