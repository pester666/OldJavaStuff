package lib;

import java.io.Serializable;

public class Injury implements Serializable{

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
