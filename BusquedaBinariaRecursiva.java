public class BusquedaBinariaRecursiva {
    public static int busquedaBinaria(int[] arr, int inicio, int fin, int x) {
        if (inicio <= fin) {
            int mid = inicio + (fin - inicio) / 2;
            if (arr[mid] == x)
                return mid;
            if (arr[mid] > x)
                return busquedaBinaria(arr, inicio, mid - 1, x);
            return busquedaBinaria(arr, mid + 1, fin, x);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9, 11, 13 };
        int x = 7;
        int resultado = busquedaBinaria(arr, 0, arr.length - 1, x);
        System.out.println((resultado != -1) ? "Elemento encontrado en índice " + resultado : "Elemento no encontrado");
    }
}