package com.weizhang;

/**
 * Created by Wei Zhang on 6/1/15.
 *
 * Compare two version numbers version1 and version2.
 If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

 You may assume that the version strings are non-empty and contain only digits and the . character.
 The . character does not represent a decimal point and is used to separate number sequences.
 For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

 Here is an example of version numbers ordering:

 0.1 < 1.1 < 1.2 < 13.37
 *
 */
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] parts1 = version1.split("\\.");
        String[] parts2 = version2.split("\\.");

        int L1 = parts1.length;
        int L2 = parts2.length;

        L1 = L1 - numberOfTrailingZeros(parts1);
        L2 = L2 - numberOfTrailingZeros(parts2);

        int result = 0;
        int v1 = 0, v2 = 0;
        int partIndex = 0;
        while (v1 == v2 && partIndex < Math.min(L1, L2)) {
            v1 = Integer.parseInt(parts1[partIndex]);
            v2 = Integer.parseInt(parts2[partIndex]);
            result = compareIntegers(v1, v2);
            if (result != 0)
                break;
            partIndex++;
        }
        if (result == 0 && L1 != L2) {
            result = compareIntegers(L1, L2);
        }
        return result;

    }

    private int numberOfTrailingZeros(String[] parts) {
        int numOfZero = 0;
        int L = parts.length;
        for (int i = L - 1; i >= 0; i--) {
            if (Integer.parseInt(parts[i]) == 0) {
                numOfZero++;
            } else {
                break;
            }
        }
        return numOfZero;
    }

    private int compareIntegers(int i1, int i2) {
        if (i1 < i2) {
            return -1;
        } else if (i1 > i2) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        CompareVersionNumbers test = new CompareVersionNumbers();
        int result = test.compareVersion("01", "1");
        result = test.compareVersion("01.2", "1.1");
        result = test.compareVersion("01.1.3", "1.1.2");
        result = test.compareVersion("01.1.1.3", "1.1.1.2");
        result = test.compareVersion("01.1.1.1", "1.1.1.1");
        result = test.compareVersion("01.0.0", "1");
    }
}
