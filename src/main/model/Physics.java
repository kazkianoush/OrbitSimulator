package model;


public class Physics {

    private int gravity;

    public void physics(int gravity) {
        this.gravity = gravity;
    }

    public int[] calculateV() {
        //calculates the current velocity
        int[] arr = {};
        return arr;
    }

    public int[] calculateAcc() {
        //calculates the current acceleration
        int[] arr = {};
        return arr;
    }

    public int calculateCurrentOrbit() {
        //calculates the next move in the orbit trajectory
        return 0;

    }
}
