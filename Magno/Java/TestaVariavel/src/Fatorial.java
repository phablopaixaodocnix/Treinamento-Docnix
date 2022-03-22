public class Fatorial {
    public static void main(String[] args) {
        int x = 6;
        int f = x;
        while(x>1){
            f = f*(x-1);
            x--;
            System.out.println(f);
        }
    }
}