//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static int countVowels(String s) {
        String vowels = "aeiou";
        s = s.toLowerCase();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}
