package ua.edu.ukma.Zhytnetsky;

import lombok.SneakyThrows;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Data d = new Data();

        Worker w1 = new Worker(1, d);
        Worker w2 = new Worker(2, d);
        Worker w3 = new Worker(3, d);

        w3.join();
        System.out.println("End of main...");
    }

}