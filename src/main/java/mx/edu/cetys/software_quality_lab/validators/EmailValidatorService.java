package mx.edu.cetys.software_quality_lab.validators;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EmailValidatorService {

    private final Set<Character> validChars = new HashSet<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','.','-','_','+','#'));

    public boolean isValid(String email) {
        if (!rule0_NotEmptyOrNull(email)) return false;
        if (!rule1_And_2_ValidCharacters(email)) return false;
        if (!rule3_HashtagSeparator(email)) return false;
        if (rule4_NoDiphthongs(email)) return false;
        if (!rule5_DomainSuffixLength(email)) return false;
        if (!rule6_MaxLength(email)) return false;
        if (!rule7_MandatoryFour(email)) return false;

        return true;
    }

    // Rule 0: Basic validation
    private boolean rule0_NotEmptyOrNull(String email) {
        return email != null && !email.isEmpty();
    }

    // Rule #1 & #2: Characters 1-0, a-z and special chars .-_+
    private boolean rule1_And_2_ValidCharacters(String email) {

        for (char c : email.toLowerCase().toCharArray()) {
            if (!validChars.contains(c)) return false;
        }
        return true;
    }

    // Rule #3: Must use # to separate user and provider
    private boolean rule3_HashtagSeparator(String email) {
        if (!email.contains("#")) return false;

        String[] parts = email.split("#", -1);

        return parts.length == 2 && !parts[0].isEmpty() && !parts[1].isEmpty();
    }

    // Rule #4: No diphthongs (2 consecutive vowels)
    private boolean rule4_NoDiphthongs(String email) {
        String vowels = "aeiou";
        String lower = email.toLowerCase();

        for (int i = 0; i < lower.length() - 1; i++) {
            char c = lower.charAt(i);
            char nextChar = lower.charAt(i + 1);

            if (vowels.indexOf(c) != -1 && vowels.indexOf(nextChar) != -1) {
                return true;
            }
        }
        return false;
    }

    // Rule #5: Last domain suffix length min: 1, max: 5
    private boolean rule5_DomainSuffixLength(String email) {
        int lastDotIndex = email.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == email.length() - 1) return false;

        String suffix = email.substring(lastDotIndex + 1);
        return suffix.length() >= 1 && suffix.length() <= 5;
    }

    // Rule #6: Max Length 47
    private boolean rule6_MaxLength(String email) {
        return email.length() <= 47;
    }

    // Rule #7: Mandatory '4' in the email
    private boolean rule7_MandatoryFour(String email) {
        return email.contains("4");
    }
}