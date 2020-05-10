import java.io.*;
import java.util.*;
import java.lang.*;
public class PersonalityNew {
    public static int[] categories_len = {10, 20, 20, 20};
    public static final String[][] DIMENSIONOPTIONS = {{"E", "I"}, {"S", "N"}, {"T", "F"}, {"J", "P"}};
    public static void main(String[] args) throws FileNotFoundException{
        instructions();
        Scanner console = new Scanner (System.in);
        Scanner input_file = new Scanner(file_instructions(console, "input"));
        //Scanner input_file = new Scanner(new File(console.next()));
        //file_instructions(console, "output");
        PrintStream output_file = new PrintStream(file_instructions(console, "output"));
        while(input_file.hasNextLine()) {
            String patient_name = input_file.nextLine();
            String answers = input_file.nextLine();
            int []categories_b_values= tally_b(answers.toUpperCase());
            // System.out.println(Arrays.toString(categories_b_values));
            int []categories_b_percentages = percentage_calc(categories_b_values, categories_len);
            String personality_type=personality_gen(categories_b_percentages);
            output_file.print(patient_name + ": " + Arrays.toString(categories_b_percentages) + " = " + personality_type + "\n");
            //String final_personalitystr=printing_personality(personality_type);
            //System.out.print(final_personalitystr + "\n");

        }
    }

    public static void instructions(){
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter. It converts the");
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a");
        System.out.println("four-letter personality type.");
    }
    public static File file_instructions(Scanner a, String file_type){
        System.out.print(file_type + " file name? ");
        String fileName = a.nextLine();
        File given_file = new File (fileName);
        return given_file;
    }
    public static int[] tally_b(String single_entry_list){
        int[] cat_b_nums= new int [4];
        String sub_single_entry_list=new String();
        for(int i=0; i<10; i++){
            sub_single_entry_list=single_entry_list.substring((7*i), ((7*(i+1))));
            // first is index 0 to 6
            // second is 7 to 13
            if(sub_single_entry_list.charAt(0)=='B'){
                cat_b_nums[0]+=1;
            }if(sub_single_entry_list.charAt(1)=='B' && sub_single_entry_list.charAt(2)=='B'){
                cat_b_nums[1]+=2;
            }else if(sub_single_entry_list.charAt(1)=='B' || sub_single_entry_list.charAt(2)=='B'){
                cat_b_nums[1]+=1;
            }if(sub_single_entry_list.charAt(3)=='B'&& sub_single_entry_list.charAt(4)=='B'){
                cat_b_nums[2]+=2;
            }else if(sub_single_entry_list.charAt(3)=='B' || sub_single_entry_list.charAt(4)=='B'){
                cat_b_nums[2]+=1;
            }if(sub_single_entry_list.charAt(5)=='B' && sub_single_entry_list.charAt(6)=='B'){
                cat_b_nums[3]+=2;
            }else if(sub_single_entry_list.charAt(5)=='B' || sub_single_entry_list.charAt(6)=='B'){
                cat_b_nums[3]+=1;
            }

        }
        System.out.println(Arrays.toString(cat_b_nums));
        return(cat_b_nums);
    }
    public static int[] percentage_calc(int [] x, int [] y){
        int[] calculated_b_percent= new int [4];
        double calculated_percentage=0.0;
        for(int i=0; i<4; i++){
            calculated_percentage=(double) ((x[i]*100)/y[i]);
            calculated_b_percent[i]=(int) calculated_percentage;
        }
        // System.out.println(Arrays.toString(calculated_b_percent));
        return calculated_b_percent;
    }
    public static String personality_gen(int [] x){
        String dimensions = "";
        for (int i = 0; i < 4; i++) {
            if (x[i] > 50) {
                dimensions = dimensions + DIMENSIONOPTIONS[i][1];
            } else if (x[i] < 50) {
                dimensions = dimensions + DIMENSIONOPTIONS[i][0];
            } else {
                dimensions = dimensions + "X";
            }
        }
        return dimensions;
    }
}

