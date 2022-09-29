package dto;

import jakarta.persistence.*;
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String firstName;
    private String lastname;
    @Column(unique = true)
    private String username;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private int NumberOfQuizzesTokenByUser;
    private int NumberOfRightAnswers;
    private int NumberOfWrongAnswers;

    public Users( String firstName, String lastname, String username) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.username = username;
    }

    public Users() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public int getNumberOfQuizzesTokenByUser() {
        return NumberOfQuizzesTokenByUser;
    }

    public void setNumberOfQuizzesTokenByUser(int numberOfQuizzesTokenByUser) {
        NumberOfQuizzesTokenByUser = numberOfQuizzesTokenByUser;
    }

    public int getNumberOfRightAnswers() {
        return NumberOfRightAnswers;
    }

    public void setNumberOfRightAnswers(int numberOfRightAnswers) {
        NumberOfRightAnswers = numberOfRightAnswers;
    }

    public int getNumberOfWrongAnswers() {
        return NumberOfWrongAnswers;
    }

    public void setNumberOfWrongAnswers(int numberOfWrongAnswers) {
        NumberOfWrongAnswers = numberOfWrongAnswers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", userRole=" + userRole +
                ", NumberOfQuizzesTokenByUser=" + NumberOfQuizzesTokenByUser +
                ", NumberOfRightAnswers=" + NumberOfRightAnswers +
                ", NumberOfWrongAnswers=" + NumberOfWrongAnswers +
                '}';
    }
}

