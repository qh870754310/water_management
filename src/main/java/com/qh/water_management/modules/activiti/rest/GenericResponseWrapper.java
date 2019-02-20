package com.qh.water_management.modules.activiti.rest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: qh
 * @Date: 2019/1/4 16:14
 * @Description:
 */
public class GenericResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream output;
    private int contentLength;
    private String contentType;

    public GenericResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new ByteArrayOutputStream();
    }

    public byte[] getData() {
        return output.toByteArray();
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return new FilterServletOutputStream(output);
    }

    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(getOutputStream(), true);
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
        super.setContentLength(contentLength);
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentType(String type) {
        this.contentType = type;
        super.setContentType(type);
    }

    public String getContentType() {
        return contentType;
    }
}
