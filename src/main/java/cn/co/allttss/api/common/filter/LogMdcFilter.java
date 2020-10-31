package cn.co.allttss.api.common.filter;


import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 *
 */
@WebFilter(urlPatterns = "/*", filterName = "logMdcFilter")
public class LogMdcFilter implements Filter {
    private static final String UNIQUE_ID = "TRACE_ID";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean bInsertMDC = insertMDC();
        try {
            chain.doFilter(request, response);
        } finally {
            if (bInsertMDC) {
                MDC.remove(UNIQUE_ID);
            }
        }
    }

    @Override
    public void destroy() {
    }

    private boolean insertMDC() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString().replace("-", "");
        MDC.put(UNIQUE_ID, uniqueId);
        return true;
    }
}
