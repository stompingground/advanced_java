package java8.lambdas;

public class EffectivelyFinal {
    public static void main(String[] args) {

        int answer = 10;

        new Thread(()-> System.out.println("anser is :"+answer)).start();

        //answer=11; cannot do this, varibles referenced in lambda must either be final or effectively final



    }

}
