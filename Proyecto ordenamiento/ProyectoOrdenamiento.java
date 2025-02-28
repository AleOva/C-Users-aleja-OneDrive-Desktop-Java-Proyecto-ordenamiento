import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProyectoOrdenamiento {

    public static void mostrarInformacion() {
        // Datos fijos del proyecto
        String universidad = "Universidad Da Vinci de Guatemala";
        String curso = "Estructura de Datos";
        String docente = "Ing. Brandon Chitay";

        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Solicitar el nombre del estudiante
        System.out.print("Ingrese su nombre: ");
        String estudiante = scanner.nextLine();

        // Listas de tareas completadas y pendientes
        List<String> puntosHechos = new ArrayList<>();
        List<String> puntosFaltantes = new ArrayList<>();

        // Se inicia el proyecto con los primeros puntos completados
        puntosHechos.add("1. Información del Desarrollador");
        puntosHechos.add("2. Menú Principal");

        // Lista de tareas restantes
        puntosFaltantes.add("3. Carga de Datos desde un CSV");
        puntosFaltantes.add("4. Algoritmo de Ordenamiento - Bubble Sort");
        puntosFaltantes.add("5. Algoritmo de Ordenamiento - Enhanced Bubble Sort");
        puntosFaltantes.add("6. Algoritmo de Ordenamiento - Quick Sort");
        puntosFaltantes.add("7. Algoritmo de Ordenamiento - Selection Sort");
        puntosFaltantes.add("8. Algoritmo de Ordenamiento - Merge Sort");
        puntosFaltantes.add("9. Algoritmo de Búsqueda - Binary Search");
        puntosFaltantes.add("10. Presentación Final en YouTube");

        // Mostrar la información en consola
        System.out.println("\n===============================");
        System.out.println(universidad);
        System.out.println(curso);
        System.out.println(docente + "\n");
        System.out.println("Nombre del estudiante: " + estudiante + "\n");

        // Mostrar tareas completadas
        System.out.println("PUNTOS HECHOS:");
        for (String punto : puntosHechos) {
            System.out.println("  " + punto);
        }

        // Mostrar tareas pendientes
        System.out.println("\nPUNTOS FALTANTES:");
        for (String punto : puntosFaltantes) {
            System.out.println("  " + punto);
        }

        System.out.println("=================================");
        // Esperar a que el usuario presione Enter antes de continuar
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine(); // Captura la entrada del usuario

        // Llamar al menú principal
        mostrarMenu(scanner);
    }

    public static void mostrarMenu(Scanner scanner) {
        List<Integer> listaDatos = new ArrayList<>(); // Lista para almacenar datos
        int opcion;

        do {
            // Mostrar opciones
            System.out.println("\n========== MENÚ PRINCIPAL ==========");
            System.out.println("1. Cargar datos desde un archivo CSV");
            System.out.println("2. Ordenar datos usando Bubble Sort");
            System.out.println("3. Ordenar datos usando Enhanced Bubble Sort");
            System.out.println("4. Ordenar datos usando Quick Sort");
            System.out.println("5. Ordenar datos usando Selection Sort");
            System.out.println("6. Ordenar datos usando Merge Sort");
            System.out.println("7. Buscar un número con Binary Search");
            System.out.println("8. Salir");
            System.out.print("Ingrese una opción: ");

            // Leer opción del usuario
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                scanner.next(); // Limpiar la entrada no válida
            }

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            // Ejecutar la acción correspondiente
            switch (opcion) {
                case 1:
                    cargarDatosDesdeCSV(scanner, listaDatos);
                    break;
                case 2:
                    ordenarDatos(listaDatos, new BubbleSort());
                    break;
                case 3:
                    ordenarDatos(listaDatos, new EnhancedBubbleSort());
                    break;
                case 4:
                    ordenarDatos(listaDatos, new QuickSort());
                    break;
                case 5:
                    ordenarDatos(listaDatos, new SelectionSort());
                    break;
                case 6:
                    ordenarDatos(listaDatos, new MergeSort());
                    break;
                case 7:
                    BinarySearch.opcionBinarySearch(listaDatos);
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 8);

        // Cerrar el scanner
        scanner.close();
    }

    public static void cargarDatosDesdeCSV(Scanner scanner, List<Integer> listaDatos) {
        // Lógica para cargar datos desde un archivo CSV
        System.out.print("Ingrese el nombre del archivo CSV (con extensión): ");
        String nombreArchivo = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Suponiendo que cada línea contiene números separados por comas
                String[] valores = linea.split(","); // Separar por comas
                for (String valor : valores) {
                    try {
                        listaDatos.add(Integer.parseInt(valor.trim())); // Convertir a entero y agregar a la lista
                    } catch (NumberFormatException e) {
                        System.out.println("Valor no válido: " + valor + ". Se ignorará.");
                    }
                }
            }
            System.out.println("Datos cargados correctamente: " + listaDatos);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void ordenarDatos(List<Integer> listaDatos, Ordenamiento algoritmo) {
        if (listaDatos.isEmpty()) {
            System.out.println("No hay datos para ordenar.");
            return;
        }

        System.out.println("Lista antes de ordenar: " + listaDatos);
        algoritmo.ordenar(listaDatos);
        System.out.println("Lista ordenada: " + listaDatos);
    }

    public static void main(String[] args) {
        mostrarInformacion(); // Llamamos a la función principal
    }
}

// Interfaz para los algoritmos de ordenamiento
interface Ordenamiento {
    void ordenar(List<Integer> lista);
}

// Clase BubbleSort
class BubbleSort implements Ordenamiento {
    public void ordenar(List<Integer> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (lista.get(j) > lista.get(j + 1)) {
                    // Intercambiar elementos
                    int temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
        System.out.println("Lista ordenada con Bubble Sort.");
    }
}

// Clase EnhancedBubbleSort
class EnhancedBubbleSort implements Ordenamiento {
    public void ordenar(List<Integer> lista) {
        int n = lista.size();
        boolean intercambiado;

        for (int i = 0; i < n - 1; i++) {
            intercambiado = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (lista.get(j) > lista.get(j + 1)) {
                    int temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                    intercambiado = true;
                }
            }

            if (!intercambiado) break;
        }
        System.out.println("Lista ordenada con Enhanced Bubble Sort.");
    }
}

// Clase QuickSort
class QuickSort implements Ordenamiento {
    public void ordenar(List<Integer> lista) {
        if (lista.isEmpty()) return;
        ordenar(lista, 0, lista.size() - 1);
    }

    private void ordenar(List<Integer> lista, int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particion(lista, inicio, fin);
            ordenar(lista, inicio, indicePivote - 1);
            ordenar(lista, indicePivote + 1, fin);
        }
    }

    private int particion(List<Integer> lista, int inicio, int fin) {
        int pivote = lista.get(fin);
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (lista.get(j) < pivote) {
                i++;
                int temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }

        int temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(fin));
        lista.set(fin, temp);

        return i + 1;
    }
}

// Clase SelectionSort
class SelectionSort implements Ordenamiento {
    public void ordenar(List<Integer> lista) {
        int n = lista.size();

        for (int i = 0; i < n - 1; i++) {
            int indiceMinimo = i;

            for (int j = i + 1; j < n; j++) {
                if (lista.get(j) < lista.get(indiceMinimo)) {
                    indiceMinimo = j;
                }
            }

            int temp = lista.get(i);
            lista.set(i, lista.get(indiceMinimo));
            lista.set(indiceMinimo, temp);
        }
        System.out.println("Lista ordenada con Selection Sort.");
    }
}

// Clase MergeSort
class MergeSort implements Ordenamiento {
    public void ordenar(List<Integer> lista) {
        if (lista.size() < 2) {
            return; // Caso base
        }
        int mitad = lista.size() / 2;
        List<Integer> izquierda = new ArrayList<>(lista.subList(0, mitad));
        List<Integer> derecha = new ArrayList<>(lista.subList(mitad, lista.size()));

        ordenar(izquierda);
        ordenar(derecha);
        merge(lista, izquierda, derecha);
    }

    private void merge(List<Integer> lista, List<Integer> izquierda, List<Integer> derecha) {
        int i = 0, j = 0, k = 0;

        while (i < izquierda.size() && j < derecha.size()) {
            if (izquierda.get(i) < derecha.get(j)) {
                lista.set(k++, izquierda.get(i++));
            } else {
                lista.set(k++, derecha.get(j++));
            }
        }

        while (i < izquierda.size()) {
            lista.set(k++, izquierda.get(i++));
        }

        while (j < derecha.size()) {
            lista.set(k++, derecha.get(j++));
        }
    }
}

// Clase BinarySearch
class BinarySearch {
    public static int buscar(List<Integer> lista, int objetivo) {
        int inicio = 0;
        int fin = lista.size() - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;

            if (lista.get(medio) == objetivo) {
                return medio; // Devuelve el índice del elemento
            }

            if (lista.get(medio) < objetivo) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return -1; // Si el elemento no está presente en la lista
    }

    public static void opcionBinarySearch(List<Integer> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay datos cargados. Cargue datos primero.");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese el número que desea buscar: ");
            int objetivo = scanner.nextInt();

            int resultado = buscar(lista, objetivo);

            if (resultado == -1) {
                System.out.println("El número no está en la lista.");
            } else {
                System.out.println("El número " + objetivo + " se encuentra en el índice: " + resultado);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la entrada: " + e.getMessage());
        }
    }
}

