public class LetterInventory {

    public int[] lettercounter;
    public int inventorysize;

    //an inventory (a count) of the alphabetic letters in the given string,
    //ignoring the case of letters and ignoring any non-alphabetic characters
    public LetterInventory(String letter) {
        lettercounter = new int[26];
        letter = letter.toLowerCase();
        char[] dataArr = letter.toCharArray();
        inventorysize = 0;
        for (char c : dataArr) {
            if (Character.isAlphabetic(c)) {
                int indexval = (int) c - 'a';
                lettercounter[indexval] += 1;
                inventorysize++;
            }
        }
    }

    //Returns a count of how many of this letter are in the inventory
    //Letter might be lowercase or uppercase
    public int get(char letter) {
        letter = Character.toLowerCase(letter);
        if (!Character.isAlphabetic(letter)) {
            throw new IllegalArgumentException();
        }
        int index = (int) letter - 'a';
        return lettercounter[index];
    }

    //Sets the count for the given letter to the given value.
    //Letter might be lowercase or uppercase.
    public void set(char letter, int value) {
        if (!Character.isAlphabetic(letter)) {
            throw new IllegalArgumentException();
        }
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        int indexval = (int) Character.toLowerCase(letter) - 'a';
        // Since the letter at a value is changing, the inventory size value must also change that is why
        //we are finding the change between the old and new values to see the new inventory size
        int previous=lettercounter[indexval];
        lettercounter[indexval] = value;
        int change_in_value=value-previous;
        inventorysize+=change_in_value;
    }

    //Returns the sum of all of the counts in this inventory.
    public int size(){
        return inventorysize;
    }

    //Returns true if this inventory is empty (all counts are 0).
    public boolean isEmpty() {
        if(inventorysize==0){
            return true;
        } else{
            return false;
        }
    }

    //Returns a String representation of the inventory with the letters all in
    //lowercase and in sorted order and surrounded by square brackets. The
    //number of occurrences of each letter should match its count in the inventory
    public String toString(){
        String results_printed="[";
        for(int i=0; i<26; i++){
            for(int j=0; j<lettercounter[i]; j++){
                results_printed+= (char) (i+ 'a');
            }
        }
        return results_printed + "]";
    }

    //Constructs and returns a new LetterInventory object that represents the sum
    //of this letter inventory and the other given LetterInventory. The counts for
    //each letter should be added together. The two LetterInventory objects being
    //added together (this and other) should not be changed by this method
    public LetterInventory add(LetterInventory other){
        LetterInventory total_sum = new LetterInventory("");
        for(int i=0; i<26; i++){
            char test=(char) (i+'a');
            int value=lettercounter[i]+other.get(test);
            total_sum.set(test, value);
        }
        return total_sum;
    }
    
    //Constructs and returns a new LetterInventory object that represents the result
    //of subtracting the other inventory from this inventory
    public LetterInventory subtract(LetterInventory other){
        LetterInventory total_difference = new LetterInventory("");
        for(int i=0; i<26; i++){
            char test=(char) (i+'a');
            int value=lettercounter[i]-other.get(test);
            if(value<0){
                return null;
            }
            total_difference.set(test, value);
        }
        return total_difference;
    }
}
