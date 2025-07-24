package com.preet.Journal.App.Repository;

import com.preet.Journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {

    User findByusername(String username);

}
