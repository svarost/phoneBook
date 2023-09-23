package repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileRepository implements Repository {
    private String repositoryDirectory = "src/main/java/storage/";
    private NoteMapper mapper = new NoteMapper();

    public FileRepository() {
        createDirectory();
    }

    private void createDirectory() {
        File directory = new File(repositoryDirectory);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Папка для сохранения заметок создана.");
            } else {
                System.out.println("Не удалось создать папку для хранения заметок.");
            }
        } else
            System.out.println("Папка для сохранения заметок уже существует.");
    }

    @Override
    public void writeNote(Note note) throws WriteFilleException {
        String fileName = repositoryDirectory + note.getLastName() + ".txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(note.toString() + "\n");
        } catch (IOException e) {
            throw new WriteFilleException("Ошибка при сохранении файла.");
        }
    }
}
