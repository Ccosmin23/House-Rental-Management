package model;

import contact.SEmployee;

public class MSignIn extends Subject {
    private SEmployee sEmployee;

    public MSignIn(SEmployee sEmployee) {
        this.sEmployee = sEmployee;
    }

    public SEmployee getsEmployee() {
        return sEmployee;
    }

    public void setsEmployee(SEmployee sEmployee) {
        this.sEmployee = sEmployee;
    }
}
