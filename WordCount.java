/*
 * This is the class that contain the String element and the count of the string
 */
public class WordCount {
    private String word;                                    //the String word
    private int count;                                      //the number of times the word is searched

    public WordCount(){
        word = null;
        count = 0;
    }

    /*
     * constructor that takes a word as the element, and set the count to 0
     *
     * @param String word
     */
    public WordCount(String data){
        word = data;
        count = 0;
    }

    /*
     * get the word
     *
     * @return String word
     */
    public String getWord() {
        return word;
    }

    /*
     * get the number of presences
     *
     * @return int count
     */
    public int getCount() {
        return count;
    }

    /*
     * add the count of the word by 1
     */
    public void addCount(){
        count++;
    }

    /*
     * gets the private information stored in the WordCount
     *
     * @return String that contains the String word followed by the count
     */
    public String toString(){
        return word+" "+count;
    }

    /*
     * This method compares the calling object to the argument (wc in this example).
     * The method returns the difference in the counts
     *
     * @param WordCount that you want you compare with
     * @return different between the count of calling object and argument
     */
    public int compareTo(WordCount wc){
        return count - wc.getCount();
    }

    /*
     * Compare the word of calling object and the argument
     *
     * @param target WordCount that you want to compare with
     * @return true if the words are the same, return false otherwise
     */
    public boolean equals(WordCount wc){
        return word.equals(wc.getWord());
    }

    /*
     * Count setter
     *
     * @param int number of count you want to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /*
     * Word setter
     *
     * @param String word that you want to set
     */
    public void setWord(String word){
        this.word = word;
    }
}
