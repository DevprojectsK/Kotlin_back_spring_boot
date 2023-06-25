package shopping.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import shopping.data.AuthUserDao
import shopping.data.UserAuth
import shopping.service.AuthService
import java.util.*

@Service
class AuthUserService(@Autowired private val authUserDao: AuthUserDao): AuthService {
    override fun register(userAuth: UserAuth): UserAuth {
        return this.authUserDao.save(userAuth)
    }
    override fun findById(id: Int): Optional<UserAuth> {
        return authUserDao.findById(id)
    }

    override fun findByEmail(email: String): UserAuth? {
        return this.authUserDao.findByEmail(email)
    }

    override fun all(): Iterable<UserAuth> {
        return authUserDao.findAll()
    }
    override fun deleteById(userAuth: UserAuth){
        return authUserDao.delete(userAuth)
    }


}