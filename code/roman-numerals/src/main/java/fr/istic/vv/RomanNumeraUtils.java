package fr.istic.vv;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeraUtils {

        /**
         * Symbols M, C, X, I can be repeated consecutively up to three times.
         * Symbols D, L and V can not be repeated.
         * @param value
         * @return
         */
        public static boolean isValidRomanNumeral(String value) {

                char[] chars = value.toCharArray();

                // verify if the symbols M, C, X, I are repeated consecutively more than three times.
                int i = 0;
                while (i < chars.length-1) {
                        char chari = chars[i];
                        if ( chari == 'M' || chari == 'C' || chari == 'X' || chari == 'I') {
                                int count = 1;
                                for (int j = i+1; j < i+4; j++) {
                                      if (chars[j] == chars[j+1]) {
                                              count ++;
                                      } else {
                                              break;
                                      }
                                }
                                if (count > 3) {
                                        return false;
                                }
                                i += count;
                        } else {
                                i++;
                        }

                }

                // Verify if symbols D, L and V are repeated
                for (int k = 0; k < chars.length-1; k++) {
                        if (chars[k] == 'D' || chars[k] == 'L' || chars[k] == 'V' ) {
                                for (int j = k+1; j < chars.length-1; j++) {
                                        if (chars[k] == chars[j]) {
                                             return false;
                                        }
                                }
                        }
                }

                return true;
        }
    
        public static int parseRomanNumeral(String numeral) {

                Map<Character, Integer> map = new HashMap<>();
                map.put('I',1);
                map.put('V',5);
                map.put('X',10);
                map.put('L',50);
                map.put('C',100);
                map.put('D',500);
                map.put('M',1000);

                int length = numeral.length();
                int result = map.get(numeral.charAt(length-1));

                for (int i = length-2; i > 0; i--) {
                        if (map.get(numeral.charAt(i)) >= map.get(numeral.charAt(i+1))) {
                                result += map.get(numeral.charAt(i));
                        } else {
                                result -= map.get(numeral.charAt(i));
                        }
                }

                return result;
        }
    
        public static String toRomanNumeral(int number) {
                StringBuilder string = new StringBuilder();
                while (number != 0) {
                        if (number >= 1000) {
                                string.append("M");
                                number -= 1000;
                        } else if (number >= 900) {
                                string.append("CM");
                                number -= 900;
                        } else if (number >= 500) {
                                string.append("D");
                                number -= 500;
                        } else if (number >= 400) {
                                string.append("CD");
                                number -= 400;
                        } else if (number >= 100) {
                                string.append("C");
                                number -= 100;
                        } else if (number >= 90) {
                                string.append("XC");
                                number -= 90;
                        } else if (number >= 50) {
                                string.append("L");
                                number -= 50;
                        } else if (number >= 40) {
                                string.append("XL");
                                number -= 40;
                        } else if (number >= 10) {
                                string.append("X");
                                number -= 10;
                        } else if (number >= 9) {
                                string.append("IX");
                                number -= 9;
                        } else if (number >= 5) {
                                string.append("V");
                                number -= 5;
                        } else if (number >= 4) {
                                string.append("IV");
                                number -= 4;
                        }  else if (number >= 1) {
                                string.append("I");
                                number -= 1;
                        }

                }

                return string.toString(); }
    
}

