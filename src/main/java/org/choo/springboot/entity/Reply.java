package org.choo.springboot.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rno;
    private String text;
    private String replyer;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
}
