package com.jni.cache;

/**
 * A simple model object
 *
 * @author Yasitha Thilakaratne
 */
public class Model {

    private long id;
    private String name;
    private boolean active;

    public Model(long id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }


    @Override
    public String toString() {
        return "Model{" + "id=" + id + ", name='" + name + '\'' + ", active=" + active + '}';
    }
}
