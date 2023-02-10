package lk.ijse.supermarket.dto;

public class User {

    private String userId;
    private String password;
    private String question;
    private String answer;
    private String usrEmpId;

    public User() {
    }

    public User(String userId, String password, String question, String answer, String usrEmpId) {
        this.userId = userId;
        this.password = password;
        this.question = question;
        this.answer = answer;
        this.usrEmpId = usrEmpId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUsrEmpId() {
        return usrEmpId;
    }

    public void setUsrEmpId(String usrEmpId) {
        this.usrEmpId = usrEmpId;
    }
}