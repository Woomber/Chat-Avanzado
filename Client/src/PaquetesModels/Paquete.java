package PaquetesModels;

import Exceptions.PaqueteException;
import java.io.Serializable;

/**
 * Clase Paquete
 * 
 * Contiene la orden y los parámetros para la comunicación entre cliente y
 * servidor.
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class Paquete implements Serializable {

    protected String orden;
    protected Param[] params;

    public Paquete() {

    }

    public Paquete(String orden) {
        this.params = new Param[0];
        this.orden = orden;
    }

    public String getOrden() {
        return orden;
    }

    public Param[] getParams() {
        return params;
    }

    /**
     * Obtiene un parámetro
     * @param param El nombre del parámetro
     * @return El parámetro
     */
    public Param getParam(String param) {
        for (Param param1 : params) {
            if (param1.getNombre().equals(param)) {
                return param1;
            }
        }
        return null;
    }
    
    /**
     * Obtiene el valor de un parámetro
     * @param param El nombre del parámetro
     * @return El valor del parámetro
     */
    public String getValue(String param){
        Param p = getParam(param);
        if(p == null) return null;
        return p.getValor();
    }

    /**
     * Agrega un parámetro al Paquete
     * @param param El parámetro a agregar
     * @throws PaqueteException En caso de que ya exista un parámetro con ese nombre
     */
    protected final void addParam(Param param) throws PaqueteException {
        for(Param p : params){
            if(p.getNombre().equals(param.getNombre()))
                throw new PaqueteException("Ya existe un parámetro de nombre '"
                        + p.getNombre() + "'");
        }
        int size = params.length + 1;
        resize(size);
        params[size - 1] = param;
    }

    /**
     * Agrega un parámetro al Paquete
     * @param nombre El nombre del parámetro
     * @param valor El valor del parámetro
     * @throws PaqueteException En caso de que ya exista un parámetro con ese nombre
     */
    protected final void addParam(String nombre, String valor) throws PaqueteException {
        addParam(new Param(nombre, valor));
    }

    /**
     * Cambia el tamaño del arreglo de parámetros
     * @param size El nuevo tamaño
     */
    private void resize(int size) {
        Param[] temp = new Param[size];
        System.arraycopy(params, 0, temp, 0, Math.min(params.length, size));
        params = temp;
    }
}
