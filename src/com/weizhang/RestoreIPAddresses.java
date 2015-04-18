package com.weizhang;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei Zhang on 4/18/15.
 *
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

 For example:
 Given "25525511135",

 component start with 0 is not valid

 return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

 *
 *
 *
 *
 * Helping methods of Java:
 Explode :

 String[] exploded="Hello World".split(" ");
 Implode :

 String imploded=StringUtils.join(new String[] {"Hello", "World"}, " ");
 *
 */
public class RestoreIPAddresses {
    private String givenString;
    private List<String> validIPs;
    public List<String> restoreIpAddresses(String s) {
        givenString = s;
        validIPs = new ArrayList<String>();
        List<String> components = new ArrayList<String>();
        dfs(components, 0);
        return validIPs;
    }

    public void dfs(List<String> components, int nextComponentStartIndex) {
        int numComponents = components.size();

        if (numComponents == 4) {
            // end case
            if (nextComponentStartIndex == givenString.length()) {
                validIPs.add(ipAddressFromComponents(components));
            }
        } else {
            // average case
            for (int i = nextComponentStartIndex + 1; i <= givenString.length() && i <= nextComponentStartIndex + 3; i++) {
                String nextComponent = givenString.substring(nextComponentStartIndex, i);
                if (isValidIPComponent(nextComponent)) {
                    components.add(nextComponent);
                    dfs(components, i);
                    components.remove(components.size() - 1);
                }
            }
        }
    }

    private boolean isValidIPComponent(String component) {
        if (component.length() > 1 && component.charAt(0) == '0') return false;
        return Integer.parseInt(component) < 256;
    }

    private String ipAddressFromComponents(List<String> components) {
        return String.join(".", components);
    }

    public static void main(String[] args) {
        RestoreIPAddresses test = new RestoreIPAddresses();
//        test.restoreIpAddresses("25525511135");
//        test.restoreIpAddresses("");
        test.restoreIpAddresses("560789");
    }
}
