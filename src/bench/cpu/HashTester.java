package bench.cpu;

public class HashTester {
    private static String result;
    private static final String charSet = "abcdefghijklmnopqrstuvwxyz";

    public static String getNextString(String text) {
        int[] index = new int[text.length()];
        int end = charSet.length() - 1;

        char array[] = text.toCharArray();

        for(int i = 0; i < array.length; ++ i)
        {
            index[i] = array[i] - 'a';
        }

        index[index.length - 1] ++;

        for(int i = index.length - 1 ; i >= 1; i--)
        {
            if(index[i] == 26)
            {
                index[i] = 0;

                index[i - 1] ++;
            }
            else
            {
                break;
            }
        }

        if(index[0] == 26)
        {
            return null;
        }

        for(int i = 0; i < array.length; ++ i)
        {
            array[i] = (char) (index[i] + 'a');
        }

        result = new String(array);

        return result;
    }

    public static void main(String[] args) {

        System.out.println(getNextString("aa"));
    }
}
