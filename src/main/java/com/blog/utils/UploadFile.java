package com.blog.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.imageio.plugins.bmp.BMPImageReader;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import com.sun.imageio.plugins.png.PNGImageReader;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UploadFile {

	private static Map<String, String> ext = new HashMap<String, String>();

	static {
		ext.put("bmp", "bmp");
		ext.put("png", "png");
		ext.put("jpg", "jpg");
		ext.put("gif", "gif");
		ext.put("jpeg", "jpeg");
	}


	/**
	 * 实现图像的等比缩放和缩放后的截取, 处理成功返回true, 否则返回false
	 * @param file 要截取文件的路径
	 * @param saveFile 截取后输出的路径
	 * @param width 要截取宽度
	 * @param hight 要截取的高度
	 * @throws Exception
	 */
	public static boolean compress(File file, File saveFile,
								   int width, int hight) {
		boolean ret = false;
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			ret = compress(in, saveFile, width, hight);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ret = false;
		} finally{
			if(null != in){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return ret;
	}

	/**
	 * 实现图像的等比缩放和缩放后的截取, 处理成功返回true, 否则返回false
	 * @param in 要截取文件的路径
	 * @param saveFile 截取后输出的路径
	 * @param width 要截取宽度
	 * @param hight 要截取的高度
	 * @throws Exception
	 */
	public static boolean compress(InputStream in, File saveFile,
								   int width, int hight) {
//     boolean ret = false;
		BufferedImage srcImage = null;
		try {
			srcImage = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		if (width > 0 || hight > 0) {
			// 原图的大小
			int sw = srcImage.getWidth();
			int sh = srcImage.getHeight();
			// 如果原图像的大小小于要缩放的图像大小，直接将要缩放的图像复制过去
			if (sw > width && sh > hight) {
				srcImage = resize(srcImage, width, hight);
			} else {
				String fileName = saveFile.getName();
				String formatName = fileName.substring(fileName
						.lastIndexOf('.') + 1);
				try {
					ImageIO.write(srcImage, formatName, saveFile);
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}
		}
		// 缩放后的图像的宽和高
		int w = srcImage.getWidth();
		int h = srcImage.getHeight();
		// 如果缩放后的图像和要求的图像宽度一样，就对缩放的图像的高度进行截取
		if (w == width) {
			// 计算X轴坐标
			int x = 0;
			int y = h / 2 - hight / 2;
			try {
				saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		// 否则如果是缩放后的图像的高度和要求的图像高度一样，就对缩放后的图像的宽度进行截取
		else if (h == hight) {
			// 计算X轴坐标
			int x = w / 2 - width / 2;
			int y = 0;
			try {
				saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	/**
	 * 实现图像的等比缩放
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	private static BufferedImage resize(BufferedImage source, int targetW,
										int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx < sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
					targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(targetW, targetH, type);
		Graphics2D g = target.createGraphics();
		// smoother than exlax:
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	/**
	 * 实现缩放后的截图
	 * @param image 缩放后的图像
	 * @param subImageBounds 要截取的子图的范围
	 * @param subImageFile 要保存的文件
	 * @throws IOException
	 */
	private static void saveSubImage(BufferedImage image,
									 Rectangle subImageBounds, File subImageFile) throws IOException {
		if (subImageBounds.x < 0 || subImageBounds.y < 0
				|| subImageBounds.width - subImageBounds.x > image.getWidth()
				|| subImageBounds.height - subImageBounds.y > image.getHeight()) {
			return;
		}
		BufferedImage subImage = image.getSubimage(subImageBounds.x,subImageBounds.y, subImageBounds.width, subImageBounds.height);
		String fileName = subImageFile.getName();
		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
		ImageIO.write(subImage, formatName, subImageFile);
	}


	/**
     * 通过图片路劲获取扩展名
     * @param str
     * @return
     */
	public static String getExtension(String str) {
		str  = str.toLowerCase();
		String[] ext1 = str.split("\\.");
		for (String s : ext1) {
			if (ext.get(s) != null) {
				return "." + ext.get(s);
			}
		}
		return "";
	}
	
	/**
	 * 压缩图片方法,保证宽度
	 * 
	 * @param orginalFile
	 *            将要压缩的图片
	 * @param width
	 *            压缩宽
	 * @param height
	 *            压缩高
	 * @param quality
	 *            压缩清晰度 建议为1.0
	 * @param newImagePrefix
	 *            压缩图片后,文件名前缀
	 * @param extname
	 *           压缩图片后,添加的扩展名
	 * @author zhengsunlei
	 * @return 如果处理正确返回压缩后的文件名 null则参数可能有误
	 */
	public static String doCompress(File orginalFile, int width, int height,
			float quality, String newImagePrefix,String extname) {
		int new_w = width;
		int new_h = height;
		String newImage = null;
		if (orginalFile != null) {
			try {
				// 文件不存在
				if (!orginalFile.exists()) {
					return null;
				}
				/* 读取图片信息 */
				Image srcFile = ImageIO.read(orginalFile);
				new_w = width;
				new_h = height;
		
				// 为等比缩放计算输出的图片宽度及高度
				int w = srcFile.getWidth(null);
				int h = srcFile.getHeight(null);
		        float scale = getRatio(w,h,width,height); 
		        new_w = (int)(scale*w); 
		        new_h = (int)(scale*h); 

		        newImage = newImagePrefix+"X"+new_h + extname;
				/* 宽高设定 */
				BufferedImage tag = new BufferedImage(new_w, new_h,BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(srcFile.getScaledInstance(new_w, new_h,Image.SCALE_SMOOTH), 0, 0, null);
				String substr=newImagePrefix.substring(0,newImagePrefix.lastIndexOf("/")+1); 
				File newFile=new File(substr);
				
				if(!newFile.exists()){
					newFile.mkdirs();
				}
				/* 压缩之后临时存放位置 */
				FileOutputStream out = new FileOutputStream(newImage);

				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);

				/* 压缩质量 */
				jep.setQuality(quality, true);
				encoder.encode(tag, jep);

				out.close();
				srcFile.flush();
				
				//file.delete();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return  newImage;
		} else {
			return null;
		}
	}
	
	

	public static String doCompress(String orginalImage, int width, int height,
			float quality, String newImagePrefix,String extname) {
		if(quality <= 0)
			quality = 1;
		if (orginalImage != null ) {
			return doCompress(new File(orginalImage) , width, height,quality, newImagePrefix,extname);
		} 
		return null;
	}

	
	 public static boolean convert2LittlePic(int width, int height,String orginalPic,String newPic) {
		 
			String cmd ="/usr/bin/convert -resize "+width+"*"+height+" -sharpen 0x.4 " + orginalPic + " " + newPic;
			try {
				Process p = Runtime.getRuntime().exec(cmd);
				p.waitFor();		
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
	}

	/**
	 * 加亮
	 * @param originalPic
	 * @return
	 */
	public static BufferedImage getHighLightPicture(BufferedImage originalPic) {   
        int imageWidth = originalPic.getWidth();   
        int imageHeight = originalPic.getHeight();   
  
        BufferedImage newPic = new BufferedImage(imageWidth, imageHeight,   
                BufferedImage.TYPE_3BYTE_BGR);   
  
        short[] brighten = new short[256];   
        short pixelValue;   
  
        for (int i = 0; i < 256; i++) {   
            pixelValue = (short) (i + 10); 
            if (pixelValue > 255) {   
                pixelValue = 255;   
            }   
            brighten[i] = pixelValue;   
        }   
  
        LookupTable lut = new ShortLookupTable(0, brighten);   
        LookupOp lop = new LookupOp(lut, null);   
        lop.filter(originalPic, newPic);   
        return newPic;   
    } 
	
	public static float getRatio(int width,int height,int maxWidth,int maxHeight){ 
		    float ratio = 1.0f; 
		    float widthRatio ; 
		    float heightRatio ; 
		    widthRatio = (float)maxWidth/width; 
		    heightRatio = (float)maxHeight/height; 
		    if(widthRatio<1.0 || heightRatio<1.0){ 
		    	if(width > height)
		    		ratio = widthRatio>heightRatio?heightRatio:widthRatio; 
		    	else
		    		ratio = widthRatio>heightRatio?widthRatio:heightRatio; 
		    } 
		    return ratio; 
	} 

	public static void  writeFile(String fileurl,InputStream in) {
        try {
            int BUFFER_SIZE = 16 * 1024 ;
            File desfile=new File(fileurl);
            OutputStream out=new BufferedOutputStream( new FileOutputStream(desfile), BUFFER_SIZE); 
            byte [] buffer = new byte [BUFFER_SIZE];    
            while(in.read(buffer)>0){
                out.write(buffer);
            }
            out.flush();
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
	
	
	public static boolean copy(File src, File dst)   {    
		int BUFFER_SIZE = 16 * 1024 ;
        try  {    
           InputStream in = null ;    
           OutputStream out = null ;    
            try  {                    
               in = new BufferedInputStream( new FileInputStream(src), BUFFER_SIZE);    
               out = new BufferedOutputStream( new FileOutputStream(dst), BUFFER_SIZE);    
                byte [] buffer = new byte [BUFFER_SIZE];    
                while (in.read(buffer) > 0 )  {    
                   out.write(buffer);    
               }     
            } finally  {    
                if ( null != in)  {    
                   in.close();    
               }     
                 if ( null != out)  {    
                   out.close();    
               }     
           }
        } catch (Exception e)  { 
           e.printStackTrace(); 
          
           return false;
           
       }  
       return true;
   }     
	
	/**
	 * 复制文件并压缩
	 * @param src
	 * @param dst
	 * @param imgtype
	 * @param fileMaxsize
	 * @return
	 */
	public static boolean copy(File src, File dst,String imgtype,int maxWidth,int fileMaxsize)   {    
		int BUFFER_SIZE = 16 * 1024 ;
        try  {    
           InputStream in = null ;    
           OutputStream out = null ;    
            try  {                    
              
               in = Misc.compressPhoto( new FileInputStream(src),Integer.parseInt(String.valueOf(src.length())),imgtype,maxWidth,maxWidth,fileMaxsize);
               out = new BufferedOutputStream( new FileOutputStream(dst), BUFFER_SIZE);    
                byte [] buffer = new byte [BUFFER_SIZE];    
                while (in.read(buffer) > 0 )  {    
                   out.write(buffer);    
               }     
            } finally  {    
                if ( null != in)  {    
                   in.close();    
               }     
                 if ( null != out)  {    
                   out.close();    
               }     
           }
        } catch (Exception e)  { 
           e.printStackTrace(); 
          
           return false;
           
       }  
       return true;
   } 
	
	//返回严格的"/YYYY/MM"格式
	public static String getYMPath(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String str = sdf.format(new Date(System.currentTimeMillis()));
		String year=str.substring(0, 4);
		String month=str.substring(5);
		return "/"+year+ "/" + month;
	}
	
	public static String getYMDPath(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date(System.currentTimeMillis()));
		String year = str.substring(0, 4);
		String month = str.substring(5, 7);
		String dd = str.substring(8);

		return  "/" + year + "/" + month + "/" + dd;
	}
	
	/**
	 * 通过文件对象分析图片扩展名
	 * @param file
	 * @return
	 */
	public static String getImageType(File file) {
        try {
        	return getImageType(new FileInputStream(file));
        } catch (IOException e) {
        	e.printStackTrace();
        	return null;
        }
        // The image could not be read
    }
	
	/**
	 * 通过文件流分析图片扩展名
	 * @param input
	 * @return
	 */
	public static String getImageType(FileInputStream input) {
        try {
            // Create an image input stream on the image
            ImageInputStream iis = ImageIO.createImageInputStream(input);
            // Find all image readers that recognize the image format
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()) {
                // No readers found
                return null;
            }
            // Use the first reader
            ImageReader reader = iter.next();
            String type = null;
            if (reader instanceof GIFImageReader) {   
                type = ".gif";   
            } else if (reader instanceof JPEGImageReader) {   
                type = ".jpg";   
            } else if (reader instanceof PNGImageReader) {   
                type = ".png";   
            } else if (reader instanceof BMPImageReader) {   
                type = ".bmp";   
            }   
            // Close stream
            iis.close();
    
            // Return the format name
            return type;
        } catch (IOException e) {
        	e.printStackTrace();
        	return null;
        }
        // The image could not be read
    }
	
	public static byte[] readInputStream(InputStream inputStream)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int l = -1;
        byte[] by = new byte[1024];
        while ((l = inputStream.read(by)) >= 0) {
            baos.write(by, 0, l);
        }
        byte[] ss = baos.toByteArray();
        baos.flush();
        baos.close();
        return ss;
    }
	
}
