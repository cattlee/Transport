package com.example.atom.Transport.config;

import java.util.Scanner;

/**
 * Created by atom on 2016/9/17.
 */
public class Td
{
    //求权重
    public static int function(int a){
        int sum=0;
        String st = Integer.toBinaryString(a);
        for(int i=0;i<st.length();i++){
            if(st.charAt(i) == '1'){
                sum++;
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int a=0,b=0;
        Scanner in = new Scanner(System.in);
        a = in.nextInt();
        b=a+1;
        while(true){
            if(function(a)==function(b)){
                break;
            }
            b++;
        }
        System.out.println(b);
    }
}
