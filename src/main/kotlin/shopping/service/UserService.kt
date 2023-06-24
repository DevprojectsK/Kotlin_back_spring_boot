package shopping.service
import java.util.Optional
import shopping.data.User

interface UserService {

    fun findById(id: Long): Optional<User>

    fun create(user: User): User

    fun all(): Iterable<User>

    fun deleteById(user: User)

}