package code;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BalancedStrings {

    public static void main(String[] args) {
        BalancedStrings balancedStrings = new BalancedStrings();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string:");
        String input = sc.next();
        System.out.println(balancedStrings.isBalancedString(input));
    }

    private boolean isBalancedString(String input) {
        if (input == null) return false;
        // (? for lookahead
        // (?:(?:[ac]*[bd]){2})* look for any number of a or c,
        // followed by one b or d - twice, so we get an even number -
        // and that whole pattern as often as needed

        // [ac]* if there are and a or c left, match them
        // $ end of string
        String regex1 = "^(?=(?:(?:[ac]*[bd]){2})*[ac]*$)(?:(?:[bd]*[ac]){2})*[bd]*$";
        Pattern p = Pattern.compile(regex1);
        Matcher m = p.matcher(input);
        boolean out = m.matches();
        return out;
    }
}
