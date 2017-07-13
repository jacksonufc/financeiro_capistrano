package filtro;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import util.JPAUtil;

@WebFilter("/*")
public class EntityManagerFilter implements Filter {
  public void doFilter(ServletRequest request, ServletResponse response,
           FilterChain chain) throws IOException, ServletException {
    try {
      JPAUtil.beginTransaction();
      chain.doFilter(request, response);
      JPAUtil.commit();
    } catch (Throwable e) {
      System.out.println(e.getMessage() + "\n" + e.getCause());
      e.printStackTrace();
      JPAUtil.rollback();
      throw new ServletException(e);
    } finally {
      JPAUtil.closeEntityManager();
    }
  }

  public void destroy() {}
  public void init(FilterConfig arg0) throws ServletException {}
}
