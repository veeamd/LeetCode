package com.weizhang;

/*
 * Earlier solutions
 */

import java.util.*;

public class Main {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1)
            return 0;

        class Rise {
            int startPrice;
            int changes;
            int endPrice() {
                return startPrice + changes;
            }
        }

        ArrayList<Rise> rises = new ArrayList<Rise>();
        boolean ascending = true;
        int pivot = 0;
        Rise lastRise = null;
        int maxChanges = 0;
        for (int i = 1; i <= prices.length; i++) {
            if ( i == prices.length && ascending == true
                    ||
                    prices[i] - prices[i-1] > 0 && ascending == false
                    ||
                    prices[i] - prices[i-1] < 0 && ascending == true
                     )
            {
                if (ascending) {

                    Rise rise = new Rise();
                    rise.startPrice = prices[pivot];
                    rise.changes = prices[i-1] - prices[pivot];

                    // merge rises if applicable
                    if (lastRise != null) {
                        if (lastRise.startPrice <= rise.startPrice && lastRise.endPrice() < rise.endPrice()) {
                            lastRise.changes = rise.endPrice() - lastRise.startPrice;
                        }
                    }
                    if (lastRise == null || lastRise.startPrice > rise.startPrice ) {
                        rises.add(rise);
                        lastRise = rise;
                    }

                    if (maxChanges < lastRise.changes) {
                        maxChanges = lastRise.changes;
                    }
                }
                ascending = !ascending;
                pivot = i - 1;
            }
        }
        return maxChanges;
    }

    public int[] twoSum(int[] numbers, int target) {
        int[] n = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(n);
        int i = 0;
        int j = n.length - 1;

        while (i < j) {
            if (n[i] + n[j] < target) {
                i++;
            } else if (n[i] + n[j] > target) {
                j--;
            } else {
                int index_i = 0, index_j = 0;
                int ii = 0;
                while (ii < numbers.length) {
                    int num = numbers[ii];
                    if (num == n[i] || num == n[j]) {
                        index_i = ii;
                        ii++;
                        while(ii < numbers.length) {
                            num = numbers[ii];
                            if (num == n[i] || num == n[j]) {
                                index_j = ii;
                                break;
                            }
                            ii++;
                        }
                        break;
                    }
                    ii++;
                }
                int[] r = {index_i + 1, index_j + 1};
                return r;
            }

        }
        return null;
    }




    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode output = new ListNode(0);
        ListNode l3 = output;
        while(l1 != null || l2 != null || l3 != null) {

            l3.val += l1 != null ? l1.val : 0;
            l3.val += l2 != null ? l2.val : 0;

            if (l3.val / 10 > 0) {
                l3.next = new ListNode(l3.val / 10);
            }
            l3.val = l3.val % 10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            if (l3.next == null && (l1 != null || l2 != null))
                l3.next = new ListNode(0);
            l3 = l3.next;
        }

        return output;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        int[] characterPositions = new int[128];
        Arrays.fill(characterPositions, -1);

        // s = s.toLowerCase();  // case sensitive
        char[] characters = s.toCharArray();
        int maxLength = 0;
        int start = -1;

        // two pointers: start and i;
        for (int i = 0; i < characters.length; i++) {
            start = Math.max(start, characterPositions[characters[i]]);
            maxLength = Math.max(maxLength, i - start);
            characterPositions[characters[i]] = i;
        }
        return maxLength;
    }


    public String longestPalindrome(String s) {
        if (s == null) return null;
        if (s.length() < 2) return s;

        int longestLeft = 0;
        int longestRight = 0;
        int maxLength = 0;

        char[] str = s.toCharArray();

        int lo; // left offset
        int ro; // right offset
        int cl; // current length
        int i = 0;
        while (i < str.length) {
            lo = 0;
            ro = 1;
            while (i - lo >= 0 && i + ro < str.length && str[i - lo] == str[i + ro]) {
                cl = ro + lo + 1;
                if (cl > maxLength) {
                    maxLength = cl;
                    longestLeft = i - lo;
                    longestRight = i + ro;
                }

                lo++;
                ro++;
            }

            lo = 1;
            ro = 1;
            while (i - lo >= 0 && i + ro < str.length && str[i - lo] == str[i + ro]) {
                cl = ro + lo + 1;

                if (cl > maxLength) {
                    maxLength = cl;
                    longestLeft = i - lo;
                    longestRight = i + ro;
                }
                lo++;
                ro++;
            }

            i++;
        }

        return s.substring(longestLeft, longestRight + 1);
    }

    // http://en.wikipedia.org/wiki/Longest_palindromic_substring

    public static String longestPalindrome2(String s) {
        if (s==null || s.length()==0)
            return "";

        char[] s2 = addBoundaries(s.toCharArray());
        int[] p = new int[s2.length];
        int c = 0, r = 0; // Here the first element in s2 has been processed.
        int m = 0, n = 0; // The walking indices to compare if two elements are the same
        for (int i = 1; i<s2.length; i++) {
            if (i>r) {
                p[i] = 0; m = i-1; n = i+1;
            } else {
                int i2 = c*2-i;
                if (p[i2]<(r-i)) {
                    p[i] = p[i2];
                    m = -1; // This signals bypassing the while loop below.
                } else {
                    p[i] = r-i;
                    n = r+1; m = i*2-n;
                }
            }
            while (m>=0 && n<s2.length && s2[m]==s2[n]) {
                p[i]++; m--; n++;
            }
            if ((i+p[i])>r) {
                c = i; r = i+p[i];
            }
        }
        int len = 0; c = 0;
        for (int i = 1; i<s2.length; i++) {
            if (len<p[i]) {
                len = p[i]; c = i;
            }
        }
        char[] ss = Arrays.copyOfRange(s2, c - len, c + len + 1);
        return String.valueOf(removeBoundaries(ss));
    }

    private static char[] addBoundaries(char[] cs) {
        if (cs==null || cs.length==0)
            return "||".toCharArray();

        char[] cs2 = new char[cs.length*2+1];
        for (int i = 0; i<(cs2.length-1); i = i+2) {
            cs2[i] = '|';
            cs2[i+1] = cs[i/2];
        }
        cs2[cs2.length-1] = '|';
        return cs2;
    }

    private static char[] removeBoundaries(char[] cs) {
        if (cs==null || cs.length<3)
            return "".toCharArray();

        char[] cs2 = new char[(cs.length-1)/2];
        for (int i = 0; i<cs2.length; i++) {
            cs2[i] = cs[i*2+1];
        }
        return cs2;
    }

////////////////////////////////////////////////////////////////////////////////////


    static public class Pillar implements Comparable<Pillar> {
        int height;
        int index;
        public Pillar(int height, int index) {
            this.height = height;
            this.index = index;
        }

        public int compareTo(Pillar p) {
            if (this.height > p.height)
                return 1;
            else if (this.height < p.height)
                return -1;
            else
                return 0;
        }

        static public Pillar leftPillar(Pillar p1, Pillar p2) {
            if (p1.index < p2.index)
                return p1;
            else
                return p2;
        }

        static  public  Pillar rightPillar(Pillar p1, Pillar p2) {
            if (p1.index < p2.index)
                return p2;
            else
                return p1;
        }

        static public int area(Pillar p1, Pillar p2) {
            int x = Math.abs(p1.index - p2.index);
            int y = Math.min(p1.height, p2.height);
            return x * y;
        }
    }



/*
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        TreeNode left = root.left;
        if (left != null)
        {
            if (left.val >= root.val
                    || maxValue(left) >= root.val
                    || !isValidBST(left))
            {
                return false;
            }
        }

        TreeNode right = root.right;
        if (right != null)
        {
            if (right.val <= root.val
                    || minValue(right) <= root.val
                    || !isValidBST(right))
            {
                return false;
            }
        }

        return true;

    }

    public int minValue(TreeNode node) {
        if (node.left != null)
            return minValue(node.left);
        else
            return node.val;
    }

    public int maxValue(TreeNode node) {
        if (node.right != null)
            return maxValue(node.right);
        else
            return node.val;

    }
    */



    ///////////////////////////////////////
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode node, TreeNode min, TreeNode max) {
        if (node == null)
            return true;

        if (min != null && node.val <= min.val) return false;
        if (max != null && node.val >= max.val) return false;

        return isValidBST(node.left, min, node) && isValidBST(node.right, node, max);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    public ListNode reverseLinkedList(ListNode head) {
        if (head == null) {
            throw new NullPointerException();
        }

        ListNode p1 = head, p2 = head.next;
        ListNode p3;
        while (p2 != null) {
            p3 = p2.next;

            // reverse pointer
            p2.next = p1;
            if (p1.next == p2) {
                // for head
                p1.next = null;
            }

            // shift pointer
            p1 = p2;
            p2 = p3;
        }
        return p1;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }

        if (m == n) {
            return head;
        }

        int i = 1;
        ListNode p1 = head;
        ListNode p2 = head.next;
        ListNode p3 = null;

        ListNode leftEnd = null, reversedEnd = null, reverseStart = null, rightStart = null;

        while (p2 != null) {
            p3 = p2.next;
            if (i < m) {
                leftEnd = p1;
            }
            else if (i>= m && i < n) {
                p2.next = p1;
                reverseStart = p2;
                rightStart = p3;
                if (p1.next == p2) {
                    // start point of the reversed part of the list
                    reversedEnd = p1;
                    p1.next = null;
                }
            } else {
                break;
            }
            p1 = p2;
            p2 = p3;
            i++;
        }
        if (reversedEnd != null) {
            reversedEnd.next = rightStart;
        }
        if (leftEnd != null) {
            leftEnd.next = reverseStart;
            return head;
        } else {
            return reverseStart;
        }
    }


    /////////////////////////////////////////////
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode a = headA;
        ListNode b = headB;

        int nullCount = 0;
        while (nullCount < 3) {
            if (a == b) {
                return a;
            }

            a = a.next;
            b = b.next;

            if (a == null) {
                nullCount++;
                a = headB;
            }

            if (b == null) {
                nullCount++;
                b = headA;
            }
        }
        return null;
    }


    /////////////////////////////////////////////
    /**
     * Note:
     Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
     The solution set must not contain duplicate triplets.
    */
    public List<List<Integer>> threeSum(int[] num) {
        if (num == null || num.length < 3) {
            return new ArrayList();
        }

        ArrayList<List<Integer>> triplets = new ArrayList<List<Integer>>();

        Arrays.sort(num);
        int a = 0, b = 1, c = num.length - 1;
        int sum = 0;
        while (a < num.length - 2) {
            // remove duplicate
            if (a > 0 && num[a] == num[a-1]) {
                a++;
                continue;
            }
            b = a + 1;
            c = num.length - 1;
            while (b < c) {
                // remove duplicate
                if (b > a + 1 && num[b] == num[b-1]) {
                    b++;
                    continue;
                }
                // remove duplicate
                if (c < num.length -1 && num[c] == num[c+1]) {
                    c--;
                    continue;
                }

                sum = num[a] + num[b] + num[c];
                if (sum < 0) {
                    b++;
                } else if (sum > 0) {
                    c--;
                } else {
                    ArrayList<Integer> triplet = new ArrayList<Integer>();
                    triplet.add(num[a]);
                    triplet.add(num[b]);
                    triplet.add(num[c]);
                    triplets.add(triplet);
                    b++; c--;

                }
            }
            a++;
        }
        return triplets;
    }

    ////////////////////////////////////////////////////////

    /**
     * zigzag conversion
     */
    /*
    public String convert(String s, int nRows) {

        StringBuilder[] rows = new StringBuilder[nRows];

        boolean rowIndexIncreasing = true;
        char[] c = s.toCharArray();
        int ci = 0; // character index
        int si = 0; // vertical string (row) index
        int pi = 0; // horizontal position index

        while (ci < s.length()) {
            if (rowIndexIncreasing) {
                // pi don't change
                StringBuilder row = rows[si];
                if (rows[si] == null) {
                    row = new StringBuilder();
                }
                row.append(c[ci]);
                si++;

            } else {
                // pi increase one every iteration
                StringBuilder row = rows[si];
                int numCharsToAppend = nRows - 2;
                int indexOfCharToInsert =
                // append space character first
                int numOfSpace = nRows - 2 - si;
                if (numOfSpace > 0) {
                    for (int i = 0; i < numOfSpace; i++) {
                        row.append(' ');
                    }
                }
                row.append(c[ci]);
                // append more space
                numOfSpace = si - 1;
                if (numOfSpace > 0) {
                    for (int i = 0; i < numOfSpace; i++) {
                        row.append(' ');
                    }
                }
                pi++;
                si--;
            }

            if (si == nRows) {
                si = nRows - 2;
                rowIndexIncreasing = false;
                pi++;
            } else if (si < 0) {
                si = 1;
                rowIndexIncreasing = true;
            }
            ci++;
        }
    }
    */

    ////////////////////////////////////////////////////////////
    public int atoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        // use to pointers, i to mark the start of the integer, start with 0;
        // j to mark the end of the integer starts with -1;
        // if j never greater than 0, then return 0 as well

        char[] chars = str.toCharArray();
        int i = 0, j = -1;

        boolean isValid = true;
        while (i < str.length()) {
            if (chars[i] <= ' ') {
                i++;
                continue;
            }
            if (chars[i] >= '0' && chars[i] <= '9' || chars[i] == '-' || chars[i] == '+') {
                break;
            } else {
                isValid = false;
                break;
            }
        }

        if (isValid == false) {
            return 0;
        } else {
            j = i;
            while (j < str.length() - 1) {
                if (chars[j+1] >= '0' && chars[j+1] <= '9') {
                    j++;
                } else {
                    break;
                }
            }
            boolean negative = false;
            if (chars[i] == '-') {
                negative = true;
                i++;
            } else if (chars[i] == '+'){
                i++;
            }

            if (j < i) {
                return 0;
            } else {
                // from j to i convert to integer
                int m = 0;
                int r = 0;
                while (j >= i) {
                    if (negative)
                        r -= (chars[j] - '0') * Math.pow(10, m);
                    else
                        r += (chars[j] - '0') * Math.pow(10, m);
                    m++;
                    j--;
                }


                if (r > 0 && negative) {
                    // overflows
                    return Integer.MIN_VALUE;
                } else if (r < 0 && !negative) {
                    // overflows
                    return Integer.MAX_VALUE;
                } else {
                    return r;
                }
            }

        }

    }

    ////////////////////////////////////////////////////////////
    public int maxArea(int[] height) {
        if (height.length < 2)
            return 0;

        Pillar[] pillars = new Pillar[height.length];
        for (int i = 0; i < height.length; i++) {
            pillars[i] = new Pillar(height[i], i);
        }

        Arrays.sort(pillars);

        int count = height.length;

        Pillar left = Pillar.leftPillar(pillars[count - 1], pillars[count - 2]);
        Pillar right = Pillar.rightPillar(pillars[count - 1], pillars[count - 2]);
        int maxArea = Pillar.area(left, right);

        int i = count - 3;
        while (i >= 0) {
            Pillar p = pillars[i];
            if (p.index < left.index) {
                left = p;
            } else if (p.index > right.index) {
                right = p;
            }
            maxArea = Math.max(maxArea, Pillar.area(left, right));
            i--;

        }

        return maxArea;

    }

    /*
    an even better version
     */
    public int maxArea2(int[] height) {
        if (height.length < 2)
            return 0;

        int l = 0, r = height.length - 1;
        int ma = 0;
        while (l < r) {
            ma = Math.max(ma, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                do {
                    l++;
                } while (l < r && height[l] <= height[l -1]);
            } else {
                do {
                    r--;
                } while (l < r && height[r] <= height[r + 1]);
            }
        }
        return ma;
    }

    ////////////////////////////////////////////////////////////
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;

        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        boolean isValid = true;
        while (i < j) {
            char left = s.charAt(i);
            char right = s.charAt(j);
            if (!isLowerAlphaNumeric(left)) {
                i++;
            } else if (!isLowerAlphaNumeric(right)) {
                j--;
            } else {
                if (left == right) {
                    i++; j--;
                } else {
                    isValid = false;
                    break;
                }
            }


        }
        return isValid;
    }

    static public boolean isLowerAlphaNumeric(char c) {
        return c <= 'z' && c >= 'a' || c >= '0' && c <= '9';
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minDepth(TreeNode root) {
        int depth = 0;
        if (root == null)
            return depth;

        LinkedList<TreeNode> nodeQ = new LinkedList<TreeNode>();
        nodeQ.add(root);
        int levelSize = nodeQ.size();
        int levelIndx = 0;
        depth = 1;
        while(nodeQ.peek() != null) {
            TreeNode n = nodeQ.remove();
            if (n.left == null && n.right == null) break;
            else {
                if (n.left != null) {
                    nodeQ.add(n.left);
                }
                if (n.right != null) {
                    nodeQ.add(n.right);
                }
                levelIndx++;
                if (levelIndx == levelSize) {
                    levelIndx = 0;
                    levelSize = nodeQ.size();
                    depth++;
                }
            }

        }
        return depth;
    }

    // Majority
    int target;
    public int majorityElement(int[] num) {
        // use modified version of quick sort
        int hi = num.length - 1;
        if (hi == 0)
            return num[hi];

        sort(num, 0, hi);
        return target;
    }

    public void exch(int[] a, int l, int r) {
        int t = a[l];
        a[l] = a[r];
        a[r] = t;
    }

    // 3 way quick sort
    public void sort(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int left = lo, right = hi;
        int i = lo + 1;
        while (i <= right) {
            if (a[i] < a[left]) {
                exch(a, left++, i++);
            } else if (a[i] > a[left]) {
                exch(a, i, right--);
            } else {
                i++;
            }
        }

        // early return
        if (i - left > a.length/2) {target = a[left]; return;}

        sort(a, lo, left - 1);
        sort(a, i, hi);
    }

    // voting algorithm
    public int majorityElement2(int[] num) {
        int maj = 0;
        int count = 0;
        for (int i = 0; i < num.length && count <= num.length/2; i++) {
            if (count == 0) {
                maj = num[i];
                count++;
            } else {
                if (maj == num[i]) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return maj;
    }

    // count and say
    public String countAndSay(int n) {
        String s = "1";
        if (n == 1)
            return s;
        n--;
        while (n > 0) {
            StringBuffer ns = new StringBuffer();
            char[] cs = s.toCharArray();
            int count = 1;
            int i = 1;
            char c = cs[0];
            while (i < cs.length) {
                if (cs[i] != c) {
                    ns.append(count);
                    ns.append(c);
                    c = cs[i];
                    count = 1;
                } else {
                    count++;
                }

                i++;
            }
            // append the last one
            ns.append(count);
            ns.append(c);

            s = ns.toString();

            n--;
        }
        return s;
    }

    // Binary search tree
    public class BSTIterator {
        LinkedList<Integer> queue;
        public BSTIterator(TreeNode root) {
            queue = new LinkedList<Integer>();
            readTree(root);
        }

        public void readTree(TreeNode node) {
            if (node == null) return;
            if (node.left != null) readTree(node.left);
            queue.add(node.val);
            if (node.right != null) readTree(node.right);
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return queue.peek() != null;
        }

        /** @return the next smallest number */
        public int next() {
            return queue.remove();
        }
    }


    // triangle
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;

        HashMap<Integer, Integer> sum = new HashMap<Integer, Integer>();
        sum.put(0, triangle.get(0).get(0));

        int tSize = triangle.size();
        for (int i = 1; i < tSize; i++) {
            List<Integer> row = triangle.get(i);
            HashMap<Integer, Integer> sumAux = new HashMap<Integer, Integer>();

            Set<Map.Entry<Integer, Integer>> entrySet = sum.entrySet();
            for (Map.Entry<Integer, Integer> entry : entrySet) {
                int index = entry.getKey();
                int value = entry.getValue();
                int v1 = value + row.get(index);
                if (sumAux.get(index) == null || sumAux.get(index) > v1)
                    sumAux.put(index, v1);
                int v2 = value + row.get(index + 1);
                if (sumAux.get(index + 1) == null || sumAux.get(index + 1) > v2)
                    sumAux.put(index + 1, v2);
            }
            sum = sumAux;
        }
        int min = Integer.MAX_VALUE;
        Set<Map.Entry<Integer, Integer>> entrySet = sum.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            if (min > entry.getValue())
                min = entry.getValue();
        }
        return min;
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int numOfRows = triangle.size();
        int[] sum = new int[numOfRows + 1];
        for (int i = numOfRows - 1; i >= 0; i--) {
            List<Integer> row = triangle.get(i);
            int rowSize = row.size();
            for (int j = 0; j < rowSize; j++) {
                sum[j] = row.get(j) + Math.min(sum[j], sum[j + 1]);
            }
        }
        return sum[0];
    }

    public static <T> LinkedList<T> createLinkedList(T...elements) {
        LinkedList<T> newList = new LinkedList<T>();
        for (T el : elements) {
            newList.add(el);
        }

        return newList;
    }



    public List<String> letterCombinations(String digits) {
        if (digits == null) return null;

        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('1', "1");
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        map.put('0', "0");

        char[] chars = digits.toCharArray();
        List<String> combinations = new LinkedList<String>();
        if (chars.length == 0) {
            combinations.add("");
            return combinations;
        }
        for (char c : chars) {
            String mappedStr = map.get(c);
            if (mappedStr != null) {
                combinations = letterCombinationsHelper(combinations, mappedStr);
            }
        }
        return combinations;
    }

    public List<String> letterCombinationsHelper(List<String> combinations, String lr) {
        LinkedList<String> newCombinations = new LinkedList<String>();
        char[] letters = lr.toCharArray();
        for (char c : letters) {
            if (combinations.size() == 0) {
                newCombinations.add(Character.toString(c));
            } else {
                for (String comb : combinations) {
                    newCombinations.add(comb + Character.toString(c));
                }
            }
        }
        return newCombinations;
    }

    /// Surrounded Regions
    private int XMAX, YMAX;
    private char[][] board;
    public void surroundedRegions(char[][] board) {
        this.board = board;
        if (board.length <= 1 || board[0].length <= 1)
            return;
        YMAX = board.length - 1;
        XMAX = board[0].length - 1;

        // search  and mark edge zeros and all their connected zeros to 1
        int iterationCount = 0;
        int start = 0;
        int end = XMAX + 1;
        boolean xAxis = true;
        int index = 0;
        while (iterationCount < 4) {
            while (index != end) {
                char c;
                int x, y;
                boolean increasing = start < end;
                x = xAxis ? index : (increasing ? 0 : XMAX);
                y = xAxis ? (increasing ? 0: YMAX) : index;

                c = board[y][x];

                if (c == 'O') {
                    // decide search directions; don't search backwards
                    int xd = xAxis ? 0 : (increasing ? -1 : 1);
                    int yd = xAxis ? (increasing ? 1 : -1) : 0;
                    // mark and search neighbor
                    searchConnectedZeros(x, y, xd, yd);
                }


                if (start < end) {
                    index++;
                } else {
                    index--;
                }
            }
            iterationCount++;
            xAxis = !xAxis;
            if (end == XMAX + 1) {
                end = YMAX + 1;
            } else if (end == YMAX + 1) {
                start = XMAX;
                end = -1;
            } else if (start == XMAX) {
                start = YMAX;
            }
            index = start;
        }

        // mark all other zero to X
        for (int x = 0; x <= XMAX; x++) {
            for (int y = 0; y <= YMAX; y++) {
                if (board[y][x] == 'O') {
                    board[y][x] = 'X';
                }
            }
        }

        // mark all 1s to 0;
        for (int x = 0; x <= XMAX; x++) {
            for (int y = 0; y <= YMAX; y++) {
                if (board[y][x] == '1') {
                    board[y][x] = 'O';
                }
            }
        }
    }

    private void searchConnectedZeros(int x, int y, int xd, int yd) {
        if (board[y][x] == 'O') {
            board[y][x] = '1';
            if (xd <= 0 && x > 0) {
                searchConnectedZeros(x-1, y, -1, 0);
            }
            if (xd >= 0 && x < XMAX) {
                searchConnectedZeros(x+1, y, 1, 0);
            }
            if (yd <= 0 && y > 0) {
                searchConnectedZeros(x, y-1, 0, -1);
            }
            if (yd >= 0 && y < YMAX) {
                searchConnectedZeros(x, y+1, 0, 1);
            }
        }

    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        // variables: start point - sp, current sum: cs, total sum: ts
        // init the sp to -1;, cs, ts to 0
        // from start point calculate the cs, ts,
        // if cs > 0 and sp = -1 set the sp to current index;
        // if cs > 0 and sp > 0 do nothing;
        // until the end of the loop, if ts > 0, the last start point will be the return value, otherwise no result;

        int sp = -1, cs = 0, ts = 0;
        if (gas == null || cost == null || gas.length != cost.length )
            return sp;

        int gr = 0; // gas remaining
        for (int i = 0; i < gas.length; ++i) {
            gr = gas[i] - cost[i];
            ts += gr;
            cs += gr;
            if (cs >= 0) {
                if (sp == -1)
                    sp = i;

            } else {
                cs = 0;
                sp = -1;
            }
        }

        if (ts >= 0)
            return sp;
        else
            return -1;
    }

    ////////  Convert Sorted List to Binary Search Tree /////////////////
    /*
    public TreeNode sortedListToBST(ListNode head) {
        // find the first mid point as the root;
        if (head == null) return null;
        ListNode tmp = findRoot(head, null);
        TreeNode root = new TreeNode(tmp.val);
        ListNode secondHead = tmp.next;
        tmp.next = null;
        findRoot(head, tmp, root, true);
        findRoot(secondHead, null, root, false);

        return root;
    }

    public ListNode findRoot(ListNode head, ListNode end) {
        if (head == null) return null;
        if (head == end) return null;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next != end && fast.next.next != null && fast.next.next != end) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public void findRoot(ListNode head, ListNode end, TreeNode root, Boolean left) {
        ListNode tmp = findRoot(head, end);
        if (tmp != null) {
            TreeNode newRoot = new TreeNode(tmp.val);
            if (left) {
                root.left = newRoot;
            } else {
                root.right = newRoot;
            }

            ListNode secondHead = tmp.next;
            tmp.next = null; // cut the middle link

            findRoot(head, tmp, newRoot, true);
            findRoot(secondHead, end, newRoot, false);

        }

    }
    */

    ////////  Convert Sorted List to Binary Search Tree 2: a better solution /////////////////

    // sort of a bottom up solution, node traverse only twice, once for get the length
    // second time: create the node one by one;
    private ListNode node = null; // global variable

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null){
            return null;
        }

        int size = 0;
        ListNode runner = head;
        node = head;

        while(runner != null){
            runner = runner.next;
            size ++;
        }

        return inorderHelper(0, size - 1);
    }

    public TreeNode inorderHelper(int start, int end){
        if(start > end){
            return null;
        }

        // depth first, will goes to the left most node
        // then the middle one, then the right node.
        // follow by one-level-up left most node,
        // so "node = node.next" will work
        int mid = start + (end - start) / 2;
        TreeNode left = inorderHelper(start, mid - 1);

        TreeNode treenode = new TreeNode(node.val);
        treenode.left = left;
        node = node.next;

        TreeNode right = inorderHelper(mid + 1, end);
        treenode.right = right;

        return treenode;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        ListNode cNode = null;
        boolean hasCycle = false;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                cNode = slow;
                hasCycle = true;
                break;
            }
        }

        if (hasCycle) {
            slow = head;
            while (slow != cNode) {
                slow = slow.next;
                cNode = cNode.next;
            }
            return cNode;
        } else {
            return null;
        }

    }

    // Implement strStr()
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;

        int hl = haystack.length();
        int nl = needle.length();

        if (nl == 0) return 0;

        int hp = 0; int np = 0;
        char hc, nc;
        char firstNeedle = needle.charAt(0);
        int resetPoint = -1;
        while (np != nl && hp != hl) {
            nc = needle.charAt(np);
            hc = haystack.charAt(hp);
            if (nc == hc) {
                // ("mississippi", "issip")
                if (resetPoint < 0 && np > 0 && hc == firstNeedle) {
                    resetPoint = hp;
                }
                np++; hp++;
            } else {
                if (np > 0) {
                    np = 0;
                    if (resetPoint > 0) {
                        hp = resetPoint;
                        resetPoint = -1;
                    }
                } else {
                    hp++;
                }
            }

        }
        int index = -1;
        if (np == nl)
            index = hp - nl;
        return index;
    }

    //////  Merge k Sorted Lists
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) return null;

        int size = lists.size();
        /*

        mid = (r - l) / 2;

        lefthalf = merge(l, mid);
        righthalf = merge(mid + 1, r);

        merge(lefthalf, righthalf)

         */
        return null;
    }

    public static void main(String[] args) {
        Main test = new Main();
        /*
        int[] prices = {1 , 4};

        int profit = test.maxProfit(prices);
        System.out.println(profit);
        */
        /*
        List<List<Integer>> triangle = new LinkedList<List<Integer>>();
        triangle.add(createLinkedList(1));
        triangle.add(createLinkedList(1, 2));
        triangle.add(createLinkedList(1, 2, 3));
        triangle.add(createLinkedList(1, 2, 3, 4));
        triangle.add(createLinkedList(1, 2, 3, 4, 5));
        triangle.add(createLinkedList(1, 2, 3, 4, 5, 6));
        triangle.add(createLinkedList(1, 2, 3, 4, 5, 6, 7));


        System.out.println( test.minimumTotal2(triangle));
        */

        //System.out.println(test.letterCombinations(""));
//        char board[][] = {{'X', 'O', 'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X', 'O', 'X'}, {'X','O','X','O','X','O'},{'O','X','O','X','O','X'}};
        /*
        char board[][] = {{'O', 'O'}, {'O','O'}};
        for (int i = 0; i < board.length; i++)
            System.out.println(board[i]);
        System.out.println(" ");
        test.surroundedRegions(board);
        for (int i = 0; i < board.length; i++)
            System.out.println(board[i]);
        */

//        ListNode head = createListNodeList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        /*
        ListNode runner = head;
        int cycleStartValue = 4;
        ListNode cycleStart = null;
        while (runner.next != null) {
            if (runner.val == cycleStartValue) cycleStart = runner;
            runner = runner.next;
        }
        runner.next = cycleStart;
*/
//        ListNode cStartResult = test.detectCycle(head);
//        System.out.println(cStartResult.val);

        int index = test.strStr("mississippi", "issip");
        System.out.println(index);

    }

}


