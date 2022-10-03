package entity;

public class Manager {
    public int user_id;
    public String user_name;
    public String password;
    public String department;

    public Manager(int user_id, String user_name, String password, String department) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.department = department;
    }

    public Manager() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
