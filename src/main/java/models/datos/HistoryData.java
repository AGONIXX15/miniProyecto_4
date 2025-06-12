package models.datos;

import java.util.Stack;

public class HistoryData {
   public Stack<String> pila = new Stack<>();

    public void setPila(String pila) {

     this.pila.push(pila);

    }
    public String getPila() {
        if (pila.isEmpty()) {
            return "No hay movimientos registrados aún.";
        }
        return "El último movimiento fue: " + pila.peek();
    }



    public String ShowAllPila() {
        if(pila.isEmpty()) {
            return "No hay movimientos registrados aún.";
        }
        String mensaje = "HISTORIAL DE MOVIMENTOS:\n";
        for (int i = 0; i < pila.size(); i++) {
            mensaje += pila.get(i);
        }
        return mensaje;


    }
}