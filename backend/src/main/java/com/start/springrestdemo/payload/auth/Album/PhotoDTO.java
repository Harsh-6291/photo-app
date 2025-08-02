package com.start.springrestdemo.payload.auth.Album;



import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO {
   private long id;
    private String name;
    private String description;
    private String fileName;
    private String download_link;
}