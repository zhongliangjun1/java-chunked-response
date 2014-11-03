package com.demo;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Author: liangjun.zhong
 * Date: 14-11-3
 * Time: PM8:33
 * To change this template use File | Settings | File Templates.
 */
public class StrutsChunkedDemo extends ActionSupport {
    private static final long serialVersionUID = 4628610425393084782L;


    // http://localhost:8080/struts/chunked
    @Override
    public String execute() throws Exception {

        HttpServletResponse response = ServletActionContext.getResponse();

        String firstPart = "<html>\n" +
                "    <head>\n" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "        <script src=\"/js/lib/jquery-min.js\"></script>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "\n" +
                "        <h2>this part is rendered at first time</h2>\n" +
                "\n" +
                "        <div id=\"J_chunked\">\n" +
                "            <!-- <h2>this part is rendered secondary and is responded by chunked</h2> -->\n" +
                "        </div>";

        String secondPart = "<script type=\"text/javascript\">\n" +
                "            console.log(\"get chunked response\");\n" +
                "            $(\"#J_chunked\").html(\"<h2>this part is rendered secondary and is responded by chunked</h2>\");\n" +
                "        </script>";

        String theEnd = "</body>\n" +
                "</html>";

        //response.setHeader("Transfer-Encoding", "chunked");
        PrintWriter writer = response.getWriter();
        //ServletOutputStream writer = response.getOutputStream();
        writer.print(firstPart);
        response.flushBuffer();
        Thread.sleep(2000);
        writer.print(secondPart);
        response.flushBuffer();
        Thread.sleep(5000);
        writer.print(theEnd);
        response.flushBuffer();

        return null;
    }
}
