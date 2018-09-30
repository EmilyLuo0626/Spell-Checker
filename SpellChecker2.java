/*
 *This program improves the original project SpellChecker in how to sort the list of data.
 * The SpellChecker gets two file names from the user in the terminal, first being dictionary and second is text
 * Then, the program will give the total number of words, the number of unique words, the number of mispelled words,
 * the amount of running time, and return a modified dictionary file.
 *
 * The program contains two three ways of sorting: simple ("-s"), transpose("-t"), and count("-c"). You can access each
 * by add "- " in front of the file names
 *
 * @author Xuqing(Emily) Luo
 *
 */

import java.io.*;
import java.util.Scanner;
import java.io.FileWriter;

public class SpellChecker2 {
    /*
     * main method here
     */
    public static void main(String[] args){
        final long startTime = System.currentTimeMillis();     //start time of the program

        WCLinkedList dictList = null;                          //list of dictionary
        WCLinkedList misspelledList = null;                    //list of mispelled words
        WCLinkedList uniqueList = new WCLinkedList();          //list of unique words
        int numWords = 0;                                      //total number of words
        int index = 0;                                         //index of the arguments in args[]


        /*
         * read the argument in args[], and check for sort method
         */
        if(args.length<2||args.length>3)
            throw new RuntimeException("Incorrect number of file names");
        else if(args[0].equals("-s")){
            dictList = new WCLinkedList();
            misspelledList = new WCLinkedList();
            index++;
        }
        else if(args[0].equals("-t")){
            dictList = new TWCLinkedList();
            misspelledList = new TWCLinkedList();
            index++;
        }
        else if(args[0].equals("-c")){
            dictList = new CWCLinkedList();
            misspelledList = new CWCLinkedList();
            index++;
        }
        else if(args.length==2){
            dictList = new WCLinkedList();
            misspelledList = new WCLinkedList();
        }
        else
            throw new RuntimeException("Incorrect dash name");


        /*
         * read the dictionary file into dictionary list
         */
        try {
            File dictionary = new File(args[index]);
            index++;
            Scanner sc1 = new Scanner(dictionary);

            while (sc1.hasNextLine())
                dictList.addLast(sc1.nextLine());

        }
        catch(FileNotFoundException e){
            System.out.println("dictionary not found");
        }

        /*
         * read the text file into mispelled word list, unique word list
         */
        try {
            File text = new File(args[index]);
            Scanner sc2 = new Scanner(text);

            String line = null, word=null;
            int start, end;

            /*
             * read the data in text file
             */
            while (sc2.hasNextLine()) {
                //get the line
                line = sc2.nextLine();

                //if empty string
                if(line.length()==0) {
                    continue;
                }

                start=-1;                                           //the start index of a word

                //go over every character in the word
                for (int i = 0; i <= line.length(); i++) {
                    word = null;
                    /*
                     * process the word
                     */
                    if(i==line.length()){
                        if(Character.isDigit(line.charAt(i-1)) || Character.isLetter(line.charAt(i-1))) {
                            word = line.substring(start + 1);
                        }
                    }
                    else if ((i>0) && !Character.isLetter(line.charAt(i)) && !Character.isDigit(line.charAt(i))) {
                        end = i;
                        if(end-start>1){
                            word = line.substring(start+1,end);
                            start = end;
                        }
                        else{
                            start = end;
                        }
                    }

                    /*
                     * add the word to the list
                     */
                    if(word!=null){
                        word = word.toLowerCase();
                        if(!dictList.contains(word) && !misspelledList.contains(word))
                            misspelledList.addLast(word);
                        if(!uniqueList.contains(word))
                            uniqueList.addLast(word);
                        numWords++;
                    }


                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("text not found");
        }

        final long endTime = System.currentTimeMillis();                    //ending time of the program

        System.out.print("words: "+numWords);
        System.out.print(", unique words: "+uniqueList.size());
        System.out.println(", misspelled: "+misspelledList.size());
        System.out.println("Time to perform check: "+(endTime-startTime)+" msec");
        System.out.println("managed dictionary written to: final."+args[index-1]);


        /*
         * create a new file, and write the data of modified dictionary list into it
         */
        try{
            FileWriter final_dict = new FileWriter("final."+args[index-1]);
            for(WordCount w:dictList){
                final_dict.write(w.toString()+"\n");
            }
            final_dict.close();
        }
        catch (IOException e){
            System.out.println("final.dictionary not created");
        }


    }

}


