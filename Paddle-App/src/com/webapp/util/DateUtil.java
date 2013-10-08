package com.webapp.util;
import java.util.StringTokenizer;
import java.util.GregorianCalendar;
import java.sql.Date;
import java.util.Calendar;
import java.util.Locale;

public class DateUtil {
    
    /** Creates a new instance of DateUtil */
    public DateUtil() {
    }
    
    
    public String displayDate(String oldFormat){
        //2005-08-24 oldformat
        //17-06-2005 newformat
        if(oldFormat !=null && oldFormat.length() == 10){
            StringTokenizer st = new StringTokenizer(oldFormat,"-");
            
            String year = st.nextToken();
            String month = st.nextToken();
            String day = st.nextToken();
            
            int tmpMonth = Integer.parseInt(month);
            if(tmpMonth<10){
                month = "0"+new Integer(tmpMonth).toString();
            }else{
                month = new Integer(tmpMonth).toString();
            }
            return day+"-"+month+"-"+year;
        }else{
            return null;
        }
    }
    
    public String displayDate2(String oldFormat){
        //2005-08-24 oldformat
        //17-06-2005 newformat
        if(oldFormat !=null && ((oldFormat.length() == 21) || (oldFormat.length() == 19))){
            StringTokenizer st = new StringTokenizer(oldFormat," ");   
            String date = st.nextToken();
            String time = st.nextToken();
            
            st = new StringTokenizer(date,"-");
            
            String year = st.nextToken();
            String month = st.nextToken();
            int tmpMonth = Integer.parseInt(month);
            if(tmpMonth<10){
                month = "0"+new Integer(tmpMonth).toString();
            }else{
                month = new Integer(tmpMonth).toString();
            }
            String day = st.nextToken();
            ////System.out.println("Day :"+day); 
            ////System.out.println("Month :"+month);
            ////System.out.println("Year :"+year);
            String displayDate = day+"-"+month+"-"+year;     
            
            
            return displayDate;
        }else{
            return null;
        }
    }
    
    public String displayDateDB(String oldFormat){
        //17-06-2005 oldformat
        //2005-08-24 newformat
        if(oldFormat !=null && oldFormat.length() == 10){
            StringTokenizer st = new StringTokenizer(oldFormat,"-");
            
            String day = st.nextToken();
            String month = st.nextToken();
            String year = st.nextToken();
            
            
            
            int tmpMonth = Integer.parseInt(month);
            if(tmpMonth<10){
                month = "0"+new Integer(tmpMonth).toString();
            }else{
                month = new Integer(tmpMonth).toString();
            }
            return year+"-"+month+"-"+day;
        }else{
            return null;
        } 
    }
 public java.sql.Date databaseDate1(String oldFormat){
        //17-06-2005
        if(oldFormat !=null && oldFormat.length() == 10){
            StringTokenizer st = new StringTokenizer(oldFormat,"-");
            int day = Integer.parseInt(st.nextToken());
            int month = (Integer.parseInt(st.nextToken()))-1;
            int year = Integer.parseInt(st.nextToken());
            ////System.out.println("Day :"+day); 
            ////System.out.println("Month :"+month);
            ////System.out.println("Year :"+year);
            GregorianCalendar tmp = new GregorianCalendar(year,month,day);
            java.sql.Date newFormat = new java.sql.Date(tmp.getTimeInMillis());
            return newFormat;
        }else{
            return null;
        }
        
    }
    public java.util.Date databaseDate(String oldFormat){
        //17-06-2005
        if(oldFormat !=null && oldFormat.length() == 10){
            StringTokenizer st = new StringTokenizer(oldFormat,"-");
            int day = Integer.parseInt(st.nextToken());
            int month = (Integer.parseInt(st.nextToken()))-1;
            int year = Integer.parseInt(st.nextToken());
            ////System.out.println("Day :"+day); 
            ////System.out.println("Month :"+month);
            ////System.out.println("Year :"+year);
            GregorianCalendar tmp = new GregorianCalendar(year,month,day);
            Date newFormat = new Date(tmp.getTimeInMillis());
            return newFormat;
        }else{
            return null;
        }
        
    }
    public int dateDiffInDays(String sFromDate, String sToDate){
        Calendar fromDate = Calendar.getInstance();
        if(sFromDate !=null && sFromDate.length() == 10){
            StringTokenizer st = new StringTokenizer(sFromDate,"-");
            int day = Integer.parseInt(st.nextToken());
            int month = (Integer.parseInt(st.nextToken()))-1;
            int year = Integer.parseInt(st.nextToken());
            ////System.out.println("Day :"+day); 
            ////System.out.println("Month :"+month);
            ////System.out.println("Year :"+year);
            fromDate = new GregorianCalendar(year,month,day);
        }else{
            return 0;
        }
        
        Calendar toDate = new GregorianCalendar();
        if(sToDate !=null && sToDate.length() == 10){
            StringTokenizer st = new StringTokenizer(sToDate,"-");
            int day = Integer.parseInt(st.nextToken());
            int month = (Integer.parseInt(st.nextToken()))-1;
            int year = Integer.parseInt(st.nextToken());
            ////System.out.println("Day :"+day); 
            ////System.out.println("Month :"+month);
            ////System.out.println("Year :"+year);
            toDate = new GregorianCalendar(year,month,day);
        }else{
            return 0;
        }
        long differenceInMillis = toDate.getTimeInMillis() - fromDate.getTimeInMillis();
        long differenceInDays = differenceInMillis /(24*60*60*1000);
        Long returnValue = new Long(differenceInDays);
        
        
        return returnValue.intValue();
    }
    
    
    
    
    
    
    
    
    public String databaseDate(String oldFormat,int hour,int min){
        //17/06/2005
        
        if(oldFormat !=null && oldFormat.length() == 10){
            StringTokenizer st = new StringTokenizer(oldFormat,"/");
            int day = Integer.parseInt(st.nextToken()); 
            int month = (Integer.parseInt(st.nextToken()));
            int year = Integer.parseInt(st.nextToken());
            ////System.out.println("Day :"+day);
            ////System.out.println("Month :"+month);
            ////System.out.println("Year :"+year);
            int seconds = 0;
            //'2005-06-22 10:14:56'
            String result = year+"-"+month+"-"+day+" "+hour+":"+min+":00";
            
            return result;
        }else{
            return null;
        }
        
    }
    public String displayDateWithTime(String oldFormatWithTime){
   // 2005-06-22 10:14:56
        if(oldFormatWithTime !=null && ((oldFormatWithTime.length() == 21) || (oldFormatWithTime.length() == 19))){
            StringTokenizer st = new StringTokenizer(oldFormatWithTime," ");   
            String date = st.nextToken();
            String time = st.nextToken();
            
            st = new StringTokenizer(date,"-");
            
            String year = st.nextToken();
            String month = st.nextToken();
            int tmpMonth = Integer.parseInt(month);
            if(tmpMonth<10){
                month = "0"+new Integer(tmpMonth).toString();
            }else{
                month = new Integer(tmpMonth).toString();
            }
            String day = st.nextToken();
            ////System.out.println("Day :"+day); 
            ////System.out.println("Month :"+month);
            ////System.out.println("Year :"+year);
            String displayDate = day+"-"+month+"-"+year+" "+time;  
            
            
            return displayDate;
        }else{
            return null;
        }
    }
    
    public String[] displayDate1(String oldFormat){
   // 2005-06-22 10:14:56
        if(oldFormat !=null && oldFormat.length() == 19){
            StringTokenizer st = new StringTokenizer(oldFormat," ");
            String date = st.nextToken();
            String time = st.nextToken();
            
            st = new StringTokenizer(date,"-");
            
            String year = st.nextToken();
            String month = st.nextToken();
            int tmpMonth = Integer.parseInt(month);
            if(tmpMonth<10){
                month = "0"+new Integer(tmpMonth).toString();
            }else{
                month = new Integer(tmpMonth).toString();
            }
            String day = st.nextToken();
            ////System.out.println("Day :"+day); 
            ////System.out.println("Month :"+month);
            ////System.out.println("Year :"+year);
            String displayDate = day+"/"+month+"/"+year;
            
            st = new StringTokenizer(time,":");
            String hour = st.nextToken();
            String min = st.nextToken();
            ////System.out.println("HR " + hour);
            ////System.out.println("Min " + min);
            String result[] = {displayDate,hour,min};
            return result;
        }else{
            return null;
        }
    }
    
     public static String getCurrentDate(){
        
        Calendar cal = new GregorianCalendar();
        
        int year = cal.get(Calendar.YEAR);             // 2002
        int mon = cal.get(Calendar.MONTH);           // 0=Jan, 1=Feb, ...
        int day = cal.get(Calendar.DAY_OF_MONTH);
        //System.out.println("Day ............."+day);
        String month = "";
        String dd = "";
        if(day<10){
            dd = "0"+day;
        }else{
            dd = Integer.toString(day);
        }
        switch(mon){
            case 0:
                month = "01";
                break;
            case 1:
                month = "02";
                break;
            case 2:
                month = "03";
                break;
            case 3:
                month = "04";
                break;
            case 4:
                month = "05";
                break;
            case 5:
                month = "06";
                break;
            case 6:
                month = "07";
                break;
            case 7:
                month = "08";
                break;
            case 8:
                month = "09";
                break;
            case 9:
                month = "10";
                break;
            case 10:
                month = "11";
                break;
            case 11:
                month = "12";
                
            default:
                break;
                
        }
        
        return year+"-"+month+"-"+dd;
        
    }
    
    public String getCurrentTime(){
        
        Calendar cal = new GregorianCalendar();
        // Get the components of the time
        int hour12 = cal.get(Calendar.HOUR);            // 0..11
        int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
        String min = String.valueOf(cal.get(Calendar.MINUTE));             // 0..59
        if(Integer.parseInt(min)<10){
            //String minutes = "0"+min;
            min = "0"+min;
            //min = Integer.parseInt(minutes);
            //System.out.println("minutes..........."+min);
        }
        int sec = cal.get(Calendar.SECOND);             // 0..59
        int ms = cal.get(Calendar.MILLISECOND);         // 0..999
        int ampm = cal.get(Calendar.AM_PM);             // 0=AM, 1=PM
        String am_pm = "";
        if(ampm==0){
            am_pm = "AM";
        }else{
            am_pm = "PM";
        }
        if(hour12==0){
            hour12=12;
        }
        return hour12+":"+min+" "+am_pm;
    }  
    
    /**
     * Method will compare date with Current Date
     * and returns Yesterday,Today or Tommorow
     * if found appropriately.
     *
     * Otherwise it returns date without change
     *
     * @author Sijar Ahmed
     */
    public static String convertToLaymanTime(String displayDate) {
        
        String currentDate = getSystemCurrentDate();
        
        String currDay = null;
        String currMonth = null;
        String currYear = null;
        String[] currDateArray = null;
        
        String displayDay = null;
        String displayMonth = null;
        String displayYear = null;
        String[] displayDateArray = null;
        
        
        ////// FIND WHETHER IS TODAY-YESTERDAY-TOMMOROW /////////
        if( displayDate.equalsIgnoreCase(currentDate)){
            return "Today";
        }else{
            //////////// parse Current Date ////////////?
            currDateArray = currentDate.split("-");
            currDay = currDateArray[0];
            currMonth = currDateArray[1];
            currYear = currDateArray[2];
            //System.out.println("CURRENT Date:"+ currDay + currMonth + currYear );
            //////////// parse Display Date ////////////?
            displayDateArray = displayDate.split("-");
            displayDay = displayDateArray[0];
            displayMonth = displayDateArray[1];
            displayYear = displayDateArray[2];
            //System.out.println("DISPLAY Date:"+ displayDay + displayMonth + displayYear );
            
            if( (currMonth.equalsIgnoreCase(displayMonth)) && (currYear.equalsIgnoreCase(displayYear)) ){
                ///// YESTERDAY--TOMMOROW ///
                if( Integer.parseInt(displayDay)== (Integer.parseInt(currDay)-1) ){
                    return "Yesterday";
                }else if(Integer.parseInt(displayDay)== (Integer.parseInt(currDay)+1) ){
                    return "Tommorrow";
                }else{
                    return displayDate;
                }
            }
        }
        return displayDate;
    }
	
/**
     * Method will return Current Date
     * in "dd-mm-yyyy" format
     *
     * @author Sijar Ahmed
     */
    public static String getSystemCurrentDate()  {
        
        Calendar cal = Calendar.getInstance();
        java.util.Date date = cal.getTime();
        Locale ukLocale = new Locale(Locale.ENGLISH.toString(),Locale.UK.toString()); //default
        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.DATE_FIELD,ukLocale);
        String currentDate = dateFormat.format(date);
        
        StringTokenizer tokenizer = new StringTokenizer(currentDate,"/");
        String month = tokenizer.nextToken();
        String day = tokenizer.nextToken();
        String year = "20" + tokenizer.nextToken();
        if(month.length()==1){
            month = "0" + month;
        }
        if(day.length()==1){
            day = "0" + day;
        }
        currentDate = day+ "-" +month+ "-" +year ;
        return currentDate;
    } 
    
    public static void main(String args[]){
        DateUtil ut = new DateUtil();
        //System.out.println((Long.parseLong("111111111111")));
        
        
        //System.out.println(("2,333").replace(',', '.'));
      //  double dd = new Double(("2,333").replace(',', '.')).doubleValue();
        
        //System.out.println(dd);
        ////System.out.println(ut.dateDiffInDays("20-05-2006","25-05-2006"));
       // System.out.println(ut.displayDateWithTime("2008-12-20 12:49:14"));  
        System.out.println(ut.getCurrentDate());
        /*String[] tt = ut.displayDate1("2005-06-22 10:14:56");
        ////System.out.println("2005-06-22 10:14:56");
        ////System.out.println("display date :" + tt[0]);
        ////System.out.println("Hour :" + tt[1]);
        ////System.out.println("Minute :" + tt[2]);
         */
        
        ////System.out.println("date : "+ut.databaseDate("22/01/2005",10, 10));
        
    }
    

}
