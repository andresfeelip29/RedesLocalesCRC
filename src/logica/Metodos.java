package logica;

public class Metodos {

    public Integer[] binarioG;
    public Integer[] binarioDivisor;
    public Integer[] bitsCeros;

    public Metodos() {
        //...
    }

    /**
     * Metodo para convertir un vector de tipo Integer a String
     */
    public String ArrayToString(Integer[] vector) {
        String dato = "";
        for (Integer numero : vector) {
            dato = dato + String.valueOf(numero);
        }
        return dato;
    }

    /**
     * Metodo utilizado para validar que los datos recibidos desde el mensaje y
     * pGerador sean validor de tipo 1 y 0
     */
    public boolean validador(String dato) {
        int largo = dato.length();
        boolean validador = false;
        for (int i = 0; i < largo; i++) {
            if (dato.charAt(i) == '0' || dato.charAt(i) == '1') {
                validador = true;
            } else {
                validador = false;
                break;
            }
        }
        return validador;
    }

    /**
     * Metodo para crear los arreglos en tiempo de ejecucion
     */
    public void crearVector(int tamanioMensaje, int tamnioPGeneradores) {
        this.binarioG = new Integer[tamanioMensaje];
        this.binarioDivisor = new Integer[tamnioPGeneradores];
        this.bitsCeros = new Integer[tamnioPGeneradores - 1];
    }

    /**
     * Metodo encargado de comvertir el polinomio de String a un arreglo de
     * numeros para poder realizar los respectivos calculos se utilizara para
     * obtener los []mensaje y []pGenerador
     */
    public void convertirTextoAArreglo(String mensaje, String generador) {
        int largoMensaje = mensaje.length();
        int LargoGenerador = generador.length();
        for (int i = 0; i < largoMensaje; i++) {
            this.binarioG[i] = Integer.parseInt(String.valueOf(mensaje.charAt(i)));
        }
        for (int i = 0; i < LargoGenerador; i++) {
            this.binarioDivisor[i] = Integer.parseInt(String.valueOf(generador.charAt(i)));
        }
        calcularBitsR();
    }

    //Segundo metodo para conver de un String en arreglo
    public Integer[] convertirTextoAArreglos(String mensaje) {
        int largoMensaje = mensaje.length();
        Integer[] arregloEnteros = new Integer[largoMensaje];
        for (int i = 0; i < largoMensaje; i++) {
            arregloEnteros[i] = Integer.parseInt(String.valueOf(mensaje.charAt(i)));
        }
        return arregloEnteros;
    }

    /**
     * Metodo para obtener el tamaño de bitsCeros sabiendo que una secuentacion
     * de 0 con un tamaño igual al generador - 1
     */
    public void calcularBitsR() {
        int largo = this.bitsCeros.length;
        for (int i = 0; i < largo; i++) {
            this.bitsCeros[i] = 0;
        }
    }

    /**
     * Metodo utilizando para unir para unir dos arreglos en este caso
     * []binarioG con []bitsCeros y luego []binarioG con el crc este metodo une
     * el arreglo de mayor tamañao primero y despues el de menor tamaño
     */
    public Integer[] unirArreglos(Integer[] vector1, Integer[] vector2) {
        int contador = 0;
        int largoArreglo1 = vector1.length;
        int largoNuevoArreglo = largoArreglo1 + vector2.length;
        Integer[] nuevoArreglo = new Integer[largoNuevoArreglo];
        for (int i = 0; i < largoNuevoArreglo; i++) {
            if (i < largoArreglo1) {
                nuevoArreglo[i] = vector1[i];
            } else {
                nuevoArreglo[i] = vector2[contador++];
            }
        }

        return nuevoArreglo;
    }

    public Integer[] calcularCRC(Integer[] vector1) {
        /**
         * Arreglo que almacenara los valores del resto
         */
        Integer[] crc;
        /**
         * Se hace una llamada al metodo combinar dos arreglos se obtiene los
         * largos del nuevo arreglo del pgeneradores[] se les asigna el tamaño a
         * los arreglos auxiliar y resto
         */
        Integer[] dividendo = vector1;
        int largoDividendo = dividendo.length;
        int largoDivisor = this.binarioDivisor.length;
        int largoR = this.bitsCeros.length;
        crc = new Integer[largoDivisor - 1];
        int largoCRC = crc.length;

        /**
         * En el primer for se reccore el largo del del dividendo menos el
         * tamaño de r que es los 0 que se le agregan al mensaje en el
         * condicional se valida que se empienze desde la posicion que contenga
         * 1 en el segundo for recorre el tamaño de divisor osea el polinomio
         * generador despues por cada poscion que se recorra del largo del
         * dividendo sumado a la poscion de recorido de divisor se va realiar la
         * operacion xor con el divisor en este caso el arreglo pGenradores []
         * est operacion se realiza con el operador ^ que corresponde operacion
         * xor bit a bit
         */
        for (int i = 0; i < largoDividendo - largoR; i++) {
            if (dividendo[i] == 1) {
                for (int j = 0; j < largoDivisor; j++) {
                    dividendo[i + j] ^= binarioDivisor[j];
                }
            }
        }

        /**
         * En este el dato del crc se va igresar al arreglo crc[] con el fin de
         * eliminar todos los ceros en la izquierda y que solo queden los datos
         * correspondientes al crc si todos los datos son 0 del dividendo
         * entonces llena el crc con ceros para la respectiva validacion en el
         * receptor
         */
//        for (int j = 0; j < largoDividendo; j++) {
//            if (dividendo[j] != 0) {
//                for (int k = 0; k < largoCRC; k++) {
//                    crc[k] = dividendo[k + j];
//                }
//                break;
//            } else {
//                for (int k = 0; k < largoCRC; k++) {
//                    crc[k] = 0;
//                }
//            }
//        }
        for (int j = 0; j < largoCRC; j++) {
            int asignar = (largoDividendo - largoCRC) + j;
            crc[j] = dividendo[asignar];
        }

        return crc;
    }
}
