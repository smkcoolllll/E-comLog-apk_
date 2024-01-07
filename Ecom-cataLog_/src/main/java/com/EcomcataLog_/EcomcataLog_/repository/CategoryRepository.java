package com.EcomcataLog_.EcomcataLog_.repository;

import com.EcomcataLog_.EcomcataLog_.entity.Catagory;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Catagory,Long> {


}
