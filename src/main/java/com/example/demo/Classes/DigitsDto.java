package com.example.demo.Classes;

import com.example.demo.ControllerReturnable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DigitsDto implements ControllerReturnable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int start;
    private int last;
}
