package com.example.RpnApi.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Stack;
@Component
@Entity
@Data@AllArgsConstructor@NoArgsConstructor
public class StackRpn {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private Stack<Integer> stack;

}
