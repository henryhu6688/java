package com.example.javaweb.entity;

public class User {

    private String username;

    private String password;

    private Integer phone;
    private int key1;

    private int id;
    private String name;
    private double salary;
    private String address;


    private int pro_num;
    private String pro_name;
    private String pro_kind;
    private String pro_factory;
    private float pro_price;
    private int pro_amount;
    private float pro_money;
/*
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return phone;
    }

    public void setAge(Integer phone) {
        this.phone = phone;
    }

    public int getKey() {
        return key1;
    }



    public int getPro_num() {
        return pro_num;
    }

    public void setPro_num(int pro_num) {
        this.pro_num = pro_num;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_kind() {
        return pro_kind;
    }

    public void setPro_kind(String pro_kind) {
        this.pro_kind = pro_kind;
    }

    public String getPro_factory() {
        return pro_factory;
    }

    public void setPro_factory(String pro_factory) {
        this.pro_factory = pro_factory;
    }

    public float getPro_price() {
        return pro_price;
    }

    public void setPro_price(float pro_price) {
        this.pro_price = pro_price;
    }

    public int getPro_amount() {
        return pro_amount;
    }

    public void setPro_amount(int pro_amount) {
        this.pro_amount = pro_amount;
    }

    public float getPro_money() {
        return pro_money;
    }

    public void setPro_money(float pro_money) {
        this.pro_money = pro_money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                '}';
    }

}