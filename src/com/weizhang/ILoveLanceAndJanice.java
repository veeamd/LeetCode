package com.weizhang;

/**
 * Google foo.bar
 *
 I Love Lance & Janice
 =====================

 You've caught two of your fellow minions passing coded notes back and forth - while they're on duty, no less! Worse, you're pretty sure it's not job-related - they're both huge fans of the space soap opera "Lance & Janice". You know how much Commander Lambda hates waste, so if you can prove that these minions are wasting her time passing non-job-related notes, it'll put you that much closer to a promotion.

 Fortunately for you, the minions aren't exactly advanced cryptographers. In their code, every lowercase letter [a..z] is replaced with the corresponding one in [z..a], while every other character (including uppercase letters and punctuation) is left untouched.  That is, 'a' becomes 'z', 'b' becomes 'y', 'c' becomes 'x', etc.  For instance, the word "vmxibkgrlm", when decoded, would become "encryption".

 Write a function called answer(s) which takes in a string and returns the deciphered string so you can show the commander proof that these minions are talking about "Lance & Janice" instead of doing their jobs.


 Languages
 =========

 To provide a Python solution, edit solution.py
 To provide a Java solution, edit solution.java

 Test cases
 ==========

 Inputs:
 (string) s = "wrw blf hvv ozhg mrtsg'h vkrhlwv?"
 Output:
 (string) "did you see last night's episode?"

 Inputs:
 (string) s = "Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!"
 Output:
 (string) "Yeah! I can't believe Lance lost his job at the colony!!"
 */
public class ILoveLanceAndJanice {
    public static String answer(String s) {
        char[] charArray = s.toCharArray();
        for(int i = 0; i < s.length(); i++) {
            charArray[i] = swap(charArray[i]);
        }
        return new String(charArray);
    }

    static int aInt = 97;
    static int zInt = 122;
    static double midPoint = 109.5;

    public static char swap(char c) {
        int castedToInt = (int)c;
        if (castedToInt <= zInt && castedToInt >= aInt) {
            return (char) (2 * midPoint - castedToInt);
        } else {
            return c;
        }
    }

    public static void main(String[] args) {
        System.out.println(answer("Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!"));
    }

    public static void testSwap() {
        System.out.println(swap('A'));
        System.out.println(swap('Z'));
        System.out.println(swap(','));
        System.out.println(swap('!'));
        System.out.println(swap('?'));
        System.out.println(swap(' '));
        System.out.println(swap('a'));
        System.out.println(swap('b'));
        System.out.println(swap('c'));
        System.out.println(swap('d'));
        System.out.println(swap('e'));
        System.out.println(swap('f'));
        System.out.println(swap('g'));
        System.out.println(swap('h'));
        System.out.println(swap('i'));
        System.out.println(swap('j'));
        System.out.println(swap('k'));
        System.out.println(swap('l'));
        System.out.println(swap('m'));
        System.out.println(swap('n'));
        System.out.println(swap('o'));
        System.out.println(swap('p'));
        System.out.println(swap('q'));
        System.out.println(swap('r'));
        System.out.println(swap('s'));
        System.out.println(swap('t'));
        System.out.println(swap('u'));
        System.out.println(swap('v'));
        System.out.println(swap('w'));
        System.out.println(swap('x'));
        System.out.println(swap('y'));
        System.out.println(swap('z'));
    }
}
