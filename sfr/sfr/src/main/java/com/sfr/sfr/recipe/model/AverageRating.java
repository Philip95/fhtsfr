package com.sfr.sfr.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AverageRating {

    private List<Double> ratings;
    private Double averageRating;

}
