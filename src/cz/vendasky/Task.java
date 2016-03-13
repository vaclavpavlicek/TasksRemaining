package cz.vendasky;

public class Task {

    private int id;

    public Task(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object task) {
        return this.id == ((Task) task).getId();
    }

}
