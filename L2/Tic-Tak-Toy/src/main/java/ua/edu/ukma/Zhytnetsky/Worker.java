package ua.edu.ukma.Zhytnetsky;

import lombok.SneakyThrows;

public class Worker extends Thread {

    private int id;
    private Data data;

    public Worker(int id, Data d) {
        this.id = id;
        this.data = d;
        this.start();
    }

    @Override
    @SneakyThrows
    public void run() {
        super.run();

        for (int i = 0; i < 5; i++) {
            synchronized (this.data) {
                while (this.data.getState() != this.id)
                    this.data.wait();

                switch (this.id) {
                    case 1 -> this.data.Tic();
                    case 2 -> this.data.Tak();
                    case 3 -> this.data.Toy();
                }
                this.data.notifyAll();
            }
        }
    }

}
