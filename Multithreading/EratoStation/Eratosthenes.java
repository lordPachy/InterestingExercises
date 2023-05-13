/**
 * Created by pietrocenciarelli on 06/05/23.
 */
public class Eratosthenes {

    public static void main(String[] args) {
        EratoStation chain = new EratoStation(2);
        chain.start();
        try {
            for (int number = 3; number < 100; number++)
                chain.pushBuffer(number);
        }
        catch (InterruptedException e) {
            System.out.println("Shouldn't happen!");
        }
        chain.interrupt();
    }
}
