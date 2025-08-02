package com.start.springrestdemo.payload.auth.Album;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PhotoViewDTO {

    private long id;

    private String name;

    private String desciption;

    
}