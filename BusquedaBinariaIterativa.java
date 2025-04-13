public class BusquedaBinariaIterativa {
    public static int busquedaBinaria(int[] arr, int x) {
        int inicio = 0, fin = arr.length - 1;
        while (inicio <= fin) {
            int mid = inicio + (fin - inicio) / 2;
            if (arr[mid] == x)
                return mid;
            if (arr[mid] < x)
                inicio = mid + 1;
            else
                fin = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9, 11, 13 };
        int x = 9;
        int resultado = busquedaBinaria(arr, x);
        System.out.println((resultado != -1) ? "Elemento encontrado en índice " + resultado : "Elemento no encontrado");
    }
}