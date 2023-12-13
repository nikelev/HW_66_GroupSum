package ait.numbers;

import ait.numbers.model.ExecutorGroupsSum;
import ait.numbers.model.GroupSum;
import ait.numbers.model.ParallelStreamGroupSum;
import ait.numbers.model.ThreadGroupSum;
import ait.numbers.test.GroupSumPerfomanseTest;

import java.util.Random;

public class GroupSumAppl {
    private static final int N_GROUPS =10_000 ;
    private static final int NUMBER_PER_GROUP =10_000 ;
    private  static final int [][] arr=new int[N_GROUPS][NUMBER_PER_GROUP];
    static Random random=new Random();
    public static void main(String[] args) {
        fillArray();
        GroupSum threadGroupSum=new ThreadGroupSum(arr);
        GroupSum exexutorGroupSum=new ExecutorGroupsSum(arr);
        GroupSum streamGroupSum=new ParallelStreamGroupSum(arr);

        new GroupSumPerfomanseTest("ThreadGroupSum",threadGroupSum).runTest();
        new GroupSumPerfomanseTest("ExecutorGroupsSum",exexutorGroupSum).runTest();
        new GroupSumPerfomanseTest("ParallelStreamGroupSum",streamGroupSum).runTest();

    }
    private static void fillArray(){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j]=random.nextInt();
            }
        }
    }
}
