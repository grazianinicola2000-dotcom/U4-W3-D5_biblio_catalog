package nicolagraziani.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(int isbn) {
        super("L'Item con ISBN: " + isbn + " non è stato trovato");
    }
}
