import java.util.PriorityQueue;
import java.util.Comparator;

public class ModaDivideYVenceras {

    // Clase Limits: representa un subarreglo
    static class Limits {
        int[] array;
        int start;
        int end;

        public Limits(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        public int length() {
            return end - start + 1;
        }
    }

    // Clase SetVectors: gestiona subconjuntos
    static class SetVectors {
        private PriorityQueue<Limits> heap;

        public SetVectors() {
            heap = new PriorityQueue<>(new Comparator<Limits>() {
                public int compare(Limits a, Limits b) {
                    return Integer.compare(b.length(), a.length()); // orden descendente
                }
            });
        }

        public void insertar(Limits p) {
            heap.offer(p);
        }

        public Limits mayor() {
            return heap.poll();
        }

        public int longitudMayor() {
            return heap.isEmpty() ? 0 : heap.peek().length();
        }

        public boolean estaVacio() {
            return heap.isEmpty();
        }

        public void destruir() {
            heap.clear();
        }
    }

    // Función para repartir elementos alrededor de la mediana
    public static void pivote2(int[] a, int valor, int ini, int fin, int[] izq, int[] der) {
        int i = ini;
        int j = fin;
        int k = ini;

        while (k <= j) {
            if (a[k] < valor) {
                int temp = a[i];
                a[i] = a[k];
                a[k] = temp;
                i++;
                k++;
            } else if (a[k] > valor) {
                int temp = a[k];
                a[k] = a[j];
                a[j] = temp;
                j--;
            } else {
                k++;
            }
        }
        izq[0] = i;
        der[0] = j + 1;
    }

    // Método para calcular la moda usando Divide y Vencerás
    public static int moda3(int[] a, int prim, int ult) {
        Limits p = new Limits(a, prim, ult);
        SetVectors homogeneo = new SetVectors();
        SetVectors heterogeneo = new SetVectors();
        heterogeneo.insertar(p);

        while (heterogeneo.longitudMayor() > homogeneo.longitudMayor()) {
            p = heterogeneo.mayor();

            int mediana = a[(p.start + p.end) / 2];
            int[] izq = new int[1], der = new int[1];
            pivote2(p.array, mediana, p.start, p.end, izq, der);

            Limits p1 = new Limits(p.array, p.start, izq[0] - 1);
            Limits p2 = new Limits(p.array, izq[0], der[0] - 1);
            Limits p3 = new Limits(p.array, der[0], p.end);

            if (p1.start <= p1.end)
                heterogeneo.insertar(p1);
            if (p3.start <= p3.end)
                heterogeneo.insertar(p3);
            if (p2.start <= p2.end)
                homogeneo.insertar(p2);
        }

        if (homogeneo.estaVacio())
            return a[prim];

        p = homogeneo.mayor();
        homogeneo.destruir();
        heterogeneo.destruir();
        return p.array[p.start];
    }

    // Main para probar
    public static void main(String[] args) {
        int[] arreglo = { 2, 5, 2, 2, 2, 2, 3, 3, 5, 3, 2 };
        int resultado = moda3(arreglo, 0, arreglo.length - 1);
        System.out.println("La moda es: " + resultado);
    }
}