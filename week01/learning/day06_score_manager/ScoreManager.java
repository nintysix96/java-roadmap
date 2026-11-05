

import java.util.Scanner;

public class ScoreManager {
    static final int MAX = 10;
    static String[] names = new String[MAX];
    static int[] scores = new int[MAX];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);

    static void addStudent(){
        if(count >= MAX){
            System.out.println("List is full");
            return;
        }

        System.out.println("Please enter your name");
        String name = sc.nextLine().trim();

        if(name.isEmpty()) {
            System.out.println("Name cannot be empty");
            return;
        }

        if(exists(name)){
            System.out.println("Student already exists");
            return;
        }

        int score = readScore();
        names[count] = name;
        scores[count] = score;
        count++;
        System.out.println("Added:" + name + " (" + score +")");

    }
    static void printAll(){
        if(count == 0){
            System.out.println("No records");
            return;
        }
        System.out.printf("%-4s %-10s %5s %7s%n","No","Name","Score","Result");
        for(int i = 0; i < count ; i++){
            String result = scores[i] >= 60 ? "PASS" : "FAIL";
            System.out.printf("%-4d %-10s %5d %7s%n",i+1,names[i],scores[i],result);
        }

    }
    static void statistics(){
        if(count == 0){
            System.out.println("No records");
            return;
        }

        int total = 0;
        int max = scores[0];
        int min = scores[0];

        for(int i = 0;i<count ;i++){
            total += scores[i];
            if(max < scores[i]) max = scores[i];
            if (min > scores[i]) min = scores[i];
        }

        double avg = (double) total / count;

        System.out.printf("Total: %d%n",total);
        System.out.printf("Average: %.1f%n", avg);
        System.out.printf("Max: %d%n", max);
        System.out.printf("Min: %d%n", min);
    }

    static void findByName(){
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        for(int i = 0; i< count;i++){
            if(names[i].equals(name)){
                String result = scores[i] >= 60 ? "PASS" : "FAIL";
                System.out.printf("%-4d %-10s %5d %7s%n",i+1,names[i],scores[i],result);
                return;
            }
        }
        System.out.println("Not found");
    }

    static int readScore(){
        System.out.println("Please enter your score(0-100)");
        while(true){
            if(!sc.hasNextInt()){
                System.out.println("Not a number");
                sc.next();
                continue;
            }

            int score = sc.nextInt();
            if(score < 0 || score > 100){
                System.out.println("Score must be 0-100");
                continue;
            }
            return score;
        }
    }
    static boolean exists(String name){
        for(int i = 0;i < count;i++){
            if(names[i].equals(name)){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        while(true){
            System.out.println("""
                =====Score Manager=====
                1.Add student;
                2.List all
                3.Statistics
                4.Search by name
                5.Exit
                Choose
                """);
            int num = sc.nextInt();
            sc.nextLine();
            switch(num){
                case 1 : addStudent();break;
                case 2 : printAll();break;
                case 3 : statistics();break;
                case 4 : findByName();break;
                case 5 : return ;
                default:System.out.println("Invalid choice");
            }
        }
    }
}
