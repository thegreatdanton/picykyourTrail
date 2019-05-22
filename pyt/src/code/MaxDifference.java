package code;

import java.util.Scanner;

public class MaxDifference {
    public static void main(String[] args)
    {
        MaxDifference maxdif = new MaxDifference();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of input numbers");
        int n = sc.nextInt();
        int arr[] = new int[n];
        System.out.println("Enter numbers one by one :");
        int count = 1;
        while(count <= n) {
            int s1 = sc.nextInt();
            if(s1 == -1) {
                break;
            }
            arr[count-1] = s1;
            count ++;
        }
        int size = arr.length;
        System.out.println("MaximumDifference is " +
                maxdif.maxDiff(arr, size));
    }

    private int maxDiff(int arr[], int arr_size)
    {
        int max_diff = arr[1] - arr[0];
        int min_element = arr[0];
        int i;
        for (i = 1; i < arr_size; i++)
        {
            if (arr[i] - min_element > max_diff)
                max_diff = arr[i] - min_element;
            if (arr[i] < min_element)
                min_element = arr[i];
        }
        return max_diff;
    }
}
