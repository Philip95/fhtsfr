package com.sfr.sfr.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AverageRating {
    private int id;
    private String name;
    private List<Double> averageRatingList;
    private double averageRating;
}