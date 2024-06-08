package dev.attaphong.ecommerce_app_study.model;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum Role {
    @FieldNameConstants.Include ADMIN,
    @FieldNameConstants.Include OPERATION,
    @FieldNameConstants.Include USER
}
