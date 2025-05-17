package com.sharath.ecom.model;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Product")
public class Product {


    @Id
    private String id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private Boolean productAvailable;
    private int stockQuantity;
    private Date releaseDate = new Date();
    private String imageName;
    private String imageType;
    private byte[] imageData;
    private String ownerNumber;


}
