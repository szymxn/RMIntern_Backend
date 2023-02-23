package at.spengergasse.rmbackend.presentation;

import at.spengergasse.rmbackend.Domain.User;
import at.spengergasse.rmbackend.service.UserService;
import at.spengergasse.rmbackend.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(UserRestController.BASE_URL)
public class UserRestController {
    public static final String BASE_URL ="/api/user";
    public static final String PATH_REGISTER ="/register";
    public static final String PATH_VAR_TOKEN ="/{token}";
    public static final String PATH_VAR_ID ="/{id}";

    public static final String PATH_UPDATE ="/update";
    public static final String PATH_ALL ="/all";
    private final UserService userService;

    @PostMapping({"",PATH_REGISTER})
    public HttpEntity<User> registerUser(@Valid @RequestBody UserDto userDto) throws Exception {
        User registerdUser = userService.registerNewUserAccount(userDto);
        return ResponseEntity.ok(registerdUser);
    }


    @GetMapping(PATH_VAR_TOKEN)
    public HttpEntity<User> getUserByToken(@PathVariable String token){
        User updatedUser = userService.getUser(token);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping(PATH_VAR_ID)
    public HttpEntity<Optional<User>> getUserById(@PathVariable Long id){
        Optional<User> updatedUser = userService.getUserByID(id);
        return ResponseEntity.ok(updatedUser);
    }

}
