package chat.mensajes.models;

import java.io.Serializable;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class Mensaje implements Serializable {

    protected String orden;
    protected Param[] params;

    public Mensaje() {

    }

    public Mensaje(String orden) {
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

    protected final void addParam(Param param) {
        int size = params.length + 1;
        resize(size);
        params[size - 1] = param;
    }

    protected final void addParam(String nombre, String valor) {
        addParam(new Param(nombre, valor));
    }

    private void resize(int size) {
        Param[] temp = new Param[size];
        System.arraycopy(params, 0, temp, 0, Math.min(params.length, size));
        params = temp;
    }
}
