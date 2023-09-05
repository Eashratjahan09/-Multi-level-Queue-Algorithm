public class GlobalTimer {

    public int time;

    public GlobalTimer(int initialTime){

        this.time=initialTime;
    }
    public int increaseTime(){
        return time++;
    }

}