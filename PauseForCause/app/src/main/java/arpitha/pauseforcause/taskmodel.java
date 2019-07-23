package arpitha.pauseforcause;

public class taskmodel {
    public String tasktitle;
    public String taskdesc;

    public taskmodel(){

    }

    public taskmodel(String notetitle,String notetime){
        this.tasktitle=notetitle;
        this.taskdesc=notetime;
    }

    public String getTasktitle(){
        return tasktitle;
    }
    public String getTaskdesc(){
        return taskdesc;
    }
    public void setTasktitle(String title){
        this.tasktitle=title;
    }

    public void setTaskdesc(String time){
        this.taskdesc=time;
    }
}
