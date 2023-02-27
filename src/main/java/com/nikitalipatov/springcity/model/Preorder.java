package com.nikitalipatov.springcity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "preorder")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Preorder {
    @Id
    private int id;

    private String preorderStatus;
    // v rabote, uspesho, ne uspeshno

    private String preorderResult;
    //мэппинг с  юзером мэни ту ван
    private int userId;
}
