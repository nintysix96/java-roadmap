package day08_oop_basic;

public class Student {
    public String name;
    public int score;

    public void introduce(){
        System.out.println("I am " + this.name +", score = "+this.score);
    }

    public boolean isPass(){
        return score >= 60;
    }

    public String getInfo(){
        return this.name + "("+this.score+")";
    }
}

