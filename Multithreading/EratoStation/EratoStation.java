import java.util.ArrayList;

public class EratoStation extends Thread{

    private int p;      //the prime number
    private EratoStation next;      //next thread in the list
    private ArrayList<Integer> waitList = new ArrayList<Integer>();

    EratoStation(int n){
        this.p = n;
    }

    public void pushBuffer(int pushed) throws InterruptedException{
        if (!isInterrupted()){
            waitList.add(pushed);
        }else{
            throw new InterruptedException();
        }
    }

    public void run(){
        while(!(isInterrupted() & waitList.isEmpty())){
            if (!waitList.isEmpty()){
                int n = waitList.get(0);
                if(n % p != 0){
                    if (next != null){
                        try{
                            next.pushBuffer(n);
                        } catch(InterruptedException e){}
                    } else{
                        next = new EratoStation(n);
                        next.start();
                    }
                }

                waitList.remove(0);
            }
        }
        if (next != null){
            next.interrupt();
        }
    }
}
