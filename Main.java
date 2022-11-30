package org.example;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    final static int SIZE = 60_000_000;
    final static int HALF = SIZE / 2;

    public static void main(String[] args) {
        oneThread();
        twoTheread();
    }

    public static void oneThread()
    {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++)
        {

            arr[i]=(float )(arr [i]*Math . sin (0.2f+i/5)* Math.cos (0.2f+i  /5)*Math.cos (0.4f+i  /2));
        }
        //System.out.println(Arrays.toString(arr));
        System.out.println("время работы " + (System.currentTimeMillis() - a));
        System.out.println(arr[SIZE - 1]);
    }
    public static void twoTheread(){
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        long a = System.currentTimeMillis();
        float[] leftArray = new float[HALF];
        float[] rightArray = new float[HALF];
        System.arraycopy(arr,0,leftArray,0, HALF);
        System.arraycopy(arr, HALF,rightArray,0, HALF);
        Thread threadLeft = new Thread(() -> {
            for ( int i = 0; i/2 < leftArray.length;i++)
            {
                leftArray[i/2] =  arr[i]=(float )(arr [i]*Math.sin(0.2f+i/5)* Math.cos(0.2f+i  /5)*Math.cos (0.4f+i  /2));
            }
//            System.out.println(Arrays.toString(arr));
        });
        Thread threadRight = new Thread(() ->{
            for(int i = 0; i/2 < rightArray.length; i++){
                arr[i/2]=(float)(arr [i]*Math.sin (0.2f+i/5)* Math.cos (0.2f+i  /5)*Math.cos (0.4f+i /2));
            }
  //          System.out.println(Arrays.toString(arr));
        });
        threadLeft.start();
        threadRight.start();
        try {
            threadLeft.join();
            threadRight.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.arraycopy(leftArray, 0, arr, 0, HALF);
        System.arraycopy(rightArray,0,arr,0, HALF);
        System.out.println("время работы " + (System.currentTimeMillis() - a));
        System.out.println(arr[SIZE - 1]);
    }
}