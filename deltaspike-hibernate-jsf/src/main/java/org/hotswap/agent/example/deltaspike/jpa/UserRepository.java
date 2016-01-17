package org.hotswap.agent.example.deltaspike.jpa;


import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public abstract class UserRepository extends AbstractEntityRepository<User, Long> {

    public abstract User findOptionalByUserName(String userName);

    public abstract User findOptionalByUserId(Long userId);
}
