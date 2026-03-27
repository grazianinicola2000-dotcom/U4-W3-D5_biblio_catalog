package nicolagraziani.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int code) {
        super("L'utente con il numero di tessera: " + code + " non è stato trovato");
    }
}
