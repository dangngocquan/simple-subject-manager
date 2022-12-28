package src.objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Account {
    // Properties
    private String name;
    private String username;
    private String password;
    private String timeAccountCreated;
    private List<Plan> plans;

    // Constructor
    public Account(String name, String username, String password, String timeAccountCreated) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.timeAccountCreated = timeAccountCreated;
        this.plans = new LinkedList<Plan>();
    }

    public Account(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        setTimeAccountCreated();
        this.plans = new LinkedList<Plan>();
    }

    // Getter
    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Plan> getPlans() {
        return this.plans;
    }

    public String getTimeAccountCreated() {
        return this.timeAccountCreated;
    }

    // Setter
    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    public void addPlan(Plan plan) {
        this.plans.add(plan);
    }

    public void setTimeAccountCreated() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        this.timeAccountCreated = format.format(new Date());
    }
}
