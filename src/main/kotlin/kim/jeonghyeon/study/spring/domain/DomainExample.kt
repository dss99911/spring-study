package kim.jeonghyeon.study.spring.domain

import java.sql.Timestamp
import javax.persistence.*
import kotlin.jvm.Transient


@Entity
class DomainExample(
    @Id var id: Int? = null,
//    @Id var id2: Int? = null,//able to use multiple primary key
    var title: String,
    @Enumerated(EnumType.ORDINAL) var status: Status,
    @Enumerated(EnumType.STRING) var type: Type,
    @Column(insertable = false, updatable = false) var registrationDate: Timestamp? = null,
) {

    @Transient
    var priority: Int = 0

    //todo what is this?
    @PostLoad
    internal fun fillTransient() {
        if (priority > 0) {
            this.priority = 1
        }
    }

    //todo what is this?
    @PrePersist
    internal fun fillPersistent() {
        if (priority != null) {
            this.priority = 2
        }
    }
}

enum class Status {
    ACTIVE, INACTIVE
}

enum class Type {
    type1, type2
}