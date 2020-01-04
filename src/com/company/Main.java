package com.company;

public class Main {

    public static void main(String[] args) {
        long inputValue = Long.valueOf(19006802003001L);
        System.out.println(numbersToWordsAgain(inputValue));
    }

    public static String numbersToWordsAgain(Long inputNumber){

        char[] trillions = new char[]{'0','0','0'};
        char[] billions  = new char[]{'0','0','0'};
        char[] millions  = new char[]{'0','0','0'};
        char[] thousands = new char[]{'0','0','0'};
        char[] hundreds  = new char[]{'0','0','0'};

        StringBuilder numberStringBuilder = new StringBuilder();
        numberStringBuilder.append(Long.toString(inputNumber)).reverse();
        int numberOfDigits = numberStringBuilder.length();
        System.out.println("Input Number: " + inputNumber);
        System.out.println("Number of Digits: " + numberOfDigits);

        for(int digitIndex = 0; digitIndex < numberStringBuilder.length(); digitIndex+=3){
            if(digitIndex == 0){
                if(numberStringBuilder.length() >= 1) hundreds[2] = numberStringBuilder.charAt(0);
                if(numberStringBuilder.length() >= 2) hundreds[1] = numberStringBuilder.charAt(1);
                if(numberStringBuilder.length() >= 3) hundreds[0] = numberStringBuilder.charAt(2);
            }
            if(digitIndex == 3){
                if(numberStringBuilder.length() >= 4) thousands[2] = numberStringBuilder.charAt(3);
                if(numberStringBuilder.length() >= 5) thousands[1] = numberStringBuilder.charAt(4);
                if(numberStringBuilder.length() >= 6) thousands[0] = numberStringBuilder.charAt(5);
            }
            if(digitIndex == 6){
                if(numberStringBuilder.length() >= 7) millions[2] = numberStringBuilder.charAt(6);
                if(numberStringBuilder.length() >= 8) millions[1] = numberStringBuilder.charAt(7);
                if(numberStringBuilder.length() >= 9) millions[0] = numberStringBuilder.charAt(8);
            }
            if(digitIndex == 9){
                if(numberStringBuilder.length() >= 10) billions[2] = numberStringBuilder.charAt(9);
                if(numberStringBuilder.length() >= 11) billions[1] = numberStringBuilder.charAt(10);
                if(numberStringBuilder.length() >= 12) billions[0] = numberStringBuilder.charAt(11);
            }
            if(digitIndex == 12){
                if(numberStringBuilder.length() >= 13) trillions[2] = numberStringBuilder.charAt(12);
                if(numberStringBuilder.length() >= 14) trillions[1] = numberStringBuilder.charAt(13);
                if(numberStringBuilder.length() >= 15) trillions[0] = numberStringBuilder.charAt(14);
            }
        }
        String trillionsString = groupThreeDigits(trillions).trim().equals("") ? "": groupThreeDigits(trillions) + "trillion, ";
        String billionsString = groupThreeDigits(billions).trim().equals("") ? "": groupThreeDigits(billions) + "billion, ";
        String millionsString = groupThreeDigits(millions).trim().equals("") ? "": groupThreeDigits(millions) + "million, ";
        String thousandsString = groupThreeDigits(thousands).trim().equals("") ? "": groupThreeDigits(thousands) + "thousand, and ";
        String hundredsString = groupThreeDigits(hundreds);

        String inputNumberAsWords = trillionsString + billionsString + millionsString + thousandsString + hundredsString;

        return inputNumberAsWords;
    }

    public static String groupThreeDigits(char[] numberSection){

        String[] digits = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine"};
        String[] Units = new String[]{"zero","","tens","hundred"};
        String[][] replacements = new String[][]{{"zero tens", ""},{"zero hundred and",""},{"zero thousand and",""},
                {"zero hundred,",""},{"zero million,",""},{"zero",""},
                {"one tens-nine","nineteen"},{"one tens-eight","eighteen"},{"one tens-seven","seventeen"},
                {"one tens-six","sixteen"},{"one tens-five","fifteen"},{"one tens-four", "fourteen"},
                {"one tens-three","thirteen"},{"one tens-two","twelve"},{"one tens-one","eleven"}, {"one tens","ten"},
                {"two tens","twenty"},{"three tens", "thirty"},{"four tens","forty"},
                {"five tens", "fifty"},{"six tens","sixty"},{"seven tens","seventy"},
                {"eight tens","eighty"},{"nine tens","ninety"}};

        /* System.out.println(numberSectionAsString); UNCOMMENT FOR DEBUGGING*/

        String numberToWords = "";

        int unitIndex = 3;
        for(char s: numberSection){
            numberToWords+= digits[Integer.parseInt(Character.toString(s))] + " ";
            numberToWords+= Units[unitIndex--];
            /* System.out.println(numberToWords);  UNCOMMENT FOR DEBUGGING*/
            String finalSectionWithZeroTens = numberToWords.substring(numberToWords.length()-9, numberToWords.length());
            String previousSection = numberToWords.substring(0, 5);

            if(unitIndex==2 ){
                numberToWords += " and ";
            }else if(unitIndex == 1 && !finalSectionWithZeroTens.equals("zero tens")){
                numberToWords +="-";
            }
        }

        for (String[] replacement : replacements){
            numberToWords = numberToWords.replace(replacement[0],replacement[1]);
        }

        return numberToWords;
    }
}
