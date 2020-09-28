package app.springboot.rest.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import app.springboot.rest.models.DocumentObjectModel;

@Repository
public interface DocumentObjectRepository extends MongoRepository<DocumentObjectModel, String> {

}
