import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctAnswer;
    private int timeLimit; // in seconds

    public QuizQuestion(String question, String[] options, int correctAnswer, int timeLimit) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.timeLimit = timeLimit;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
}

public class Quiz {
    private QuizQuestion[] questions;
    private int score;
    private int currentQuestion;

    public Quiz(QuizQuestion[] questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestion = 0;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (currentQuestion = 0; currentQuestion < questions.length; currentQuestion++) {
            QuizQuestion question = questions[currentQuestion];
            System.out.println(question.getQuestion());
            for (int i = 0; i < question.getOptions().length; i++) {
                System.out.println((i + 1) + ". " + question.getOptions()[i]);
            }

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up!");
                    timer.cancel();
                }
            }, question.getTimeLimit() * 1000);

            System.out.print("Enter your answer (1-" + question.getOptions().length + "): ");
            int userAnswer = scanner.nextInt();

            if (userAnswer == question.getCorrectAnswer()) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is " + question.getCorrectAnswer());
            }

            System.out.println();
        }

        displayResults();
    }

    private void displayResults() {
        System.out.println("Quiz Results:");
        System.out.println("Score: " + score + "/" + questions.length);
        System.out.println("Correct answers: ");
        for (int i = 0; i < questions.length; i++) {
            if (i < score) {
                System.out.print((i + 1) + ". Correct, ");
            } else {
                System.out.print((i + 1) + ". Incorrect, ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QuizQuestion[] questions = new QuizQuestion[] {
            new QuizQuestion("What is the capital of France?", new String[] {"Paris", "London", "Berlin", "Rome"}, 1, 30),
            new QuizQuestion("What is the largest planet in our solar system?", new String[] {"Earth", "Saturn", "Jupiter", "Uranus"}, 3, 30),
            new QuizQuestion("What is the smallest country in the world?", new String[] {"Vatican City", "Monaco", "Nauru", "Tuvalu"}, 1, 30),
        };

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}
