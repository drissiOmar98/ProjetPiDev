package entity;

public class Employee {
    private String id;
    private String sex;
    private String name;
    private String phone;
    private String email;
    private String adress;
    private float salary;
    private String province;
    private String RegDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegDate() {
        return RegDate;
    }

    public void setRegDate(String regDate) {
        RegDate = regDate;
    }


    public Employee(String id, String sex, String name, String phone, String adress, String email, float salary, String province, String regDate) {
        this.id = id;
        this.sex = sex;
        this.name = name;
        this.phone = phone;
        this.adress = adress;
        this.email = email;
        this.salary = salary;
        this.province = province;
        RegDate = regDate;
    }


}
