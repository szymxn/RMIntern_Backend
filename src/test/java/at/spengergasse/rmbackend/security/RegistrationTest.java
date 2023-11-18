package at.spengergasse.rmbackend.security;

import at.spengergasse.rmbackend.Domain.User;
import at.spengergasse.rmbackend.persistence.UserRepository;
import at.spengergasse.rmbackend.presentation.UserRestController;
import at.spengergasse.rmbackend.service.UserService;
import at.spengergasse.rmbackend.service.dto.UserDto;
import at.spengergasse.rmbackend.service.dto.command.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mysql.cj.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
public class RegistrationTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRestController userRestController;

    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();

        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void ensureRegisterAndLoginWorksProperly() throws Exception{
        UserDto user = new UserDto("Sel", "Tan", "P@ssw0rd", "TAN19731@spengergasse.at", false);


        mockMvc.perform(post("/api/user/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isOk());

        LoginDto loginDto = new LoginDto(user.getEmail(), user.getPassword());

        mockMvc.perform(post("/api/user/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(loginDto)))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/perform_logout"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
