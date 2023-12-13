package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum { // Объявление класса ThreadGroupSum, который наследуется от абстрактного класса GroupSum
    public ThreadGroupSum(int[][] numberGroups) { // Конструктор класса, который принимает двумерный массив чисел
        super(numberGroups); // Вызов конструктора родительского класса с переданным массивом
    }

    @Override
    public int computeSum() { // Переопределение абстрактного метода computeSum() из родительского класса
        OneGroupSum[] oneGroupSums = new OneGroupSum[numberGroups.length]; // Создание массива объектов OneGroupSum, каждый из которых будет вычислять сумму в одной группе чисел
        Thread[] threads = new Thread[numberGroups.length]; // Создание массива потоков, каждый из которых будет запускать один объект OneGroupSum

        for (int i = 0; i < numberGroups.length; i++) { // Цикл по всем группам чисел
            oneGroupSums[i] = new OneGroupSum(numberGroups[i], 0); // Создание нового объекта OneGroupSum для текущей группы чисел
            threads[i] = new Thread(oneGroupSums[i]); // Создание нового потока, который будет запускать объект OneGroupSum
            threads[i].start(); // Запуск потока
        }

        int totalSum = 0; // Инициализация переменной для хранения общей суммы
        for (int i = 0; i < numberGroups.length; i++) { // Цикл по всем группам чисел
            try {
                threads[i].join(); // Ожидание завершения потока
                totalSum += oneGroupSums[i].getSum(); // Добавление суммы текущей группы к общей сумме
            } catch (InterruptedException e) { // Обработка исключения, которое может быть вызвано методом join()
                e.printStackTrace(); // Вывод информации об исключении
            }
        }

        return totalSum; // Возвращение общей суммы
    }
}
