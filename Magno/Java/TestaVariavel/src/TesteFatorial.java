public class TesteFatorial {
    public static void main(String[] args) {
        int x = 4;
        int f = x;

        while (x>1){
            f =  f*(x-1);
            x--;
        }
        System.out.println(f);
    }
}
