package utilities;

public class Utility
{
    public static final <T> void swap (int[] a, int i, int j)
    {
        int t = a[i];

        a[i] = a[j];

        a[j] = t;
    }
}
