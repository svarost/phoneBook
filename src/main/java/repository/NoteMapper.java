package repository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoteMapper {

    public Note map(String noteString) throws InvalidInputException {
        List<String> strList = new ArrayList<>(Arrays.asList(noteString.split(" ")));
        Note note = new Note();
        try {
            if (strList.size() < 6)
                throw new InvalidInputException("Введено некорректное количество данных.");

            String birthday = findBirthday(strList);
            note.setBirthday(birthday);
            strList.remove(birthday);

            String phoneNumber = findPhoneNumber(strList);
            note.setPhoneNumber(phoneNumber);
            strList.remove(phoneNumber);

            char sex = findSex(strList);
            note.setSex(sex);
            strList.remove(String.valueOf(sex));

            Map<String, String> fullName = findFullName(strList);
            note.setFirstName(fullName.get("firstName"));
            note.setLastName(fullName.get("lastName"));
            note.setPatronymic(fullName.get("patronymic"));
        } catch (InvalidInputException e) {
            throw new InvalidInputException(e.getMessage());
        }
        return note;
    }

    private String findBirthday(List<String> strings) throws InvalidInputException {
        Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        for (String s : strings) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                strings.remove(s);
                return s;
            }
        }
        throw new InvalidInputException("Дата введена некорректно.");
    }

    private String findPhoneNumber(List<String> strings) throws InvalidInputException {
        for (String s : strings) {
            if (isNumber(s))
                return s;
        }
        throw new InvalidInputException("Номер телефона введен некорректно.");
    }

    private char findSex(List<String> strings) throws InvalidInputException {
        for (String s : strings) {
            if (s.equals("f") || s.equals("m")) {
                strings.remove(s);
                return s.charAt(0);
            }
        }
        throw new InvalidInputException("Пол введен некорректно.");
    }

    private Map<String, String> findFullName(List<String> strings) throws InvalidInputException {
        Map<String, String> name = new HashMap<>();
        int count = 0;
        boolean isPatronymic = false;
        boolean isCorrectNameFormat = true;
        for (int i = 0; i < strings.size(); i++) {
            if (!Character.isUpperCase(strings.get(i).toCharArray()[0])) {
                isCorrectNameFormat = false;
            }
            if ((strings.get(i).substring(strings.get(i).length() - 3)).equals("вич")
                    || strings.get(i).substring(strings.get(i).length() - 3).equals("вна")) {
                name.put("patronymic", strings.get(i));
                count = i;
                isPatronymic = true;
            }
        }

        if (!isCorrectNameFormat)
            throw new InvalidInputException("Фамилия, имя и(или) отчество введены некорректно.");

        if (!isPatronymic)
            throw new InvalidInputException("Отчество введено некорректно.");


        name.put("firstName", strings.get(count - 1));
        strings.remove(strings.get(count));
        strings.remove(strings.get(count - 1));
        name.put("lastName", strings.get(0));
        return name;
    }

    private boolean isNumber(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
