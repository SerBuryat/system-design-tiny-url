package com.systemdesign.tinyurl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_tiny_url")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TinyUrl {

    @Id
    @Column(name = "hash")
    private String hash;

    @Column(name = "url")
    private String url;
}
