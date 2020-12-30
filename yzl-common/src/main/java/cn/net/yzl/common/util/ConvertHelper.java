package cn.net.yzl.common.util;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 *
 */
public class ConvertHelper {
    public  static int toInt32(Object object) {
        return toInt32(object,0);
    }
    public  static int toInt32(Object object,int defaultVal){
        String obj =ConvertHelper.toString(object);
        try {
            int result = Integer.parseInt(obj);
            return  result;
        }catch (Exception e){
            return defaultVal;
        }
    }
    public  static BigDecimal toBigDecimal(Object object) {
        return toBigDecimal(object,BigDecimal.ZERO);
    }
    public  static BigDecimal toBigDecimal(Object object,BigDecimal defaultVal){
        String obj =ConvertHelper.toString(object);
        try {
            BigDecimal result = new BigDecimal(obj);
            return  result;
        }catch (Exception e){
            return defaultVal;
        }
    }
    public  static Date toDate(int year, int month, int day){
        Calendar c = new GregorianCalendar(year,month,day);
        Date date=c.getTime();
        return date;
    }
    public  static Date toDate(int year,int month,int day,int hour,int minute,int second){
        Calendar c = new GregorianCalendar(year,month-1,day,hour,minute,second);
        Date date=c.getTime();
        return date;
    }
    public static Date toDate(Object obj) {
        String timeStr = ConvertHelper.toString(obj);

        //2012年02月25日 格式时
        if(Pattern.compile("^\\d{4}年\\d{1,2}月\\d{1,2}日$").matcher(timeStr).matches())
        {
            return toDate(timeStr, "yyyy年MM月dd日");
        }
        //2012-02-25
        if(Pattern.compile("^\\d{4}-\\d{1,2}-\\d{1,2}$").matcher(timeStr).matches())
        {
            return toDate(timeStr, "yyyy-MM-dd");
        }
        //2012/02/25
        if(Pattern.compile("^\\d{4}/\\d{1,2}/\\d{1,2}$").matcher(timeStr).matches())
        {
            return toDate(timeStr, "yyyy/MM/dd");
        }

        return toDate(timeStr, "yyyy-MM-dd HH:mm:ss");
    }
    public static Date toDate(Object obj,String format)
    {
        String timeStr =ConvertHelper.toString(obj);
        if(!StringHelper.checkStr(timeStr))
        {
            return  null;
        }
        Date date = null;
        try {
            //date = sdf.parse(timeStr);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date=simpleDateFormat.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    public static String formatDecimal(BigDecimal decimal, String format)
    {
        if(decimal==null)
        {
            return format;
        }
        java.text.DecimalFormat decformat=new java.text.DecimalFormat(format);
        return decformat.format(decimal);
    }

    public static BigDecimal toDecimal(Object value ){
        BigDecimal ret = BigDecimal.ZERO;
        if( StringHelper.checkStr(ConvertHelper.toString(value)) ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }

    public  static boolean toBool(String bool){
        if(!StringHelper.checkStr(bool))
        {
            return false;
        }
        if(bool.equals("1")||bool.toLowerCase().equals("true")){
            return true;
        }
        return false;
    }

    public static List<Integer> toIntList(String str){
        if(!StringHelper.checkStr(str))
        {
            return new ArrayList<Integer>();
        }
        //1,2,3,4
        String []balance_types =str.split(",");
        List<Integer> balance_typesint = new ArrayList<Integer>();
        for(String balance_typeStr : balance_types)
        {
            balance_typesint.add(ConvertHelper.toInt32(balance_typeStr));
        }
        return  balance_typesint;
    }

    public static String toString(Object object){
        if(object!=null)
        {
            return object.toString();
        }
        return "";
    }

    /*
     * 将数组的参数转换成sql字符串参数，可以直接用in
     *
     * */
    public static String toSqlStrParams(String[] strList){
        if(strList==null || strList.length==0)
            return "";
        String str="";
        for (String item:strList){
            if("".equals(str)){
                str="'"+ item +"'";
            }
            else
                str+=",'"+ item +"'";
        }
        return str;
    }

    public static String formatBigDecimal(Object m,String format) {

        BigDecimal d = toBigDecimal(m);
        return  toBigDecimalString(d,format);//#,##0.00
    }


    public static String formatBigDecimal(Object m) {

        BigDecimal d = toBigDecimal(m);
        return  toBigDecimalString(d,"#,##0.00");//#,##0.00
    }

    public static String toBigDecimalString(BigDecimal d) {
        return  toBigDecimalString(d,"##0.00");//#,##0.00
    }

    public static String toBigDecimalString(BigDecimal d,String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);//格式化设置
        return decimalFormat.format(d);
    }
    //文件转换byte
    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }
}
