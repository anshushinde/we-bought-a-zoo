import java.io.*;
import java.io.BufferedReader;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;

public class OurZoo {
    private String[] animals;
    private String[] newAnimals;
    private int tracker;

    public OurZoo() throws IOException {
        newAnimals = new String[10];

        try (InputStream file = OurZoo.class.getResourceAsStream("/zoo.txt"); BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                tracker++;
            }
            animals = new String[tracker];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream file = OurZoo.class.getResourceAsStream("/zoo.txt"); BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                animals[i] = line;
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public OurZoo(String[] list) {
        animals = list;
        for(int i = 0; i < animals.length; i++) {
            if(animals[i] != null) {
                tracker++;
            }
        }
        newAnimals = new String[10];
    }

    public static void main(String[] args) throws IOException {
        Scanner user = new Scanner(System.in);
        OurZoo z = new OurZoo();
        z.insertValue("dog", true);
        z.insertValue("frog", false);

        System.out.println(z.getNewAnimalsLogical());
//        z.deleteValue("bulette");
//        z.insertValue("goat", true);
//       z.printAnimals();
       //z.saveList();
//        System.out.println("0: Quit\n1: Get Value\n2: Set Value\n3: Insert Value\n4: Delete Value\n5: Print Array to Console\n6: Append New Elements to file\n7: Display Menu(optional)");
//        int response = user.nextInt();
//        menuSelect(response, user);

    }


    //menuSelect method
    public static void menuSelect(int menuOption, Scanner object) throws IOException {
        Scanner obj = object;
        OurZoo p = new OurZoo();
        while(menuOption != 0) {
            if (menuOption == 1) {
                System.out.println("How would you like to get value: through index press 'i', through first element with letter press 'l', or through nth element with first letter press 'n' ");
                obj.nextLine();
                String ans = obj.nextLine();
                if (ans.equals("i")) {
                    System.out.println("From what index do you want to get your value from?");
                    int index = obj.nextInt();
                    object.nextLine();
                    System.out.println("Your value is " + p.getValue(index));
                } else if (ans.equalsIgnoreCase("l")) {
                    System.out.println("What letter do you want your value to start with");
                    String letter = object.nextLine();
                   System.out.println("Your value is " + p.getValue(letter));
                } else if (ans.equalsIgnoreCase("n")) {
                    System.out.println("What letter do you want your value to start with");
                    String letter = object.nextLine();
                    System.out.println("What nth element should the element with desired letter be?");
                    int nth = object.nextInt();
                    object.nextLine();
                    System.out.println("Your value is " + p.getValue(letter, nth));
                }
            } else if (menuOption == 2) {
                System.out.println("What index do you want to set?");
                int set = object.nextInt();
                object.nextLine();
                System.out.println("What animal name do you want to set?");
                //object.nextLine();
                String name = object.nextLine();
                boolean finish = p.setValue(name, set);
                if (finish) {
                    System.out.println("Your animal is all set");
                } else {
                    System.out.println("Your animal was not set. your index was either too small or too big");
                }
            } else if (menuOption == 3) {
                System.out.println("What do you want to insert?");
                object.nextLine();
                String insert = object.nextLine();
                System.out.println("Do you want it to be in alphabetical order [yes press 1/no press 2]");

                int yn = object.nextInt();
                object.nextLine();
                if (yn == 1) {
                    //System.out.println("here");
                    p.insertValue(insert, true);
                    //p.printAnimals();
                } else if(yn==2) {
                    //System.out.println("here");
                    p.insertValue(insert, false);
                   // p.printAnimals();
                }
            } else if (menuOption == 4) {
                System.out.println("How do you want to delete a value: by its index (press 1), by its name (press 2), or by the first letter and nth element (press 3)?");
                int num = object.nextInt();
                if (num == 1) {
                    System.out.println("What index do you want to delete?");
                    int index = object.nextInt();
                    p.deleteValue(index);
                } else if (num == 2) {
                    System.out.println("What name do you want to delete");
                    object.nextLine();
                    String name = object.nextLine();
                    p.deleteValue(name);
                } else {
                    System.out.println("What first letter do you want to delete");
                    object.nextLine();
                    String letter = object.nextLine();
                    System.out.println("Which number of names with that first letter do you want to delete?");
                    int n = object.nextInt();
                    p.deleteValue(letter, n);
                }
            } else if (menuOption == 5) {
                p.printAnimals();
            } else if (menuOption == 6) {
                p.saveList();
            } else if (menuOption == 7) {
                System.out.println("0: Quit\n1: Get Value\n2: Set Value\n3: Insert Value\n4: Delete Value\n5: Print Array to Console\n6: Append New Elements to file\n7: Display Menu(optional)");
            }
            System.out.println("Press another option");
            menuOption = object.nextInt();
        }

        if(menuOption == 0){
            System.out.println("bye bye");
        }
    }


    public String getValue(int index) {
        Scanner input = new Scanner(System.in);
        while (index > tracker) {
            System.out.println("your index is out of bounds please enter a lower number");
            index = input.nextInt();

        }
        return animals[index];
    }

    public String getValue(String firstLetter) {
        for(int i = 0; i < animals.length; i++) {
            String name = animals[i];
            if(name.substring(0,1).equalsIgnoreCase(firstLetter)) {
                return name;
            }
        }
        return "No animal found";
    }

    public String getValue(String firstLetter, int n) {
        int counter = 0;
        for(int i = 0; i < animals.length; i++) {
            String name = animals[i];
            if((name.substring(0,1).equalsIgnoreCase(firstLetter))) {
                counter++;
                if(counter == n) {
                    return name;
                }
            }
        }
        return "No animal found";
    }

    public boolean setValue(String animalName, int index){
        if(index >= animals.length || index < 0){
            return false;
        } else {
            animals[index] = animalName;
            int k = 0;
            while(newAnimals[k] != null && k < newAnimals.length){
                k++;
            }
            newAnimals[k] = animalName;
            return true;
        }

    }

    public void insertValue(String animalName, boolean inAlaphbetical) {
        Scanner input = new Scanner(System.in);
        int index;
        if (inAlaphbetical == false) {
            String[] tempArr = new String[tracker + 1];
            System.out.println("At what index do you want to place the name");
            index = input.nextInt();

            if (animals.length == tracker) {

                for (int i = 0; i < index; i++) {
                    tempArr[i] = animals[i];
                }
                tempArr[index] = animalName;
                for (int i = index + 1; i <= animals.length; i++) {
                    tempArr[i] = animals[i -1];
                }

                animals = tempArr;
                tracker++;
                int k = 0;
                while(newAnimals[k] != null && k < 10){
                    k++;
                }
                newAnimals[k] = animalName;
            } else if (tracker < animals.length) { //check after creating delete method
                for (int i = 0; i < index; i++) {
                    tempArr[i] = animals[i];
                }
                tempArr[index] = animalName;
                for (int i = index + 1; i <= tracker; i++) {
                    tempArr[i] = animals[i - 1];
                }
                animals = tempArr;
                tracker++;
                int k = 0;
                while(newAnimals[k] != null && k < 10){
                    k++;
                }
                newAnimals[k] = animalName;
                //have to add into new animals array
            }

        } else {
            for (int i = 0; i < animals.length; i++) {
                for (int j = i + 1; j < animals.length; j++) {

                    if (animals[i].compareTo(animals[j]) > 0) {
                        String temp = animals[i];
                        animals[i] = animals[j];
                        animals[j] = temp;
                    }
                }

            }

            while(animals[0].equals("")) {
                for(int m = 0; m < animals.length -1; m++){
                    animals[m] = animals[m+1];
                }
                animals[animals.length - 1] = "";
            }

            String[] tempArr = new String[(getAnimalsLogical() + 1)];
            index = 0;
            boolean greater = true;
                  while (greater && animals[index].compareTo(animalName) <= 0 ) {
                      index++;
                      if(index == animals.length){
                          greater = false;
                      }
                  }

            //ind = index;

            if(!greater){
                for(int i = 0; i < animals.length;i++){
                    tempArr[i] = animals[i];
                }
                tempArr[getAnimalsLogical()] = animalName;
                animals = tempArr;
            }
            else if (animals.length == tracker) {
                for (int i = 0; i < index; i++) {
                    tempArr[i] = animals[i];

                }
                tempArr[index] = animalName;
                for (int i = index + 1; i < tempArr.length; i++) {
                    tempArr[i] = animals[i - 1];
                }
                if(index == tempArr.length -1){
                    tempArr[tempArr.length -1] = animals[index];
                }

                animals = tempArr;
                tracker++;
                int k = 0;
                while(newAnimals[k] != null && k < 10){
                    k++;
                }
                newAnimals[k] = animalName;

            } else if (tracker < animals.length) { //check after creating delete method
                for (int i = 0; i < index; i++) {
                    tempArr[i] = animals[i];
                }
                for (int i = tracker; i > index; i--) {
                    tempArr[i] = animals[i - 1];
                }

                tempArr[index] = animalName;

                animals = tempArr;
                tracker++;
                int k = 0;
                while(newAnimals[k] != null && k < 10){
                    k++;
                }
                newAnimals[k] = animalName;
                //have to add into new animals array
            }


        }
//        for(String i:animals){
//            System.out.println(i);
//        }
        System.out.println("Inserted");
    }

    public String deleteValue(int index) {
        String value = "";
        if(!(index < animals.length)){
            return "No animal at index";
        }
        if (index < animals.length) {
            value = animals[index];

            for (int i = index; i < animals.length - 1; i++) {
                animals[i] = animals[i + 1];
            }
            animals[animals.length - 1] = "";


            tracker--;
        }
        return value;
    }

    public String deleteValue(String name) {
        int index = 0;
        while(index < animals.length &&(!(animals[index].equalsIgnoreCase(name)))  ) {
            index++;
        }

        return deleteValue(index);
    }

    public String deleteValue(String letter, int n) {
        String save = "";
        int count = 0;
        for(int i = 0; i < animals.length; i++) {
            if(animals[i].length() > 0) {
                if (animals[i].substring(0, 1).equals(letter)) {
                    count++;
                    if (count == n) {
                        save = deleteValue(i);
                        return save;
                    }
                }
            }
        }
        return "No animal with that first letter in the list";
    }

    public void printAnimals() {
        Scanner user = new Scanner(System.in);
        System.out.println("How would you like you list printed: press 1 for alphabetical; 2 for reverse alphabetical; 3 for by length; 4 for alternating alphabetical");
        int answer = user.nextInt();

        if (answer == 1) {
            String[] temp = animals;
            for (int i = 0; i < temp.length; i++) {
                for (int j = i + 1; j < temp.length; j++) {

                    if (temp[i] != null && temp[j] != null) {
                        if (temp[i].compareTo(temp[j]) > 0) {
                            String temper = temp[i];
                            temp[i] = temp[j];
                            temp[j] = temper;
                        }
                    }
                }

            }
            for (String i : temp) {
                System.out.println(i);
            }
        } else if (answer == 2) {
            String[] temp = animals;
            for (int i = 0; i < temp.length; i++) {
                for (int j = i + 1; j < temp.length; j++) {

                    if (temp[i].compareTo(temp[j]) > 0) {
                        String temper = temp[i];
                        temp[i] = temp[j];
                        temp[j] = temper;
                    }
                }

            }
            for (int i = temp.length - 1; i >= 0; i--) {
                if (!(temp[i].equals("")) || !(temp[i].equals(null))) {
                    System.out.println(temp[i]);
                }
            }
        } else if (answer == 3) {
            String[] temp = animals;
            int counter = temp.length;
            while (counter > 0){
                int minIndex = 0;
                for (int i = 1; i < temp.length; i++) {
                    if (!(temp[i].equals("")) && !(temp[i].equals(null))) {
                        if (temp[minIndex].length() > temp[i].length()) {
                            minIndex = i;
                        }
                    }
                }
                System.out.println(temp[minIndex]);
                for(int m = minIndex; m < temp.length -1 ; m++){
                    temp[m] = temp[m+1];
                }
                temp[temp.length -1] = "";
                counter--;
            }

        }
        else{
            String[] temp = animals;
            for (int i = 0; i < temp.length; i++) {
                for (int j = i + 1; j < temp.length; j++) {

                    if (temp[i].compareTo(temp[j]) > 0) {
                        String temper = temp[i];
                        temp[i] = temp[j];
                        temp[j] = temper;
                    }
                }

            }
            while(temp[0].equals("")) {
                for(int m = 0; m < temp.length -1; m++){
                    temp[m] = temp[m+1];
                }
                temp[temp.length - 1] = "";
            }

            int first = 0;
            int last = temp.length -1;

            while((first != last) && (last > first) ) {
                if (!(temp[first].equals("")) && !(temp[first].equals(null))){
                    System.out.println(temp[first]);
                    if (!(temp[last].equals("")) && !(temp[last].equals(null))) {
                        System.out.println(temp[last]);
                    }else{
                        System.out.println(temp[last-1]);
                        last--;
                    }

                } else{
                    first--;
                }
                first++;
                last--;

            }
        }
    }

    public boolean saveList() {
        boolean finish = true;
        try(FileWriter file = new FileWriter("zoo.txt", true); BufferedWriter writer = new BufferedWriter(file)){

            for(int i = 0; i < newAnimals.length; i++) {
                if (newAnimals[i] != null) {
                    writer.write(newAnimals[i]);
                    writer.newLine();
                }
            }
            for(int i = 0; i < newAnimals.length; i++){
                if(newAnimals[i] != null) {
                    newAnimals[i] = null;
                }
            }
            return finish;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int getAnimalsLogical() {
        return tracker;
    }

    public int getNewAnimalsLogical() {
        int tracker = 0;

        for(int i =0; i < newAnimals.length; i++) {
            if(newAnimals[i] != null) {
                tracker++;
            }
        }

        return tracker;
    }

    public void printNewAnimals() {
        for(String i: newAnimals){
            System.out.println(i);
        }

    }
}