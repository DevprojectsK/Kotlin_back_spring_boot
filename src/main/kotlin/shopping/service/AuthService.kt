package shopping.service
import java.util.Optional
import shopping.data.UserAuth

interface AuthService{
    fun register(userAuth: UserAuth): UserAuth
    fun findById(id: Int): Optional<UserAuth>
    fun findByEmail(email: String): UserAuth?
    fun all(): Iterable<UserAuth>
    fun deleteById(userAuth: UserAuth)


}