package com.geekbrains.lesson7;

//        1. Перенести приведенный ниже код в новый проект, где мы организуем гонки.
//        Все участники должны стартовать одновременно, несмотря на разное время подготовки. В тоннель не
//        может одновременно заехать больше половины участников (условность).
//        Попробуйте все это синхронизировать.
//        Когда все завершат гонку, нужно выдать объявление об окончании.
//        Можно корректировать классы (в том числе конструктор машин) и добавлять объекты классов из
//        пакета util.concurrent.
//        Обязательно необходимо объявить победителя гонки, он должен быть только один, и это участник
//        первым закончивший последний этап.

import java.util.concurrent.CyclicBarrier;

public class MainApp {
    public static final int CARS_COUNT = 4;
    public static boolean isWinner = false;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT + 1);
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cyclicBarrier);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            cyclicBarrier.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            cyclicBarrier.await();
            cyclicBarrier.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (Exception e) {
            e.getMessage();
        }

    }
}