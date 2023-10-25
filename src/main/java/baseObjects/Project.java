package baseObjects;

import org.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
@Table(name="projects")
public class Project {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    ArrayList<Developer> projectDevelopers;
    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    ArrayList<Task> tasks;

    public Project(long id, String name, ArrayList<Developer> projectDevelopers) {
        this.id = id;
        this.name = name;
        this.projectDevelopers = projectDevelopers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Developer> getProjectDevelopers() {
        return projectDevelopers;
    }

    public void setProjectDevelopers(ArrayList<Developer> projectDevelopers) {
        this.projectDevelopers = projectDevelopers;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public JSONObject ToJSONObject()
    {
        JSONObject obj=new JSONObject();
        obj.put("id",id);
        obj.put("name",name);

        ArrayList<JSONObject> developers=new ArrayList<JSONObject>();
        if(projectDevelopers!=null)
           for(Developer developer:projectDevelopers)
               developers.add(developer.ToJSONObject(false));

        obj.put("developers",developers);
        ArrayList<JSONObject> tasksJSON=new ArrayList<JSONObject>();
        if(tasks!=null)
            for(Task task:tasks)
                tasksJSON.add(task.ToJSONObject());
        obj.put("tasks",tasks);
        return obj;
    }
}
