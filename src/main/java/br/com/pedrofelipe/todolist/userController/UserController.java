package br.com.pedrofelipe.todolist.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        // Verifica se o usuário já existe pelo username
        UserModel user = this.userRepository.findByUsername(userModel.getUsername());
        
        if (user != null) {
           return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario já existe");
        }

       var passworsHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
       userModel.setPassword(passworsHashred);
        // Salva o novo usuário
        UserModel userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
