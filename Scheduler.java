import java.util.*;
import java.util.Comparator;
import java.util.PriorityQueue;
public class Scheduler {




    static PriorityQueue<Process> processQueue = new PriorityQueue<Process>(10, new
            Comparator<Process>() {
                public int compare(Process process1, Process process2) {
                    return (int)(process1.getArrivalTime()-process2.getArrivalTime());
                }
            });

    static GlobalTimer globalTimer = new GlobalTimer(0);
    public static void main(String[] args) {




        Scanner scanner = new Scanner(System.in);
        Scheduler scheduler = new Scheduler();

        System.out.print("Enter the number of queues: ");
        int numQueues = scanner.nextInt();
        System.out.print("Enter the number of numProcesses: ");

        int numProcesses = scanner.nextInt();
             for (int j = 0; j < numProcesses; j++) {
                System.out.println("Enter details for Process " + (j + 1));
                System.out.print("Name: ");
                String name = scanner.next();
                System.out.print("Arrival Time: ");
                int arrivalTime = scanner.nextInt();
                System.out.print("Burst Time: ");
                int burstTime = scanner.nextInt();
                 System.out.print("priority Time: ");
                 int priority = scanner.nextInt();
                Process process = new Process(name, arrivalTime, burstTime, globalTimer, priority);
                processQueue.add(process);
            }
        ArrayList <PriorityQueue> queues=new ArrayList<>();
             for(int i=0;i<=numQueues;i++){
                 queues.add(new PriorityQueue<Process>(10, new
                         Comparator<Process>() {
                             public int compare(Process process1, Process process2) {
                                 return (int)(process1.getArrivalTime()-process2.getArrivalTime());
                             }
                         }));
             }
             int counter=0;
             while(!processQueue.isEmpty() || !(counter<=0)){
                 if(!processQueue.isEmpty()){
                     Process p=processQueue.peek();
                     while(p.arrivalTime<= globalTimer.time){

                         int index=p.getPriority();
                         queues.get(index).add(p);
                         counter++;
                         processQueue.poll();
                         if(!processQueue.isEmpty()){
                             p=processQueue.peek();
                         }
                         else{
                             break;
                         }
                     }

                 }
                 boolean found = false;


                 for(int i=0;i<queues.size();i++){

                     if(!queues.get(i).isEmpty()){
                         Process p= (Process) queues.get(i).peek();
                         p.run();
                         //System.out.println("start_time"+ p.start_time);
                         if(p.duration==0){
                             int response_time= p.start_time- p.arrivalTime;
                             int end_time=(int)globalTimer.time;
                             int waiting_time=end_time- p.arrivalTime- p.initial_duration;
                             int turnaround_time=end_time-p.arrivalTime;
                             System.out.println("Arrival time:"+p.arrivalTime+"start_time"+ p.start_time+" "+"response_time"+ response_time+" "+ "end_time"+ end_time+" "+"waiting_time"+waiting_time+" "+"turnaround_time"+turnaround_time);
                             queues.get(i).poll();
                             counter--;
                         }
                         found=true;
                         break;
                     }

                 }
//
                 if(!found){
                     globalTimer.increaseTime();
                 }
                ;


             }




    }
}