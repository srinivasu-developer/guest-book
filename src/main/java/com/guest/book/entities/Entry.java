package com.guest.book.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * An entity class to manage {@link Entry}s
 *
 * @author Srinivasu Nakka
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "entries")
public class Entry {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String text;

    @CreatedDate
    private LocalDateTime date;

    private boolean isApproved;

    @Column(length = 64)
    private String image;

    @Transient
    public String getPhotosImagePath() {
        if (image == null || id == null) return null;
        return "/src/resources/user-photos/" + id + "/" + image;
    }

    public Entry(String name, String text) {
        Assert.hasText(name, "Name must not be null or empty!");
        Assert.hasText(text, "Text must not be null or empty!");
        this.name = name;
        this.text = text;
        this.date = LocalDateTime.now();
    }
}
