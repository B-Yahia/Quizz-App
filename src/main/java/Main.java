import dto.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        startApplication( repository);
    }

    private static void startApplication(Repository repository) {
        System.out.println("Please enter your username");
        String username = getUserInput();
        Users LoggedUser = getUser(username,repository);
        if (LoggedUser.getLastname() != null){
            System.out.println("welcome "+ LoggedUser.getFirstName() +" "+ LoggedUser.getLastname());
            if (LoggedUser.getUserRole().equals(UserRole.Administrator)){
                adminMenu(repository);
            }else {
                userMenu(LoggedUser,repository);
            }
        }else {
            System.out.println("the username is incorrect ,please try again");
            startApplication(repository);
        }
    }

    private static void adminMenu(Repository repository) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please choose an option\n" +
                "1- Create a new user\n" + "2- Add question to the DB\n" +
                "3- See the list of users\n" + "4- Return to the login page\n" +
                "5- Display the questions list \n" + "6- delete an question from the list");
        int userInput = keyboard.nextInt();
        if (userInput==1){
            CreateUser(repository);
        }else if (userInput==2){
            CreateQuestion(repository);
        }else if (userInput==3){
            seeListOfUsers(repository);
        }else if (userInput==4){
            startApplication(repository);
        }else if (userInput==5){
            displayTheListOfQuestions(repository);
        }else if (userInput==6){
            deleteQuestionFromList(repository);
        }else {
            System.out.println("Invalid input ");
        }
        adminMenu(repository);
    }

    private static void deleteQuestionFromList(Repository repository) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the ID of the question");
        int userInput = keyboard.nextInt();
        List<Question> questionList =  repository.getListOfQuestions();
        for (Question q: questionList) {
            if(userInput == q.getQuestionId()){
                Question questionToDelete = q;
                repository.deleteQuestion(questionToDelete);
            }
        }

    }

    private static void displayTheListOfQuestions(Repository repository) {
        List<Question> questionList = repository.getListOfQuestions();
        for (Question q: questionList) {
            System.out.println("The question ID is : "+q.getQuestionId()+" and the question statement is : "+q.getTheQuestion());
        }
    }

    private static void seeListOfUsers(Repository repository) {
        List<Users> usersList = repository.getListOfUsers();
        System.out.println("You Database has "+usersList.size()+" users");
        for (Users u:usersList) {
            System.out.println(u.getFirstName()+" "+u.getLastname() +" is a " +u.getUserRole().toString());
        }
    }

    private static Users getUser(String userName ,Repository repository) {

        List<Users> usersList = repository.getListOfUsers();
        Users user = new Users();
        for (Users u:usersList ) {
            if (userName.equals(u.getUsername())){
                user = u;
            }
        }
        return user;
    }

    private static void userMenu(Users userLogged,Repository repository) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("please choose one of the options\n" + "1-Take a quizz\n" + "2- Return to the Login menu\n" + "3- See My statics \n" + "4- Change your account details" );
        int userInput = keyboard.nextInt();
        if (userInput == 1){
            takeQuizz(userLogged,repository);
        }else if (userInput == 2){
            startApplication(repository);
        }else if (userInput==3){
            userStatics(userLogged);
        }else if (userInput==4) {
            makeChangeInProfile(userLogged,repository);
        } else {
            System.out.println("invalid input");
        }
        userMenu(userLogged,repository);
    }

    private static void makeChangeInProfile(Users userLogged,Repository repository) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Hi "+userLogged.getFirstName()+" "+userLogged.getLastname()+" \n" +
                "To change your first name type (f) and to change your last name type (l)");
        String userChoose = keyboard.nextLine();
        if (userChoose.equals("f")){
            System.out.println("Please type your new first name");
            String newFirstName = getUserInput();
            userLogged.setFirstName(newFirstName);
            repository.updateUserDetails(userLogged);
        }else if (userChoose.equals("l")){
            System.out.println("Please enter your last name");
            String newLastName = getUserInput();
            userLogged.setLastname(newLastName);
            repository.updateUserDetails(userLogged);
        }else {
            System.out.println("Invalid input please try again");
            makeChangeInProfile(userLogged,repository);
        }
    }

    private static void userStatics(Users userLogged) {
        System.out.println("Number of correct answers you had :"+userLogged.getNumberOfRightAnswers()+"\n" +
                "Number of wrong answers you had :"+userLogged.getNumberOfWrongAnswers()+"\n" +
                "Number of quizzes you took :"+userLogged.getNumberOfQuizzesTokenByUser());
    }

    private static void CreateQuestion(Repository repository) {
        System.out.println("Please enter the question statement");
        String TheStatement = getUserInput();
        System.out.println("Please enter the first answer:");
        String answer1 = getUserInput();
        System.out.println("Please enter the second answer:");
        String answer2 = getUserInput();
        System.out.println("Please enter the first answer:");
        String answer3 = getUserInput();
        Answers answers = new Answers(answer1,answer2,answer3);
        repository.addQuestionToDB(new Question(TheStatement,answers,answer1));
    }

    private static void takeQuizz(Users userLogged,Repository repository) {
        int score =0;
        List<Question> questionsList = repository.getListOfQuestions();
        for (Question q: questionsList) {
            System.out.println(q.getTheQuestion());
            List<String> answers = new ArrayList<>();
            answers.add(q.getTheAnswers().getFirstAnswer());
            answers.add(q.getTheAnswers().getThe2ndAnswer());
            answers.add(q.getTheAnswers().getThe3thAnswer());
            Collections.shuffle(answers);
            for (String answer:answers ) {System.out.println(answer);}
            System.out.println("Please type or copy paste your answer");
            String answer = getUserInput();
            if (answer.equals(q.getCorrectAnswer())){
                System.out.println("correct answer");
                userLogged.setNumberOfRightAnswers(userLogged.getNumberOfRightAnswers()+1);
                score = score+1 ;
            }else {
                System.out.println("incorrect answer");
                userLogged.setNumberOfWrongAnswers(userLogged.getNumberOfWrongAnswers()+1);
            }
        }
        userLogged.setNumberOfQuizzesTokenByUser(userLogged.getNumberOfQuizzesTokenByUser() + 1);
        System.out.println("Your score is :"+score);
        repository.updateUserDetails(userLogged);
    }

    private static void CreateUser( Repository repository) {
        System.out.println(" Please enter the user Fisrt Name ");
        String userFName = getUserInput();
        System.out.println(" Please enter the user Last Name  ");
        String userLName = getUserInput();
        System.out.println("If the user is an administrator type (a) if the user in participant type (p) ");
        String userInput = getUserInput();
        System.out.println("Please choose your username ");
        String username = getUserInput();
        Users user = new Users(userFName,userLName,username);
        if (userInput.equals("a")){
            user.setUserRole(UserRole.Administrator);
        }if (userInput.equals("p")){
            user.setUserRole(UserRole.NormalUser);
        }
        System.out.println(" the user is created \n" +
                "user name is "+user.getFirstName()+ " " +user.getLastname()+"\n" +
                "user role is "+ user.getUserRole()+"\n");
        repository.addUserToDB(user);
    }

    private static String getUserInput() {
        Scanner keyboard = new Scanner(System.in);
        String TheStatement = keyboard.nextLine();
        return TheStatement;
    }
}
