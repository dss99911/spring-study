package kim.jeonghyeon.study.spring.domain

import javax.persistence.*

@Entity
class Office(
    @Id var id: Int,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    val address: Address? = null
)

@Entity
class Address(
    @Id var id: Int
)