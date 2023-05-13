import java.util.Scanner;

class CharacterGenerator{

public static void main(String[] args) {
    System.out.println("\nHello!\nPlease enter a name for your character!");
    Scanner input = new Scanner(System.in);
    String name = input.nextLine();
    MyCharacter myCharacter = new MyCharacter(name);
    System.out.println("\nThis program will help you create your Table Top RPG character on a basic level. Use what happens as inspiration!\n\nFirst, we need to determine what your starting stats are.\nThese stats are based on a 2d6 system. Have you rolled your own, or would you like us to roll them for you?");
    myCharacter.statGeneration();
    myCharacter.getStats();
    myCharacter.advanceLife();
    System.out.println(myCharacter.lifeStory());
    input.close();
    
}


}