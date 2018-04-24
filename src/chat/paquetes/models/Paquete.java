package chat.paquetes.models;

import chat.exceptions.PaqueteException;
import java.io.Serializable;

/**
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

    public Param getParam(String param) {
        for (Param param1 : params) {
            if (param1.getNombre().equals(param)) {
                return param1;
            }
        }
        return null;
    }
    
    public String getValue(String param){
        Param p = getParam(param);
        if(p == null) return null;
        return p.getValor();
    }

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

    protected final void addParam(String nombre, String valor) throws PaqueteException {
        addParam(new Param(nombre, valor));
    }

    private void resize(int size) {
        Param[] temp = new Param[size];
        System.arraycopy(params, 0, temp, 0, Math.min(params.length, size));
        params = temp;
    }
}
