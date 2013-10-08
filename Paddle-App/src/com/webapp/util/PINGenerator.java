/*
 * GenerateScratchCodes.java
 *
 * Created on 29 March 2006, 14:09
 */

package com.webapp.util;

import java.security.SecureRandom;
 


/**
 *
 * @author  in4users
 */
public class PINGenerator extends SecureRandom{
    
    public String get10DigitPins(){
        String randomNum = "";
        try{
            getInstance("SHA1PRNG");
            randomNum = new Integer(this.next(1111)).toString();
            while(randomNum.length()!=4){
                ////System.out.println(randomNum);
                getInstance("SHA1PRNG");
                randomNum = new Integer(this.next(1111)).toString();
            }
            //randomNum+=random.nextInt(1234567);
            //randomNum+=random.nextInt();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return randomNum;
    }
    public static void main(String args[]){
        PINGenerator obj = new PINGenerator(); 
    // String generateSecureFileName(String maileusId,String Filename)   
      String uq=  obj.generateSecureFileName("Ganesh","issues.JPG");
        System.out.println(uq);
        
    }
    
    public String generate15DigitNumber(){
        String randomNum = "";
        try{
            getInstance("SHA1PRNG");                                                                                    
            randomNum = new Integer(this.next(9999)).toString();
            if(randomNum.length()<15){
                getInstance("SHA1PRNG");
                randomNum+= new Integer(this.next(10)).toString();
            }
            //randomNum+=random.nextInt(1234567);
            //randomNum+=random.nextInt();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return randomNum;
    }
    
        public String generateSecureFileName(String maileusId,String Filename){
        String randomNum = "";
        try{
            getInstance("SHA1PRNG");                                                                                    
            randomNum = new Integer(this.next(9999)).toString();
            if(randomNum.length()<15){
                getInstance("SHA1PRNG");
                randomNum+= new Integer(this.next(10)).toString();
           randomNum=maileusId+randomNum+Filename;
          
            }
            //randomNum+=random.nextInt(1234567);
            //randomNum+=random.nextInt();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return randomNum;
    }
    

    public static String crc(String input) {
        int key = 234;
        int len = input.length();
        for (int index = 0; index < len; index++)
            key = key * 973169 + input.charAt(index);
        
        return Long.toString(key & 0xffff, 16).toUpperCase();
    }
    
    
    public static String crc1(String input) {
        int key = 234;
        int len = input.length();
        for (int index = 0; index == len; index++)
            key = key * 973169 + input.charAt(index);
        
        return Long.toString(key & 0xffff, 16).toUpperCase();
    }
    

    public String generate10DigitNumber(int mobile){
        String randomNum = "";
        try{
         //   getInstance("SHA1PRNG");
             
            randomNum = new Integer(this.next(mobile)).toString();
            if(randomNum.length()==11){
              //  getInstance("SHA1PRNG");
                randomNum+= new Integer(this.next(10)).toString();
            }
            //randomNum+=random.nextInt(1234567);
            //randomNum+=random.nextInt();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return randomNum;
    }
    
    
   
    
    
    
    
    
    
}
    /*
    public void generate12DigitCode(){
        SecureRandom prng = null;
        String SQL = "";
        Connection con = null;
        PreparedStatement pstmt = null;
        String randomNum1 = "";
        String randomNum2 = "";
        long code1 = 0;
        long code2 = 0;
        try{
            for(int i=0;i<5;i++){
                randomNum1 = generate12DigitNumber();
                while(randomNum1.length()!=12){
                    randomNum1 = generate12DigitNumber();
                }
                ////System.out.println("lenght.............."+randomNum.length());
                randomNum2 = generate12DigitNumber();
                while(randomNum2.length()!=12){
                    randomNum2 = generate12DigitNumber();
                }
                con = DatabaseConnector1.getConnection();
                SQL = "INSERT INTO SCRATCHCODES VALUES(?,?,?)";
                pstmt = con.prepareStatement(SQL);
                pstmt.setString(1, "INFOSENSE");
                pstmt.setString(2, randomNum1);
                pstmt.setString(3, randomNum2);
                pstmt.executeUpdate();
            }
     
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if(pstmt!=null){
                    pstmt.close();
                }
            }catch(Exception e){
                pstmt = null;
            }
            try{
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                con = null;
            }
        }
    }
     
     
     
    public void generate10DigitCode(){
        SecureRandom prng = null;
        String SQL = "";
        Connection con = null;
        PreparedStatement pstmt = null;
        String randomNum1 = "";
        String randomNum2 = "";
        long code1 = 0;
        long code2 = 0;
        try{
            for(int i=0;i<5;i++){
                randomNum1 = generate10DigitNumber();
                while(randomNum1.length()!=10){
                    randomNum1 = generate10DigitNumber();
                }
                ////System.out.println("lenght.............."+randomNum.length());
                randomNum2 = generate10DigitNumber();
                while(randomNum2.length()!=10){
                    randomNum2 = generate10DigitNumber();
                }
                con = DatabaseConnector1.getConnection();
                SQL = "INSERT INTO SCRATCHCODES VALUES(?,?,?)";
                pstmt = con.prepareStatement(SQL);
                pstmt.setString(1, "INFOSENSE");
                pstmt.setString(2, randomNum1);
                pstmt.setString(3, randomNum2);
                pstmt.executeUpdate();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if(pstmt!=null){
                    pstmt.close();
                }
            }catch(Exception e){
                pstmt = null;
            }
            try{
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                con = null;
            }
        }
    }
     
    public String generate10DigitNumber(){
        String randomNum = "";
        try{
            getInstance("SHA1PRNG");
            randomNum = new Integer(this.next(9999)).toString();
            if(randomNum.length()<10){
                getInstance("SHA1PRNG");
                randomNum+= new Integer(this.next(10)).toString();
            }
            //randomNum+=random.nextInt(1234567);
            //randomNum+=random.nextInt();
     
        }catch(Exception e){
            e.printStackTrace();
        }
        return randomNum;
    }
     
     */



