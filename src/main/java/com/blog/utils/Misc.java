package com.blog.utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;


public class Misc {
	public static final String number = "0123456789";
	
	public static boolean isStringEmpty(String str) {
		boolean ret = false;
		if (str == null || "".equals(str.trim())) {
			ret = true;
		}
		return ret;
	}
	
	public static boolean isNumber(String str) {
	    if(str == null)
	        return false;
		for (int i = 0; i < str.length(); i++) {
			if (number.indexOf(str.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isMobile(String mobile){
	       
	       boolean isMobile = false;
	       /*
	        * modify by xiongwei
	        *  中国移动：134、135、136、137、138、139、150、151、152、157、158、159、187、188、182、147
	           中国联通：130、131、132、155、156、185、186.
	           中国电信：133、153、180、189/177
	        */
	       String regex = "^(1((3[0-9])|(5[012356789])|(8[0-9])|(4[012356789])|(7[012356789]))([0-9]{8}))|(0[0-9]{10,11})$";
	       isMobile = mobile.trim().matches(regex);
	       
	       return isMobile;
		}
	
	public static String getIp(HttpServletRequest request){
		String ip  = request.getHeader("X-Real-IP");
		if(ip == null){
			 ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String getMobileRandom(){
		return getNumberRandom(6);
		//return getRandomString(6).toUpperCase();
	}
	public static String getNumberRandom(int len) {
		String sRand = "";
		for(int i=0;i<len;i++)
			sRand += (int) Math.round(Math.random() * 9);
		return sRand;
	}
	
	/**
	 * 把日期字符串转化成yyyy-MM-dd HH:mm:ss.SSS日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date convertString2Date(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.SSS");
		if (date == null)
			return new Date();
		try {
			return new Date(formatter.parse(date).getTime());
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date convertString2Date(String date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		if (date == null)
			return new Date();
		try {
			return new Date(formatter.parse(date).getTime());
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static int getDaysBetween(Date date1, Date date2) {
		int days = 0;
		days = Math
				.abs((int) ((date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000)));
		return days;
	}
	/**
	 * 把日期转化成日期时间格式字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String convertTimestamp2StringTime(Date time) {
		if (time == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(time);
	}

	/**
	 * 把日期转化成指定格式的日期时间格式字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String convertTimestamp2StringTime(Date time, String format) {
		if (time == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(time);
	}

	public static Date getDateBeforeDays(Date d,int day){
	   Calendar now =Calendar.getInstance();
	   now.setTime(d);
	   now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
	   return now.getTime();
	}
	
	
	/**
	 * 获取年月的日期
	 * @param date
	 * @return
	 */
	public  static Date getDateByYearMonth(Integer year,Integer month) {
		if(year == null || month == null)
			 return new Date();
		try {
		    Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.YEAR,year);
	        cal.set(Calendar.MONTH,month-1);
	        return cal.getTime();
		} catch (Exception e) {
			return new Date();
		}
    
    }
	
	/**
	 * 判断某个日期是星期几
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static int getDayForWeek(Date date){
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    int dayForWeek = 0;
	    if(c.get(Calendar.DAY_OF_WEEK) == 1){
	        dayForWeek = 7;
	    }else{
	        dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
	    }
	    return dayForWeek;
	}
	
	
	/**
	 * 获取某月最后一天几号
	 * @param date
	 * @return
	 */
	public  static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

	/**
	 * 获取某月第一天几号
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

	/**
	 * 取的几个月前的日期
	 * @param cdate
	 * @param months
	 * @return
	 */
	public static Date getDateBeforeMonth(Date cdate,int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(cdate);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-months);
        return cal.getTime();
    }
	
	/**
	 * 把日期转化成指定格式的日期时间格式字符串
	 * 
	 * @param time
	 * @return
	 */
	public static Date convertDate2SpecifyFormat(Date time, String format) {
		if (time == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String s_date = formatter.format(time);
		Date cdate;
		try {
			cdate = new Date(formatter.parse(s_date).getTime());
		} catch (ParseException e) {
			return null;
		}
		return cdate;
	}
	
	/**
	 * 把字符串转换成日期时间格式
	 * 
	 * @param time
	 * @return
	 */
	public static Date convertString2DateSpecifyFormat(String time, String format) {
		if (time == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date cdate;
		try {
			cdate = formatter.parse(time);
		} catch (ParseException e) {
			return null;
		}
		return cdate;
	}
	
	public static Date getDateAfterMinutes(int minutes) {
		if (minutes == 0)
			minutes = 120;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minutes);
		return new Date(calendar.getTimeInMillis());
	}
	
	public static String getCurrentURLWithHost(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append("http://");
		sb.append(request.getServerName());
		if (request.getServerPort() != 80) {
			sb.append(":").append(request.getServerPort());
		}
		sb.append(request.getRequestURI());
		String queryString = request.getQueryString();
		if ((queryString != null) && (!queryString.equals(""))) {
			sb.append("?");
			sb.append(queryString);
		}

		return sb.toString();
	}
	
	/**
	 * 压缩图片
	 * @param input
	 * @param imageType
	 * @param maxWidth
	 * @param maxSize
	 * @return
	 */
	  public static InputStream  compressPhoto(InputStream input,int inputSize,String imageType,int maxWidth,int maxHeight,int maxSize){
		  InputStream is = input;
		  try{
			 
			  BufferedImage bi = ImageIO.read(input);
	
			  int pwidth = (int) (bi.getWidth());
			  int pheight = (int) (bi.getHeight());
			  double ratioX = 1.0000;
			  double ratioY = 1.0000;
			  double Nheight = 0;
			  double Nwidth = 0;
	
			  
			  if(pwidth > maxWidth){
				  double scale =  (double)maxWidth / (double)pwidth >= (double)maxHeight / (double)pheight ? (double)maxHeight / (double)pheight : (double)maxWidth / (double)pwidth;
			      if (scale>1.0){
			          scale=1.0;
			      }
			      Nwidth = pwidth * scale;
			      Nheight = pheight * scale;
			      if(Nheight > maxHeight)
			    	  Nheight = maxHeight;
			      ratioX = Nwidth / pwidth;
				  ratioY = Nheight / pheight;
			   }
			   if(pheight > maxHeight){
			         double scale =  (double)maxHeight / (double)pheight >= (double)maxWidth / (double)pwidth ? (double)maxWidth / (double)pwidth : (double)maxHeight / (double)pheight;  
			         if (scale>1.0){
			             scale=1.0;
			         }
			         Nwidth = pwidth * scale;
				     Nheight = pheight * scale;
			         if(Nwidth > maxWidth)
			        	 Nwidth= maxWidth;
			         ratioX = Nwidth / pwidth;
					    ratioY = Nheight / pheight;
			    } 
			  
			  if(maxSize > 0 && inputSize!=0 && inputSize> maxSize){
				  
				  double sizeratioX = Math.sqrt((double)maxSize/inputSize);
				  //double sizeratioX  =  (double)maxSize/(double)inputSize;
				  
				   if(sizeratioX < ratioX){
					   ratioX = sizeratioX;
					   ratioY = sizeratioX;
				   }
				   
			   }
			   
			   AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratioX, ratioY), null);
			   Image Itemp = op.filter(bi, null);
			   ByteArrayOutputStream   bs   =new   ByteArrayOutputStream();  
			   ImageOutputStream   imOut   =ImageIO.createImageOutputStream(bs);
			   
			   ImageIO.write((BufferedImage) Itemp,imageType,imOut);
			   is   =new   ByteArrayInputStream(bs.toByteArray());

		  }catch(Exception e){
			  
			  e.printStackTrace();
			  return is;
		  }
		  return is;
		} 
	  public static boolean isImage(File o) {
	        try {
	            // Create an image input stream on the image
	            ImageInputStream iis = ImageIO.createImageInputStream(o);
	            // Find all image readers that recognize the image format
	            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
	            if (!iter.hasNext()) {
	                // No readers found
	                return false;
	            }
	            // Use the first reader
	            ImageReader reader = iter.next();
	    
	            // Close stream
	            iis.close();
	    
	            // Return the format name
	            return true;
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	        // The image could not be read
	        return false;
	    }
	  public static String encodeUserName(String username){
		  return encodeUserName(username,null);
	  }
		
	public static String encodeUserName(String username,String maxlen){
		if(username == null) return "";
		if(maxlen != null && maxlen.length() > 0){
	        int slen = username.getBytes().length;
	        int ilen = Integer.parseInt(maxlen.trim());
			if(slen > ilen){
				try {
					username = bSubstring(username,ilen);
				} catch (Exception e) {
					username = username.substring(0,ilen);
				}
			}	
		}
		int ulength = username.length();
		if(ulength > 2){
			if(isNumber(username))
				if(ulength ==11)
					return username.substring(0,3)+"****"+ username.substring(7);
				else if(ulength >11){
					return username.substring(0,4)+"******"+ username.substring(10);
				}else
					return username.substring(0,username.length()-2) + "**";
			else if(username.toLowerCase().startsWith("qq")){
				if(isNumber(username.substring(2))){
					return username.substring(0,username.length()-2) + "**";
				}else{
					return Misc.simpleEncodeUsername(username);
				}
			}else
				return Misc.simpleEncodeUsername(username);
		}else if(ulength == 2){
		    return username.substring(0,1)+"*";
		}else
			return username;
	}
	public static String bSubstring(String s, int length) throws Exception{
		try {

			byte[] bytes = s.getBytes("Unicode");
			int n = 0; // 表示当前的字节数
			int i = 2; // 要截取的字节数，从第3个字节开始
			for (; i < bytes.length && n < length; i++) {
				// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
				if (i % 2 == 1) {
					n++; // 在UCS2第二个字节时n加1
				} else {
					// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
					if (bytes[i] != 0) {
						n++;
					}
				}
			}
			// 如果i为奇数时，处理成偶数
			if (i % 2 == 1) {
				// 该UCS2字符是汉字时，去掉这个截一半的汉字
				if (bytes[i - 1] != 0)
					i = i - 1;
				// 该UCS2字符是字母或数字，则保留该字符
				else
					i = i + 1;
			}
			return new String(bytes, 0, i, "Unicode");
		} catch (Exception e) {
			throw e;
		}
	}
	public static String simpleEncodeUsername(String username){
		if(username == null)
			return "";
		int pos = username.indexOf("@");
		if(pos >= 2){
		    String str = null;
		    if(pos >=4){
		        str = username.substring(pos-4,pos+1);
		        return username.replace(str, "****@");
		    }else{
		        str = username.substring(pos-2,pos+1);
		        return username.replace(str, "**@");
		    }
		}else{//所有用户都加密2012-11-29
			if(username.length() > 2 && !"社区管理员".equals(username) 
					&& !"比购攻城师".equals(username) && !"比购小助理".equals(username)
					&& !"爆料专家".equals(username)){
				return username.substring(0,username.length()-2) + "**";
			}
			return username;
		}
		
	}
	public static String encodeIP(String ip){
		if(ip == null) 
			return "";
		int len = 0;
		int pos = ip.lastIndexOf(".");
		if(pos != -1)
			len = ip.substring(pos+1).length();
		else
			return ip;
		
		ip = ip.substring(0,pos+1);
		for (int i = 0; i < len; i++) {
			ip += "*"; 
		}
		return ip;
	}
	/**
	 * 格式化数值，不会四舍五入，len：保留小数位
	 * @param money
	 * @param len
	 * @return
	 */
	public static Double formatDouble(Double money,int len){
	    if(money == null)
	        return 0.0;
	    try {
            DecimalFormat formater = new DecimalFormat();
            formater.setMaximumFractionDigits(len);
            formater.setGroupingSize(0);
            formater.setRoundingMode(RoundingMode.DOWN);
            return Double.valueOf(formater.format(money));
        } catch (Exception e) {
            return 0.0;
        }
    }
	/**
     * 格式化数值,会四舍五入,格式为#,###.00
     * @param d
     * @return
     */
	public static String getDecimalFormat(double d) {
		DecimalFormat fmt = new DecimalFormat("#,###.00");
		String outStr = null;
		try {
			outStr = fmt.format(d);
		} catch (Exception e) {
			outStr = String.valueOf(d);
		}
		if (outStr.startsWith("."))
			outStr = "0" + outStr;
		if (outStr.startsWith("-."))
            outStr = "-0" + outStr.substring(1);
		return outStr;
	}
	/**
     *按照指定的格式格式化数值,会四舍五入
     * @param d
     * @return
     */
	public static String getDecimalFormat(double d, String format) {
		DecimalFormat fmt = new DecimalFormat(format);
		String outStr = null;
		try {
			outStr = fmt.format(d);
		} catch (Exception e) {
			outStr = String.valueOf(d);
		}
		if (outStr.startsWith("."))
			outStr = "0" + outStr;
		if (outStr.startsWith("-."))
		    outStr = "-0" + outStr.substring(1);
		return outStr;
	}

	/**
	 * BigDecimal保留几位小数，多余小数位舍去
	 * @param b
	 * @param scale  保留的小数位
	 * @return
	 */
	public static BigDecimal getDecimalFormat(BigDecimal b,int scale){
		BigDecimal result = new BigDecimal(0);
		if(b==null){
			return result;
		}
		result = b.setScale(scale,BigDecimal.ROUND_DOWN);
		return result ;
	}

	public static Double parseDouble(String s){
		if(s == null)
			return 0d;
		try {
			s = s.trim().replace(",", "");
			return Double.parseDouble(s);
		} catch (Exception e) {
			return 0d;
		}
	}
	
	public static Integer parseInteger(String s){
		if(s == null)
			return 0;
		try {
			s = s.trim().replace(",", "");
			return Integer.parseInt(s);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static Short parseShort(String s){
		if(s == null)
			return 0;
		try {
			s = s.trim().replace(",", "");
			return Short.parseShort(s);
		} catch (Exception e) {
			return 0;
		}
		
	}
	public static Long parseLong(String s){
		if(s == null)
			return 0l;
		try {
			s = s.trim().replace(",", "");
			return Long.parseLong(s);
		} catch (Exception e) {
			return 0L;
		}
	}
	
	/**
	 * 获取整数最大值
	 * @param num
	 * @return
	 */
	public static Long getMaxNum(long num){
		long max = 0;
		if(num<=0){
			return 0L;
		}
		if(num>=1000000000 && num<10000000000l){
			double result = Misc.parseDouble(String.valueOf(num)).doubleValue()/100000000;
			result = Math.ceil(result);
			max = (long) (result*100000000);
			
		}else if(num>=100000000 && num<1000000000){
			double result = Misc.parseDouble(String.valueOf(num)).doubleValue()/10000000;
			result = Math.ceil(result);
			max = (long) (result*10000000);
			
		}else if(num>=10000000 && num<100000000){
			double result = Misc.parseDouble(String.valueOf(num)).doubleValue()/1000000;
			result = Math.ceil(result);
			max = (long) (result*1000000);
		}else if(num>=1000000 && num<10000000){
			double result = Misc.parseDouble(String.valueOf(num)).doubleValue()/100000;
			result = Math.ceil(result);
			max = (long) (result*100000);
		}else if(num>=100000 && num<1000000){
			double result = Misc.parseDouble(String.valueOf(num)).doubleValue()/10000;
			result = Math.ceil(result);
			max = (long) (result*10000);
		}else if(num>=10000 && num<100000){
			double result = Misc.parseDouble(String.valueOf(num)).doubleValue()/1000;
			result = Math.ceil(result);
			max = (long) (result*1000);
		}else if(num>=1000 && num<10000){
			double result = Misc.parseDouble(String.valueOf(num)).doubleValue()/100;
			result = Math.ceil(result);
			max = (long) (result*100);
		}else if(num<1000){
			double result = Misc.parseDouble(String.valueOf(num)).doubleValue()/10;
			result = Math.ceil(result);
			max = (long) (result*10);
		}
		return max;
	}
	
}
