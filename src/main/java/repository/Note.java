package repository;

public class Note {
    private int _id = 1;
    private String _lastName;
    private String _firstName;
    private String _patronymic;
    private String _birthday;
    private String _phoneNumber;
    private char _sex;

    public Note() {
        _id = 1;
    }

    public Note(String _lastName, String _firstName, String _patronymic, String _birthday,
                String _phoneNumber, char _sex) {
        _id++;
        this._lastName = _lastName;
        this._firstName = _firstName;
        this._patronymic = _patronymic;
        this._birthday = _birthday;
        this._phoneNumber = _phoneNumber;
        this._sex = _sex;
    }



    public void set_id() {
        _id++;
    }

    public int get_id() {
        return _id;
    }

    public String getLastName() {
        return _lastName;
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getPatronymic() {
        return _patronymic;
    }

    public String getBirthday() {
        return _birthday;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public char getSex() {
        return _sex;
    }

    public void setLastName(String _lastName) {
        this._lastName = _lastName;
    }

    public void setFirstName(String _firstName) {
        this._firstName = _firstName;
    }

    public void setPatronymic(String _patronymic) {
        this._patronymic = _patronymic;
    }

    public void setBirthday(String _birthday) {
        this._birthday = _birthday;
    }

    public void setPhoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public void setSex(char _sex) {
        this._sex = _sex;
    }

    @Override
    public String toString() {
        return String.format("<%s><%s><%s><%s><%s><%c>",
                _lastName, _firstName, _patronymic, _birthday, _phoneNumber, _sex);
    }
}
