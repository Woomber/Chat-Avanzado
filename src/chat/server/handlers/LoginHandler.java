package chat.server.handlers;

import chat.models.Usuario;

/**
 * Handler de inicio de sesión
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class LoginHandler implements Handler {
    
    private final String username;
    private final String password;

    public LoginHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean run() {
        Usuario u = new Usuario();
        // Login TODO
        return false;

    }

}
