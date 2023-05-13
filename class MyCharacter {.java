import java.util.Random;
import java.util.Scanner;

class MyCharacter {

static Scanner s = new Scanner(System.in);
private String name;
private int age = 18;
private int physical;
private int mental;
private int charisma;
private int physMod;
private int mentMod;
private int charMod;
private String story;

// MyCharacter will initialize after given the character's name
public MyCharacter(String name){
    this.name = name;
}

// statGeneration will generate stats. Input is used to grab stats from the user, or call the characterStats method.
public void statGeneration(){
    System.out.println("\n1) I have rolled up my own stats.\n2) I would like my stats generated for me.\n------------------------------------------");
    int i = s.nextInt();
    while (i != 1 && i != 2){
        System.out.println("\nPlease enter either 1 or 2.");
        i = s.nextInt();
    }
    switch (i) {
        case 1: System.out.println("\n\nPlease enter your character's Physical score: ");
                this.physical = s.nextInt();
                System.out.println("\n\nPlease enter your character's Mental score: ");
                this.mental = s.nextInt();
                System.out.println("\n\nPlease enter your character's Charisma score: ");
                this.charisma = s.nextInt();
            break;
        case 2: System.out.println("\nWe will now generate your stats for you.");
                this.characterStats();
            break;
    }
}

// characterStats rolls two six sided die and returns the value. charStats array is never used, but it holds the physical stat in charStat[0], mental in charStats[1], and so on.
public int[] characterStats(){
    Random r = new Random();
    physical = r.nextInt(6) + r.nextInt(6) + 2;
    mental = r.nextInt(6) + r.nextInt(6) + 2;
    charisma = r.nextInt(6) + r.nextInt(6) + 2;
    int[] charStats = {physical, mental, charisma};
    return charStats;
}

// The Getters and Setters are not used, but are here for examples
public int getPhysMod(){
    physMod = Math.floorDiv(physical - 6, 2);
    return physMod;
}

public int getMentMod(){
    mentMod = Math.floorDiv(mental - 6, 2);
    return mentMod;
}

public int getCharMod(){
    charMod = Math.floorDiv(charisma - 6, 2);
    return charMod;
}

public int getPhys(){
    return this.physical;
}

public void setPhys(int x){
    this.physical = x;
}

public int getMental(){
    return this.mental;
}

public void setMental(int x){
    this.mental = x;
}

public int getCharisma(){
    return this.charisma;
}

public void setCharisma(int x){
    this.charisma = x;
}

// getStats displays the stats in an easy to digest format
public void getStats(){
    System.out.println("\n" + this.name.toString());
    for (int nameCount = 0; nameCount <= name.length(); nameCount++){
        System.out.print("-");
    }
    System.out.println("\nPhysical: " + this.physical + "\nMental: " + this.mental + "\nCharisma: " + this.charisma + "\n");
}

// advanceLife sends the user to either advEducation or lifePath. These are the methods that do most of the work
public void advanceLife(){
    if (story == null){
        story = "\nThis is the life of " + this.name +"\n";
        int sLength = story.length();
        for (int count = 1; count <= sLength; count++){
            story += "-";
        }
        story += "\n";
    }

    if (age == 18){
        advEducation();
    }
    
    lifePath();

}

// If the user decides to go through advanced education, a dice roll is made and has their mental modifier applied to it. If it is over 8, they pass.
public void advEducation(){
    Random r = new Random();
    System.out.println("Would you like to send your character to advanced education?\n1) Yes    2) No");
    String input = s.next();
    while (!input.equals("1") && !input.equals("2")){
        System.out.println("Please enter either 1 or 2");
        input = s.next();
    }
    switch (input) {
        case "1": if (r.nextInt(6) + r.nextInt(6) + getMentMod() >= 8){
            age += 4;
            System.out.println("\n" + name + " has succeeded in their pursuit of higher education.");
            story += ("At age " + age + ", " + name + " has graduated their higher education.\n");
            mental += 2;
            break;
        }
                else {
                    System.out.println("\n" + name + " has failed in their pursuit of higher education.");
                    story += (name + " has flunked out of their higher education.\n");
                    break;
                }
        case "2": 
            story += (name + " has decided to skip higher education.\n");
            break;
    }
}

// lifePath accesses the different life paths. The user selects an option from the list, and sent to the correct path.
public void lifePath(){
    int path;
    System.out.println("\nWhat sort of life would you like your character to live?\n1) Adventurous     2) Studious     3) Charismatic     4) Simple\n");
    String input = s.next();
    while (true){
    while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")){
        System.out.println("Please enter either 1, 2, 3, or 4");
        input = s.next();
    }
    int answer = Integer.parseInt(input);
    switch (answer){
        case 1: path = 1;
            callPath(path);
            break;
        case 2: path = 2;
            callPath(path);
            break;
        case 3: path = 3;
            callPath(path);
            break;    
        case 4: path = 4;
            callPath(path);   
            break;
    }
    path = answer;

    System.out.println("Would you like to advance on your path? Start a new one? Or are you finished with your character?\n1) Advance     2) Start New     3) Finished");
    String continueInput = s.next();
    while (!continueInput.equals("1") && !continueInput.equals("2") && !continueInput.equals("3")){
        System.out.println("Please enter either 1, 2, or 3");
        continueInput = s.next();
    }
    int pathAnswer = Integer.parseInt(continueInput);
    switch (pathAnswer){
        case 1:break;
        case 2:System.out.println("\nWhat sort of life would you like your character to live?\n1) Adventurous     2) Studious     3) Charismatic     4) Simple\n");
            input = s.next();
            while (!input.equals("1") && !input.equals("2") && !input.equals("3" ) && !input.equals("4")){
            System.out.println("Please enter either 1, 2, 3, or 4");
            input = s.next();
    }
            pathAnswer = Integer.parseInt(input);
            break;
        case 3:
        s.close(); 
        return;
    }

}
}

// callPath calls the methods for the different life paths
public void callPath(int x){
    switch (x){
        case 1: lifePathOne();
        break;
        case 2: lifePathTwo();
        break;
        case 3: lifePathThree();
        break;
        case 4: lifePathFour();
        break;
    }
}

// The life paths are all fairly similar. They give the user a slightly more detailed flavor text, but the general idea is added to the story to be viewed for later.
public void lifePathOne(){
    Random r = new Random();
    int die = r.nextInt(6+1);
    switch (die){
        case 1: System.out.println("\nYou experienced an accident during your adventure, that has limited your physical abilities!\n");
        physical--;
        story += "At age " + age +", " + name +" suffered a debilitating accident.\n";
        break;
        case 2: System.out.println("\nYou were blamed for something you didn't do, and it has affected people's view of you.\n");
        charisma--;
        story += "At age " + age +", " + name +" was falsey accused.\n";
        break;
        case 3: System.out.println("\nYou were able to reconnect with an old friend who was able to help you on your way\n");
        story += "At age " + age +", " + name +" reconnected with an old friend.\n";
        break;
        case 4: System.out.println("\nYou found your way into a new group of people\n");
        story += "At age " + age +", " + name +" found a new group to join.\n";
        break;
        case 5: System.out.println("\nYou gained fame from one of your actions\n");
        story += "At age " + age +", " + name +" gained fame from one of their actions.\n";
        charisma++;
        break;
        case 6: System.out.println("\nYou managed to get through an incredibly challenging ordeal!\n");
        story += "At age " + age +", " + name +" got through a challenging ordeal.\n";
        physical++;
        break;
    }
    age+=4;
}

public void lifePathTwo(){
    Random r = new Random();
    int die = r.nextInt(6+1);
    switch (die){
        case 1: System.out.println("\nYou lost a lot of your studies, causing your research to fall behind!\n");
        mental--;
        story += "At age " + age +", " + name +" lost their research.\n";
        break;
        case 2: System.out.println("\nYour recent studies have not been looked upon kindly by the public.\n");
        charisma--;
        story += "At age " + age +", " + name +" created controversy with their research.\n";
        break;
        case 3: System.out.println("\nYou were contracted to work on a famous group's research.\n");
        story += "At age " + age +", " + name +" worked with a famous group.\n";
        break;
        case 4: System.out.println("\nYou find new friends through your research.\n");
        story += "At age " + age +", " + name +" found people through their research.\n";
        break;
        case 5: System.out.println("\nYou gained from one of your recent studies.\n");
        story += "At age " + age +", " + name +" gained fame from their research.\n";
        charisma++;
        break;
        case 6: System.out.println("\nYou had an incredible breakthrough! Your studies will be talked about for sometime.\n");
        story += "At age " + age +", " + name +"\'s studies lead to a major breakthrough.\n";
        mental++;
        break;
    }
    age+=4;
}

public void lifePathThree(){
    Random r = new Random();
    int die = r.nextInt(6+1);
    switch (die){
        case 1: System.out.println("\nYou were involved in a scandal!\n");
        charisma--;
        story += "At age " + age +", " + name +" was exposed in a scandal.\n";
        break;
        case 2: System.out.println("\nYou were invited to a battle of wits and embarrassed yourself during it.\n");
        mental--;
        story += "At age " + age +", " + name +" had their intellgence berated.\n";
        break;
        case 3: System.out.println("\nYou go on a long trip, finding many new friends.\n");
        story += "At age " + age +", " + name +" took a long trip, and gained many friends.\n";
        break;
        case 4: System.out.println("\nYou create a following for yourself.\n");
        story += "At age " + age +", " + name +" gained many followers.\n";
        break;
        case 5: System.out.println("\nYour new way of life has encouraged a positive growth in your physique.\n");
        story += "At age " + age +", " + name +" changed their way of life, and it had a positive effect.\n";
        physical++;
        break;
        case 6: System.out.println("\nYou gained fame for winning a prestigious prize!\n");
        story += "At age " + age +", " + name +" won a prestigious prize.\n";
        charisma++;
        break;
    }
    age+=4;
}

public void lifePathFour(){
    Random r = new Random();
    int die = r.nextInt(6+1);
    switch (die){
        case 1: System.out.println("\nYou have become injured from an accident!\n");
        physical--;
        story += "At age " + age +", " + name +" became injured from an accident.\n";
        break;
        case 2: System.out.println("\nYou had a major relationship in your life come to an end.\n");
        charisma--;
        story += "At age " + age +", " + name +" lost someone who was close to them.\n";
        break;
        case 3: System.out.println("\nYou had a fortunate string of good luck!\n");
        story += "At age " + age +", " + name +" experienced a vast amount of luck.\n";
        break;
        case 4: System.out.println("\nYou found a new friend.\n");
        story += "At age " + age +", " + name +" found a new friend.\n";
        break;
        case 5: System.out.println("\nYou start a new journal of your life after a traumatic event.\n");
        story += "At age " + age +", " + name +" experienced a traumatic event, and started keeping a journal.\n";
        mental++;
        break;
        case 6: System.out.println("\nYou found your way into a massive fortune!\n");
        story += "At age " + age +", " + name +" came into a large fortune.\n";
        charisma++;
        break;
    }
    age+=4;
}

// This returns the story String to be viewed.
public String lifeStory(){
    return story;
}

}