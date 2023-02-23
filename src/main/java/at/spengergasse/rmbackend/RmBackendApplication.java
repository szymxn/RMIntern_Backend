package at.spengergasse.rmbackend;

import at.spengergasse.rmbackend.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RmBackendApplication  {

    public static void main(String[] args) {
        SpringApplication.run(RmBackendApplication.class, args);
    }
}
