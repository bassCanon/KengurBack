package ba.com.kengur.error;

public class EntityNotFound extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntityNotFound(Long id) {
        super("Book id not found : " + id);
    }

}
