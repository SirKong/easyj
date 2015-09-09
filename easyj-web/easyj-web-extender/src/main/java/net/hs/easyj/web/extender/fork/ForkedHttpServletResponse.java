package net.hs.easyj.web.extender.fork;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * 拆分的 HttpServletResponse
 *
 * @author Gavin Hu
 * @create 2015/2/7
 */
public class ForkedHttpServletResponse extends HttpServletResponseWrapper {

    public ForkedHttpServletResponse(HttpServletResponse response) {
        super(response);
    }

    private ByteArrayOutputStream content = new ByteArrayOutputStream();

    private PrintWriter writer = new PrintWriter(content) {
        @Override
        public void write(int c) {
            super.write(c);
            super.flush();
        }

        @Override
        public void write(char[] buf, int off, int len) {
            super.write(buf, off, len);
            super.flush();
        }

        @Override
        public void write(char[] buf) {
            super.write(buf);
            super.flush();
        }

        @Override
        public void write(String s, int off, int len) {
            super.write(s, off, len);
            super.flush();
        }

        @Override
        public void write(String s) {
            super.write(s);
            super.flush();
        }
    };

    private ServletOutputStream outputStream = new ServletOutputStream() {
        @Override
        public void write(int b) throws IOException {
            content.write(b);
        }
    };

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return outputStream;
    }

    @Override
    public void addHeader(String name, String value) {
        // do nothing!
    }

    @Override
    public void setLocale(Locale loc) {
        // do nothing!
    }

    @Override
    public void setContentLength(int len) {
        // do nothing!
    }

    @Override
    public void setContentType(String type) {
        // do nothing!
    }

    @Override
    public String toString() {
        return new String(content.toByteArray());
    }

}
