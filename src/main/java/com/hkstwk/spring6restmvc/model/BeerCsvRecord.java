package com.hkstwk.spring6restmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerCsvRecord {
    private Integer row;
    private Integer count_x;
    private Float abv;
    private String ibu;
    private Integer id;
    private String beer;
    private String style;
    private Integer brewery_id;
    private Float ounces;
    private String style2;
    private Integer count_y;
    private String brewery;
    private String city;
    private String state;
    private String label;
}
