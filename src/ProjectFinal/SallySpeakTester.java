package ProjectFinal;

import java.util.Scanner;

public class SallySpeakTester {
    public static void main(String[] Args)
    {
        System.out.print("SallySpeak is a program adapted from a previous lab. " + "\n" +
                "The initial premise is that a dog named Sally uses a specific language to communicate. " + "\n" +
                "First a few exmaples and then the user will be prompted to write a sentence in the language." + "\n" + "\n");

        String firstTest = ("earperk tailwag tailwag tailwag bark whimper earperk bark growl earperk tailwag");
        String secondTest = ("Hello my name is Jeff.");
        String thirdTest = ("bark whimper earperk");

        System.out.print("Is this sentence in SallySpeak?" + "\n");
        System.out.print(firstTest);

        SallySpeak first = new SallySpeak(firstTest);
        if (first.isLegal()) System.out.print("\n" +"\n" + "Yes it is!");

        System.out.print("\n" +"\n" + "What about this sentence?" + "\n");
        System.out.print(secondTest);

        SallySpeak second = new SallySpeak(secondTest);
        if (second.isLegal()) System.out.print("\n" +"\n" + "Yes, it is!");
        else System.out.print("\n" +"\n" + "No, it is not!");

        System.out.print("\n" +"\n" + "Ok, last test." + "\n");
        System.out.print(thirdTest);

        SallySpeak third = new SallySpeak(thirdTest);
        if(third.isLegal()) System.out.print("\n" +"\n" + "Yes, it is!");
        else System.out.print("\n" +"\n" + "No, it is not!");

        System.out.print("\n" +"\n" + "Now its your turn. Type a sentence in SallySpeak: ");
        String userTest = (new Scanner(System.in)).nextLine();
        SallySpeak fourth = new SallySpeak(userTest);

        if(fourth.isLegal()) System.out.print("\n" + "Yes, it is!");
        else System.out.print("\n" + "No, it is not!");
    }
}
