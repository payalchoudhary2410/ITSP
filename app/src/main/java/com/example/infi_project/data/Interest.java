package com.example.infi_project.data;

public class Interest {
    String random;
    String userPhone;// to be used later
                    // when we have to add further details

    public Interest() {
    }

    public Interest(String random, String userPhone) {
        this.random = random;
        this.userPhone = userPhone;
    }


    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
