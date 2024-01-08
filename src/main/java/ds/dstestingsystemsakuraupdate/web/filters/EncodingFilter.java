package ds.dstestingsystemsakuraupdate.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Filter for encode any request to UTF-8 encoding
 */
public class EncodingFilter implements Filter {

    private static final Logger log = LogManager.getLogger(EncodingFilter.class);

    private String encoding;

    public void destroy() {
        log.debug("Filter destruction starts");
        // do nothing
        log.debug("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        log.debug("Filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        log.trace("Request uri --> " + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            log.trace("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }

        log.debug("Filter finished");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) {
        log.debug("Filter initialization starts");
        encoding = fConfig.getInitParameter("encoding");
        log.trace("Encoding from web.xml --> " + encoding);
        log.debug("Filter initialization finished");
    }

}

