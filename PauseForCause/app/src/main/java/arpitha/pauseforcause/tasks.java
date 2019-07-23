package arpitha.pauseforcause;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class tasks extends AppCompatActivity {

    private ListView listViewTasks;
    private DatabaseReference database;
    private ViewPager mViewPager;
    static RecyclerView R_view;

    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),Organization.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(tasks.this, NewTask.class);
                startActivity(myIntent);
            }
        });

        database = FirebaseDatabase.getInstance().getReference().child("Tasks");
        R_view= (RecyclerView)findViewById(R.id.refview);
        R_view.setHasFixedSize(true);
        R_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        onStart();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tasks,menu);
        return true;
    }

    public void onStart() {
        super.onStart();

        final Query query = database.orderByChild("Title");
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                FirebaseRecyclerAdapter<taskmodel,task_view_holder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<taskmodel, task_view_holder>(

                        taskmodel.class,
                        R.layout.task_item,
                        task_view_holder.class,
                        query

                ) {
                    @Override
                    protected void populateViewHolder(final task_view_holder viewHolder, taskmodel model, int position) {
                        final String taskid = getRef(position).getKey();
                        Log.d("users",taskid);
                        database.child(taskid).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("Title")) {
                                    String task = dataSnapshot.child("Title").getValue().toString();
                                    String desc = dataSnapshot.child("Data").getValue().toString();
                                    Log.d("users", desc);
                                    viewHolder.setTaskTitle(task);
                                    viewHolder.setTaskDesc(desc);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }
                };
                R_view.setAdapter(firebaseRecyclerAdapter);
            }
        });

        try {
            th.start();
        }catch (Exception e){
            System.out.print(e);
        }
    }

}
