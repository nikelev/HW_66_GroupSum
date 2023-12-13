package ait.numbers.model;

import java.util.Arrays;

public class ParallelStreamGroupSum extends GroupSum{
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        return Arrays.stream(numberGroups) // Преобразование двумерного массива в поток
                .parallel() // Преобразование потока в параллельный поток
                .mapToInt(group -> Arrays.stream(group).sum()) // Преобразование каждой группы чисел в сумму чисел в группе
                .sum(); // Суммирование всех групп
    }
}
