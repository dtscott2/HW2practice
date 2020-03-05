package sample;

import java.util.UUID;

public class Student {

    public String name;
    public UUID id;
    public int age;
    public String major;
    public double GPA;


    @Override
    public String toString(){
        return (this.name + " " + this.id + " " + this.age + " " + this.major + " " + this.GPA);
    }



}

