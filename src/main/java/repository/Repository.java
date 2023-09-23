package repository;

public interface Repository {
    void writeNote(Note note) throws WriteFilleException;
}
