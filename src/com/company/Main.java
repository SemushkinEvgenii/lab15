package com.company;

import java.util.ArrayList;
import java.util.List;

class Scratch {

    public static void main(String[] args) {

        Computer pub1 = new Computer(); // паблик
        Dashboard p1 = new Dashboard(pub1); // подписчики
        pub1.changeData(33);

        Computer pub2 = new Computer();
        Dashboard p2 = new Dashboard(pub2);
        pub1.changeData(22);

        Computer pub3 = new Computer();
        Dashboard p3 = new Dashboard(pub3);
        pub3.changeData(44);

    }
}
interface Notifier{
    public void addObserver(Observer obs);
}

class Computer implements Notifier{ // ^
    private List servers;
    private int sub;

    public Computer(){servers = new ArrayList(); }

    public void addObserver(Observer obs) {
        servers.add(obs);
    }

    public void notifyObserver() { 
        // опр. сколько запросов на изменение подписчиков
        for (int i = 0; i < servers.size(); i++){
            Observer obs = (Observer)servers.get(i);
            obs.set(sub); // изменение кол-ва подписчиков в obs
        }
    }

    public void changeData(int sub){
        this.sub = sub; // вызываем подписчиков
        notifyObserver();
    }

}

interface Observer{ // подписчики
    public void set(int sub);
}

class Dashboard implements Observer{
    private Notifier notifier;
    private int sub;

    public Dashboard(Notifier notifier){
        this.notifier = notifier;
        notifier.addObserver(this);
    }

    public void set(int sub) {
        this.sub = sub;
        show();
    }

    public void show(){System.out.println("количество подписиков " + sub); }
}
