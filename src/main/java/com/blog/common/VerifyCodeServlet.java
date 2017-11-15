package com.blog.common;

import com.blog.utils.Misc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    private static Log log = LogFactory.getLog(VerifyCodeServlet.class);

    private Random random = new Random();

    public void init() throws ServletException {
        super.init();

    }

    //生成随机颜色
    private Color getRandColor() {
        Color color[] = new Color[5];
        color[0] = new Color(255,0,0);
        color[1] = new Color(0, 255, 0);
        color[2] = new Color(0, 0, 255);
        color[3] = new Color(0, 0, 0);
        color[4] = new Color(255, 0, 255);
        return color[random.nextInt(5)];
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 生成服务器相应的service方法

        // 阻止生成的页面内容被缓存，保证每次重新生成随机验证码#####################

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 指定图形验证码图片的大小；
        int width = 80;// 宽度
        int height = 20;// 高度
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

        // 准备在图片中绘制内容
        //g.setColor,3次为了设置背景色
//		Graphics g = image.getGraphics();
        Graphics2D g = image.createGraphics();
        Random random = new Random();
//		g.fill3DRect(0, 0, width - 1, height - 1,true);
//		g.draw3DRect(0,0, width - 1, height - 1,false);

        //设置图片背景色
        g.fillRect(0, 0, width, height);

        // 设置图形验证码中的字符串的字体的大小
//		Font mFont = new Font("Times New Roman", Font.PLAIN,18);
        Font mFont = new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 18);
        g.setFont(mFont);
        //g.setColor(new Color(255,0,0));

        // 画边框。
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到。
        g.setColor(getRandColor(160,200));

        // 生成随机线条
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String regflag = request.getParameter("reg");
        String codekey = "code";
        if(!Misc.isStringEmpty(regflag)){
            codekey = codekey + regflag;
        }
        int len = 4;
        String sRand = "";
        String re=request.getParameter("re");//只请求图片
        HttpSession session = request.getSession(true);
        if(re!=null&&re.equals("1")){//
            sRand=session.getAttribute(codekey).toString();//从session中取出验证码
        }else{
            sRand = generateRandString(len);
            //log.debug("随机生成的字符串为" + sRand);
            session.setAttribute(codekey, sRand.toLowerCase());
            //log.debug("从session中取出来" + session.getAttribute("code"));
        }

        char[] carr = sRand.toCharArray();
        // 生成随机的字符串并加入到图片中
        for (int i = 0; i < len; i++) {
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            //g.setColor(getRandColor());
//			g.drawString(String.valueOf(carr[i]), 15 * i + 10, 15);
            g.drawString(String.valueOf(carr[i]), 10 * i + 20, 15);
        }

        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    private String generateRandString(int len){
        String sRand = "";
        String tmp = null;
        while(sRand.length() < len){
            tmp = getRandomChar().toLowerCase();
            if(!tmp.equals("0") && !tmp.equals("o")
                    && !tmp.equals("1") && !tmp.equals("i")
                    && !tmp.equals("l")){

                sRand = sRand + tmp;
            }
        }
        return sRand;

    }

    //随机生成字符串
    private String getRandomChar() {
        int rand = (int) Math.round(Math.random() * 2);
        long itmp = 0;
        char ctmp = '\u0000';
        switch (rand) {
            case 1:
                itmp = Math.round(Math.random() * 25 + 65);
                ctmp = (char) itmp;
                return String.valueOf(ctmp);
            case 2:
                itmp = Math.round(Math.random() * 25 + 97);
                ctmp = (char) itmp;
                return String.valueOf(ctmp);
            default:
                itmp = Math.round(Math.random() * 9);
                return String.valueOf(itmp);
        }
    }


}
