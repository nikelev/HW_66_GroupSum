package ait.numbers.model;
import ait.numbers.task.OneGroupSum;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorGroupsSum extends GroupSum{
    public ExecutorGroupsSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        ExecutorService executor = Executors.newWorkStealingPool(); // Создание пула потоков
        OneGroupSum[] oneGroupSums = new OneGroupSum[numberGroups.length]; // Создание массива объектов OneGroupSum

        for (int i = 0; i < numberGroups.length; i++) { // Цикл по всем группам чисел
            oneGroupSums[i] = new OneGroupSum(numberGroups[i], 0); // Создание нового объекта OneGroupSum для текущей группы чисел
            executor.execute(oneGroupSums[i]); // Запуск потока
        }

        executor.shutdown(); // Завершение работы пула потоков
        while (!executor.isTerminated()) {} // Ожидание завершения всех потоков

        int totalSum = 0; // Инициализация переменной для хранения общей суммы
        for (OneGroupSum oneGroupSum : oneGroupSums) { // Цикл по всем объектам OneGroupSum
            totalSum += oneGroupSum.getSum(); // Добавление суммы текущей группы к общей сумме
        }

        return totalSum; // Возвращение общей суммы
    }
}

