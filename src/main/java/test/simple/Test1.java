package test.simple;

public class Test1 extends BaseTest{



    public static void main(String [] args){
        int i= 1;
        test1(i);
        logger.info("i=" + i);
    }

    private static void test1(int i){
        i = 3;
        i++;
    }
}
