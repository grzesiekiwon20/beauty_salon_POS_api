package com.beautysalon.appcontroller;


import com.beautysalon.config.SpringWebConfig;
import com.beautysalon.user.UserServiceImpl;
import com.beautysalon.webmvc.AppController;
import com.beautysalon.webmvc.GlobalModelAdvice;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.WebAttributes;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = AppController.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = { GlobalModelAdvice.class , SpringWebConfig.class}
        )
)
class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockitoBean
    private UserServiceImpl userService;


    @Test
    void mainPage_ShouldReturnMainView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"));
    }

    @Test
    void homePage_ShouldReturnHomeView() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    void loginPage_ShouldReturnLoginView() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void loginError_WithoutError_ShouldReturnLoginViewWithoutErrorMessage() throws Exception {
        mockMvc.perform(get("/login-error"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeDoesNotExist("errorMessage"));
    }

    @Test
    void loginError_WithAuthenticationExceptionInSession_ShouldAddErrorMessageToModel() throws Exception {
        // Create a mock session with an AuthenticationException
        MockHttpSession session = new MockHttpSession();
        AuthenticationException authEx = new AuthenticationException("Bad credentials") {};
        session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, authEx);

        mockMvc.perform(get("/login-error").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("errorMessage", "Bad credentials"));
    }

    @Test
    @WithMockUser
    void accountPage_WithAuthenticatedUser_ShouldReturnAccountViewWithUserDetails() throws Exception {
        mockMvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andExpect(view().name("account"));
    }

    @Test
    void accountPage_WithoutAuthentication_ShouldRedirectToLogin() throws Exception {
        mockMvc.perform(get("/account"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}