package shopping.data
import jakarta.persistence.*
import shopping.controller.dto.UserDto
import java.util.Date

@Entity
@Table(name="persona")
data class User(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id var id: Long?,
    var name: String,
    var email: String,
    var created: Date
){
    constructor(): this(null, "", "", Date())
    constructor(userDto: UserDto): this(null, userDto.name, userDto.email, Date())
}