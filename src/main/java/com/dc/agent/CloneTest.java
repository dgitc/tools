package com.dc.agent;

import java.util.Calendar;
import java.util.Date;

public class CloneTest {

    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR,2023);
        Date adate = instance.getTime();
        instance.set(Calendar.YEAR,2024);
        Date aclonedate = instance.getTime();
        A a = new A(1,"张三", adate);
        B b = new B(2,"李四",adate);
        a.setB(b);
        A aclone = a.clone();
        System.out.println(a.getB());
        System.out.println(aclone.getB());
        a.setB(b);
    }

}

class A implements Cloneable{
    private int id;
    private String name;
    private Date birthday;
    private B b;

    public A(int id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    @Override
    public A clone() {
        try {
            A clone = (A) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", b=" + b +
                '}';
    }
}

class B{
    private int id;
    private String name;
    private Date birthday;

    public B(int id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}

