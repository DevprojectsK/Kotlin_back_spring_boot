package shopping.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import shopping.controller.dto.UserDto
import shopping.data.User
import shopping.service.UserService
import java.util.Optional

@RestController
@RequestMapping("v1/user")
class UserController(@Autowired private val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<User>{
        return ResponseEntity.ok(userService.create(User(userDto)))
    }

    @GetMapping("/{id}")
    fun finById(@PathVariable id: Long): ResponseEntity<User>{
        val userOptional = userService.findById(id)
        if(userOptional.isPresent){
            return ResponseEntity.ok(userOptional.get())
        }else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with id $id not found")
        }
    }

    @GetMapping("/all")
    fun all(): ResponseEntity<Iterable<User>>{
        return ResponseEntity.ok(userService.all())
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<String> {
        val userinfo: Optional<User> = userService.findById(id)
        val user: User = userinfo.orElse(null) ?: User()
        if (userinfo.isPresent){
            userService.deleteById(user)
            return ResponseEntity.ok("Eliminado correctamente")
        }else{
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "UUsuario id $id no encotnrado")
        }

    }

}