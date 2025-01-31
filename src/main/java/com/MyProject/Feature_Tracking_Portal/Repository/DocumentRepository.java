package com.MyProject.Feature_Tracking_Portal.Repository;
import com.MyProject.Feature_Tracking_Portal.Models.Document;
//import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import java.util.List;

//@NoArgsConstructor
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

// i need the document by (featureid) all the documents , and show them on the page.[ i think single single query will be there]

}