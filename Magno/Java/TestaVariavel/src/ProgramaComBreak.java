public class ProgramaComBreak {
    public static void main(String[] args) {
        for (int linha=1; linha<=5; linha++){
            System.out.print(linha+" ");
            for (int coluna=1; coluna<=linha; coluna++){
                System.out.print(coluna);
            }
            System.out.println();
        }

    }
}
