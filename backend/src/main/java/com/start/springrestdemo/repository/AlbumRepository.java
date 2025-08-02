package com.start.springrestdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.start.springrestdemo.models.Album;


@Repository
public interface AlbumRepository extends JpaRepository<Album,Long>{
      
     public List<Album> findByAccount_id(Long id); 
}
