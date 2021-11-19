package app.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {


        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))){
            response.sendRedirect("mainform");
        }else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"))){
            response.sendRedirect("mainform");
        }else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_RISK"))){
            response.sendRedirect("risk");
        }else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_CORP"))){
            response.sendRedirect("corp");
        }

    }

}
