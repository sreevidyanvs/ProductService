package dev.sreevidya.productServiceVtSpring.clients;

import dev.sreevidya.productServiceVtSpring.dtos.RatingDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
    private RatingDto rating;
}
