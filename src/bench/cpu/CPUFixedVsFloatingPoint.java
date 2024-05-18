package bench.cpu;

import bench.IBenchmark;
import utilities.NumberRepresentaion;

public class CPUFixedVsFloatingPoint implements IBenchmark {
    private int size;
    private int result;


    public void initialize(Object ... params){
        this.size = (Integer)params[0];
    }
    public void warmUP(){
        for(int i = 0;i < size;++i){
            result = (i >> 256) ;
            result = (int) ((float)i / 256);
        }
    }

    public void run(){

    }

    public void run(Object ... options){
        result = 0;

        switch ((NumberRepresentaion)options[0]){
            case FLOATING:
                for(int i = 0;i < size;++i){
                    result += (float)i / 256;
                }
                break;

            case FIXED:
                for(int i = 0;i < size;++i){
                    result += (i >> 8);
//                    result += (i / 256);
                }
                break;
            default:
                break;
        }
        System.out.println("Result: " + result);
    }

    public void clean(){

    }

    public void cancel() {

    }
}
