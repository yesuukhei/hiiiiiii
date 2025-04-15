
import java.util.*;
import java.io.*;

public class Flashapp {
    private static List<Flashcard> flashcards = new ArrayList<>();
    
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String... args) { 
        for(String arg : args) {
            if(arg.equals("--help")) {
                showHelp();
                return;
            }
        }

        if (args.length == 0) {
            System.out.println("Usage: flashcard <cards-file> [options]");
            return;
        }
        System.out.println(Arrays.toString(args));
        String filename = args[0];
        String order = "random";
        int repetitions = 1;
        boolean invertCards = false;

        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "--help":
                    showHelp();
                    return;
                case "--order":
                    if (i + 1 < args.length) order = args[++i];
                    break;
                case "--repetitions":
                    if (i + 1 < args.length) repetitions = Integer.parseInt(args[++i]);
                    break;
                case "--invertCards":
                    invertCards = true;
                    break;
                default:
                    System.out.println("Unknown option: " + args[i]);
                    return;
            }

            loadFlashcards(filename);
            sortFlashcards(order);
            runQuiz(order, repetitions, invertCards);
        }
    
       
        
    }
    private static void sortFlashcards(String order) {
        switch (order) {
            case "random":
                Collections.shuffle(flashcards);
                break;
            case "worst-first":
                flashcards.sort(Comparator.comparingInt(Flashcard::getIncorrectAttempts).reversed());
                break;
            case "recent-mistakes-first":
                flashcards.sort(Comparator.comparingLong(Flashcard::getLastIncorrectTimestamp).reversed());
                break;
            default:
                System.out.println("Invalid order option. Using random order.");
                Collections.shuffle(flashcards);
        }
    }

    private static void showHelp() {
        System.out.println("----Welcome to my MyFlashCard application-----");
        System.out.println("Songoltuud:");
        System.out.println(" --help                 Tuslamjiin medeelel haruulah");
        System.out.println(" --order <daraalal>     Asuulga haragdah daraalal uurchluh");
        System.out.println("                        random: Sanamsargu daraalal (default)");
        System.out.println("                        worst-first: Hamgiin ih aldaa hiisen asuudliig ehend ni haruulah");
        System.out.println("                        recent-mistakes-first: Suuld aldaa hiisen asuudliig turund ni haruulah");
        System.out.println(" --repetitions <too>    Kartyg heden udaa zow hariult hiih heregteig togtoolgoh");
        System.out.println(" --invertCards          Asuulgiin hariultuudiig solih");
    }

    private static void runQuiz(String order, int repetitions, boolean invertCards) {
        System.out.println("Starting quiz with order: " + order + ", repetitions: " + repetitions + ", invert: " + invertCards);
        sortFlashcards(order);
        // 1. Handle ordering
        List<Flashcard> quizCards = new ArrayList<>(flashcards);
        if ("random".equals(order)) {
            Collections.shuffle(quizCards);
        } 
        for (int i = 0; i < repetitions; i++) {
            for (Flashcard card : flashcards) {
           
                String question = invertCards ? card.getAnswer() : card.getQuestion();
                String correctAnswer = invertCards ? card.getQuestion() : card.getAnswer();
                
                System.out.println("Asuult: " + question);
                String answer = scanner.nextLine();                
                if (answer.equalsIgnoreCase(correctAnswer)) {
                    System.out.println(" Zuw taalaa! ");
                } else {
                    System.out.println(" Buruu taalaa! Zuw hariult: " + correctAnswer);
                }
            }
        }
    }


    // file unshih
    private static void loadFlashcards(String filename) {
       try {
            File myfile = new File( filename);
            // File myfile = new File("./mycards.txt");
            Scanner myReader = new Scanner(myfile);  
            while(myReader.hasNextLine() ) {
                String line = myReader.nextLine();
                String[] parts = line.split("::");
                if(parts.length == 2) {
                    flashcards.add(new Flashcard(parts[0], parts[1]));
                }
            }
            myReader.close();
       } catch(IOException e){
        System.out.println("aldaa garaa: " + e.getMessage());
   
       }
    }

}
