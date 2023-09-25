package dev.sreevidya.productServiceVtSpring.models;

import lombok.*;

import java.util.Date;
@Getter
@Setter
public class BaseModel {
    private long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private boolean isDeleted;

}
