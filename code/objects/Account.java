package code.objects;

import java.util.LinkedList;
import java.util.List;

public class Account {
    // Properties
    private String name;
    private List<Plan> plans;

    // Constructor
    public Account(String name) {
        this.name = name;
        this.plans = new LinkedList<Plan>();
    }

    // Getter
    public String getName() {
        return this.name;
    }
}
