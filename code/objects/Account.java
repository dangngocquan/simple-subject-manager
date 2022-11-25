package code.objects;

import java.util.LinkedList;
import java.util.List;

public class Account {
    // Properties
    private String name;
    private String username;
    private String password;
    private List<Plan> plans;

    // Constructor
    public Account(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
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

    // Setter
    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    public void addPlan(Plan plan) {
        this.plans.add(plan);
    }
}
