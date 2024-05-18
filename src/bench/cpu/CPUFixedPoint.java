package bench.cpu;

import bench.IBenchmark;

public class CPUFixedPoint implements IBenchmark {
    private int size;
    private int[]res;
    int[] num = {0,1,2,3};
    private int j,k,l;

    private int[]a;
    private int[]b;
    private int[]c;

    public void run(){

    }

    public void test1(){//29
        for(int i = 0;i < this.size;++i){
            j = num[1] * (k - j) * (l - k);
            k = num[3] * k - (l - j) * k;
            l = (l - k) * (num[2] + j);
            res[l] = j + k + l;
            res[k] = j * k * l;
        }
    }

    public void test2(){//11
        for(int i = 0;i < this.size;++i){
            if (j == 1) {
                j = num[2];
            } else {
                j = num[3];
            }
            if (j > 2) {
                j = num[0];
            } else {
                j = num[1];
            }
            if (j < 1) {
                j = num[1];
            } else {
                j = num[0];
            }
        }
    }

    public void test3(){//6
        for(int i = 0;i < this.size;++i){
            c[i] = a[b[i]];
        }
    }

    public void run(Object ... params){
        test1();
        test2();
        test3();
    }

    public void initialize(Object ... params){
        this.size = (Integer)params[0];
        res = new int[this.size];
        a = new int[this.size];
        b = new int[this.size];
        c = new int[this.size];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * this.size);
            b[i] = (int) (Math.random() * this.size);
        }
    }

    public void clean(){

    }

    public void cancel(){

    }

    public void warmUP(){
        for(int i = 0;i < 15;++i){
            test1();
            test2();
            test3();
        }
    }
}
