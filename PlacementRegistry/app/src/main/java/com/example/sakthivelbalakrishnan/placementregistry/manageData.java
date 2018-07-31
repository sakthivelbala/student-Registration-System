package com.example.sakthivelbalakrishnan.placementregistry;

/**
 * Created by G on 02-04-2018.
 */

public class manageData {
    public static String fname,lname,intrest,acheivments;
    public static String age;
    public manageData(){

    }
    public manageData(String fname, String lname, String intrest, String acheivments, String age) {
        this.fname = fname;
        this.lname = lname;
        this.intrest = intrest;
        this.acheivments = acheivments;
        this.age = age;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getIntrest() {
        return intrest;
    }

    public String getAcheivments() {
        return acheivments;
    }

    public String getAge() {
        return age;
    }
}
