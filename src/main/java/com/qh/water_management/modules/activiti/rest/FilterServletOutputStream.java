package com.qh.water_management.modules.activiti.rest;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: qh
 * @Date: 2019/1/4 16:15
 * @Description:
 */
public class FilterServletOutputStream extends ServletOutputStream {

    private DataOutputStream stream;
    private WriteListener writeListener;

    public FilterServletOutputStream(OutputStream outputStream) {
        stream =  new DataOutputStream(outputStream);
    }

    public boolean isReady() {
        return true;
    }

    public void setWriteListener(WriteListener writeListener) {
        this.writeListener = writeListener;
    }

    public void write(int b) throws IOException {
        stream.write(b);
    }

    public void write(byte[] b) throws IOException {
        stream.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        stream.write(b, off, len);
    }

}
