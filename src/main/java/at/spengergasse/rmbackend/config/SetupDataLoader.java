package at.spengergasse.rmbackend.config;

import at.spengergasse.rmbackend.Domain.Privilege;
import at.spengergasse.rmbackend.Domain.Role;
import at.spengergasse.rmbackend.Domain.User;
import at.spengergasse.rmbackend.persistence.PrivilegeRepository;
import at.spengergasse.rmbackend.persistence.RoleRepository;
import at.spengergasse.rmbackend.persistence.UserRepository;
import org.hsqldb.types.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component

public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if(alreadySetup){
            return;
        }

        Privilege read = createPrivilege("READ");
        Privilege write = createPrivilege("WRITE");
        Privilege passwordPrvilege = createPrivilege("CHANGE_PASSWORD");
        Privilege createForm = createPrivilege("CREATE");
        Privilege editForm = createPrivilege("EDIT");
        Privilege assingToForm = createPrivilege("ASSIGN_FORM");

        List<Privilege> admin = new ArrayList<>(Arrays.asList(read,write,passwordPrvilege,createForm,editForm,assingToForm));
        List<Privilege> customer = new ArrayList<>(Arrays.asList(read,assingToForm));
        List<Privilege> company = new ArrayList<>(Arrays.asList(read,write,createForm,editForm));

        Role adminRole = createRole("ADMIN",admin);
        Role customerRole = createRole("CUSTOMER",customer);
        Role companyRole= createRole("COMPANY",company);

        createUser("test@test.com", "Test", "Test", "test", new ArrayList<>(Arrays.asList(adminRole)));

       alreadySetup=true;

    }

    @Transactional
    Privilege createPrivilege(String name){
        Privilege privilege = privilegeRepository.findByName(name);
        if(privilege == null){
            privilege= new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }

        return privilege;
    }

    @Transactional
    Role createRole(String name, Collection<Privilege> privileges){
        Role role = roleRepository.findByName(name);
        if(role == null){
            role= new Role(name);
        }

        role.setPrivileges(privileges);
        roleRepository.save(role);
        return role;
    }

    @Transactional
    User createUser(String email, String vorName, String nachname, String password, Collection<Role> roles){
        User user = userRepository.findByEmail(email);
        if(user == null){
            user = new User();
            user.setVorname(vorName);
            user.setNachname(nachname);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setEnabled(true);
        }

        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }
}
