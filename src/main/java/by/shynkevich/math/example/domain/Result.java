package by.shynkevich.math.example.domain;

public class Result {

    private int countFailed;
    private int countResolved;
    private int countTotal;
    private boolean done;

    public Result(int countFailed, int countResolved, int countTotal, boolean done) {
        this.countFailed = countFailed;
        this.countResolved = countResolved;
        this.countTotal = countTotal;
        this.done = done;
    }

    public int getCountFailed() {
        return countFailed;
    }

    public void setCountFailed(int countFailed) {
        this.countFailed = countFailed;
    }

    public int getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getCountResolved() {
        return countResolved;
    }

    public void setCountResolved(int countResolved) {
        this.countResolved = countResolved;
    }
}
