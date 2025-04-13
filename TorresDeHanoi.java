public class TorresDeHanoi {
    public static void main(String[] args) {
        int n = 4;
        torresHanoi(n, 'A', 'B', 'C');
    }

    public static void torresHanoi(int discos, char origen, char auxiliar, char destino) {
        if (discos == 1) {
            System.out.println("Mover disco de " + origen + " a " + destino);
        } else {
            torresHanoi(discos - 1, origen, destino, auxiliar);
            System.out.println("Mover disco de " + origen + " a " + destino);
            torresHanoi(discos - 1, auxiliar, origen, destino);
        }
    }
}