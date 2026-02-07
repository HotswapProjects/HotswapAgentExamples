package org.hotswap.agent.example.deltaspike.jpa;


import jakarta.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

@ApplicationScoped
@Repository
public abstract class UserRepository extends AbstractEntityRepository<User, Long> {

    public abstract User findOptionalByUserName(String userName);

    @Query("SELECT u FROM User u WHERE userId=?")
    public abstract User findOptionalByUserId(Long userId);
}
