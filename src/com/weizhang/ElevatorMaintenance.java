package com.weizhang;

import java.util.*;

/**
 * Google foo.bar
 *
 Elevator Maintenance
 ====================

 You've been assigned the onerous task of elevator maintenance - ugh! It wouldn't be so bad, except that all the elevator documentation has been lying in a disorganized pile at the bottom of a filing cabinet for years, and you don't even know what elevator version numbers you'll be working on.

 Elevator versions are represented by a series of numbers, divided up into major, minor and revision integers. New versions of an elevator increase the major number, e.g. 1, 2, 3, and so on. When new features are added to an elevator without being a complete new version, a second number named "minor" can be used to represent those new additions, e.g. 1.0, 1.1, 1.2, etc. Small fixes or maintenance work can be represented by a third number named "revision", e.g. 1.1.1, 1.1.2, 1.2.0, and so on. The number zero can be used as a major for pre-release versions of elevators, e.g. 0.1, 0.5, 0.9.2, etc (Commander Lambda is careful to always beta test her new technology, with her loyal henchmen as subjects!).

 Given a list of elevator versions represented as strings, write a function answer(l) that returns the same list sorted in ascending order by major, minor, and revision number so that you can identify the current elevator version. The versions in list l will always contain major numbers, but minor and revision numbers are optional. If the version contains a revision number, then it will also have a minor number.

 For example, given the list l as ["1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"], the function answer(l) would return the list ["1.0", "1.0.2", "1.0.12", "1.1.2", "1.3.3"]. If two or more versions are equivalent but one version contains more numbers than the others, then these versions must be sorted ascending based on how many numbers they have, e.g ["1", "1.0", "1.0.0"]. The number of elements in the list l will be at least 1 and will not exceed 100.

 Languages
 =========

 To provide a Python solution, edit solution.py
 To provide a Java solution, edit solution.java

 Test cases
 ==========

 Inputs:
 (string list) l = ["1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"]
 Output:
 (string list) ["1.0", "1.0.2", "1.0.12", "1.1.2", "1.3.3"]

 Inputs:
 (string list) l = ["1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"]
 Output:
 (string list) ["0.1", "1.1.1", "1.2", "1.2.1", "1.11", "2", "2.0", "2.0.0"]
 */
public class ElevatorMaintenance {

    public static String[] answer(String[] l) {
        ElevatorVersion[] versions = new ElevatorVersion[l.length];
        for (int i = 0; i < l.length; i++) {
            versions[i] = new ElevatorVersion(l[i]);
        }
        Arrays.sort(versions);
        String[] returnValue = new String[l.length];
        for (int i = 0; i < l.length; i++) {
            returnValue[i] = versions[i].getVersionString();
        }
        return returnValue;
    }


    public static void main(String[] args) {
        String[] answer = answer(new String[]{"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"});
        printArrayOfStrings(answer);
        answer = answer(new String[]{"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"});
        printArrayOfStrings(answer);
    }

    public static void printArrayOfStrings(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
        System.out.println();
        System.out.println();
    }

    static class ElevatorVersion implements Comparable<ElevatorVersion> {

        private String versionString;
        private String[] parts;
        private int[] numberParts;

        public ElevatorVersion(String versionString) {
            this.versionString = versionString;
            parts = versionString.split("\\.");
            numberParts = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                numberParts[i] = Integer.parseInt(parts[i]);
            }
        }

        public String getVersionString() {
            return versionString;
        }

        public int getNumberPartAtIndx(int index) {
            if (index < numberParts.length) {
                return numberParts[index];
            } else {
                return 0;
            }
        }

        public int compareTo(ElevatorVersion o) {
            int compareCount = Math.min(o.parts.length, this.parts.length);
            for (int i = 0; i < compareCount; i++) {
                if (this.getNumberPartAtIndx(i) < o.getNumberPartAtIndx(i)) {
                    return -1;
                } else if (this.getNumberPartAtIndx(i) > o.getNumberPartAtIndx(i)) {
                    return 1;
                }
            }
            return this.parts.length - o.parts.length;
        }
    }
}


