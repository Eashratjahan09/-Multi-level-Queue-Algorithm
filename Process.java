public class Process {
    String name;
    int arrivalTime;
    int duration;
    GlobalTimer globalTimer;
    int priority;
    int start_time;
    int initial_duration;



    public Process(String name, int arrivalTime, int duration, GlobalTimer globalTimer, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.globalTimer = globalTimer;
        this.priority=priority;
        this.start_time=(int)1e9;
        this.initial_duration=duration;
    }
    public String getName() {
        return name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getduration() {
        return duration;
    }
    public int getPriority() {
        return priority;
    }
    public void run(){
        start_time=Math.min(start_time, globalTimer.time);
        System.out.println(name+"process at" +"  "+globalTimer.time);
        duration--;
        globalTimer.increaseTime();
    }




    public boolean isFinished() {
        return duration <= 0;
    }


}