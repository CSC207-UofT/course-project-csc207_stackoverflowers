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

    public void getIntoLevel() {currentStatus = levelStatus.DURING;}

    public void endLevel(){
        currentStatus = levelStatus.END;
    }

    public boolean levelStarted(){return currentStatus == levelStatus.START;}

    public String getOutputString(){
        return null;
    };

    public boolean levelEnded(){
        return currentStatus == levelStatus.END;
    }
}
