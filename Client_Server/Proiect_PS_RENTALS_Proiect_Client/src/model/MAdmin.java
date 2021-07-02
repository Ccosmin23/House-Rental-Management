package model;

import contact.SEmployee;

public class MAdmin extends Subject {
    private SEmployee sEmployee;

    public MAdmin(SEmployee sEmployee) {
        this.sEmployee = sEmployee;
    }

    public SEmployee getsEmployee() {
        return sEmployee;
    }

    public void setsEmployee(SEmployee sEmployee) {
        this.sEmployee = sEmployee;
    }
}
