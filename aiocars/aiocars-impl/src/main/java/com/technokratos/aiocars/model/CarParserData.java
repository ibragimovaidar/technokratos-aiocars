package com.technokratos.aiocars.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class CarParserData {

    @Column(name = "autoru_slug")
    private String autoruSlug;

    @Column(name = "dromru_slug")
    private String dromruSlug;
}
