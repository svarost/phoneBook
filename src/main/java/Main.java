import repository.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidInputException {
        Repository repository = new FileRepository();
        NoteMapper mapper = new NoteMapper();
        String messageEn = "Please enter the last name, first name, patronymic, date of birth (in DD.MM.YYYY format), \n" +
                "phone number and gender (in M or F format) of the person separated by a space. " +
                "To exit, enter \"quit\".";
        String messageRu = "Пожалуйста, введите Фамилию, Имя, Отчество, дату рождения (в формате ДД.ММ.ГГГГ), \n" +
                "номер телефона (только цифры), пол (в формате M or F) персоны, разделенные пробелом. " +
                "Для выхода введите \"quit\".";
        while (true) {
            String input = promt(messageRu);
            if (input.toUpperCase().equals("QUIT")) {
                return;
            }
            try {
                Note note = mapper.map(input);
                System.out.println(note);
                repository.writeNote(note);
                System.out.println("Заметка успешно сохранена. \n");

            } catch (InvalidInputException exception) {
                System.out.println(exception.getMessage() + "\n");
            } catch (WriteFilleException e) {
                System.out.println(e.getMessage());;
            }
        }
    }

    private static String promt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        return in.nextLine();
    }
}
