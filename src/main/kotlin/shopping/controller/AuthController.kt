package shopping.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import shopping.data.User
import shopping.data.UserAuth
import shopping.dtos.LoginDTO
import shopping.dtos.Message
import shopping.dtos.RegisterDTO
import shopping.service.AuthService

@RestController
@RequestMapping("v1/token")
class AuthController(@Autowired private val authService: AuthService) {

    @PostMapping("registrar")
    fun  register(@RequestBody body: RegisterDTO): ResponseEntity<UserAuth> {
        val user = UserAuth()
        user.name = body.name
        user.email = body.email
        user.password = body.password
        return ResponseEntity.ok(authService.register(user))
    }

    @GetMapping("/{id}")
    fun  fingByIdUser(@PathVariable id: Int): ResponseEntity<UserAuth>{
        val user = authService.findById(id)
        if(user.isPresent){
            return ResponseEntity.ok(user.get())
        }else{
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario $id no encoentrado")
        }
    }

    @GetMapping("/todos")
    fun listarTodos(): ResponseEntity<Iterable<UserAuth>>{
        return ResponseEntity.ok(authService.all())
    }

    @DeleteMapping("/{id}")
    fun  deleteUser(@PathVariable id: Int): ResponseEntity<String>{
        val user = authService.findById(id)
        val useredelete: UserAuth = user.orElse(null) ?: UserAuth()
        if(user.isPresent){
            authService.deleteById(useredelete)
            return ResponseEntity.ok("eliminado correctamente")
        }else{
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario $id no encoentrado")
        }
    }

    @PostMapping("auth")
    fun login(@RequestBody body : LoginDTO): ResponseEntity<Any>{
        val userInfo = this.authService.findByEmail(body.email)
            ?:  return  ResponseEntity.badRequest().body(Message("Usuario no encontrado"))
        return  ResponseEntity.ok(userInfo)
    }
}