package com.guest.book.entities;

import com.guest.book.enums.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * An entity class to manage {@link Role}s
 *
 * @author Srinivasu Nakka
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Role(RoleEnum name) {
        this.name = name.name();
    }
}
