package arpitha.pauseforcause;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;



public class task_view_holder extends RecyclerView.ViewHolder {
    View mView;

    TextView textTitle, textTime;
    CardView noteCard;
    //Button button;

    public task_view_holder(View itemView) {
        super(itemView);

        mView = itemView;

        textTitle = mView.findViewById(R.id.TaskName);
        textTime = mView.findViewById(R.id.TaskDesc);

         //button = itemView.findViewById(R.id.button);



    }

    public void setTaskTitle(String title) {
        textTitle.setText(title);
        //button.setText(title);
    }

    public void setTaskDesc(String time) {
        textTime.setText(time);
    }
}
