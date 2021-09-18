package com.example.couldthisbelve;

import java.util.Random;

public class lovecalculator {
    public static void main(String[] args) {
        System.out.println("your love score is "+ifYouHardMyLove("mr x","mr y"));

    }
    public static int ifYouHardMyLove(String yourName,String theirName){
        Random random=new Random();
        int loveScore =random.nextInt(101);

        if(loveScore>80)
        {
            System.out.println("Love for each other like ");
        }
        else if(loveScore>50)
        {
            System.out.println("your love is quitee");
        }
        else if (loveScore>20)
        {
            System.out.println("no love passible");
        }
        return loveScore;
    }
}