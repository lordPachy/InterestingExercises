//import java.util.ArrayList;

public class EratoStation extends Thread{

    private int p;      //the prime number
    private EratoStation next;      //next thread in the list
    //private ArrayList<Integer> waitList = new ArrayList<Integer>();
    private volatile int n;      //the number we have to divide by

    EratoStation(int n){
        this.p = n;
        System.out.println(p + " is now a prime thread");
    }

    public synchronized void pushBuffer(int pushed) throws InterruptedException{
        //System.out.println("I'm " + p + " and I'm being pushed number " + n);
        this.n = pushed;
        notify();
    }

    public synchronized void run(){
        while(!isInterrupted()){
            try{   
                System.out.println("waiting...");
                wait();
                System.out.println("finally, something to process!");
                if(n % p != 0){
                    //System.out.println("HI! I'm " + p + ", and I'm handling " + n + "!");
                    if (next != null){
                        try{
                            next.pushBuffer(n);
                        } catch(InterruptedException e){
                            System.out.println("What happened?");
                        }
                    } else{
                        next = new EratoStation(n);
                        next.start();
                    }
                }

            } catch (InterruptedException e) {
                System.out.println(p + " has finished working");
                if (next != null){
                    next.interrupt();
                }
            }
        }
    }
}
