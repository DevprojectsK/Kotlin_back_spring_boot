package shopping.data

import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Entity
class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var name = ""

    @Column(unique = true)
    var email = ""

    @Column
    var password = ""
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }
}