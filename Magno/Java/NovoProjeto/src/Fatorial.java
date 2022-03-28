public class Fatorial {
    public static void main(String[] args) {
        int x = 6;
        for (int f = x; x>1; x--){
            f=f*(x-1);
            System.out.println(f);
        }
    }
}
