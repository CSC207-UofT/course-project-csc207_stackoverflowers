package ControllersPresenters;

public abstract class Level {
    /*
    An abstract class that has a status of starting, during and ending.
    should be able to fetch that status when asked.
     */
    private levelStatus currentStatus;
    enum levelStatus{START, DURING, END}

    public Level(){
        currentStatus = levelStatus.START;
    }
    public levelStatus getCurrentStatus() {
        return currentStatus;
    }
    public boolean levelEnded(){
        return currentStatus == levelStatus.END;

    }
}
