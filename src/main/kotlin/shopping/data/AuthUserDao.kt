package shopping.data

import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface AuthUserDao: CrudRepository<UserAuth, Int>{
    fun findByEmail(email:String) : UserAuth?
}