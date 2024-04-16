package io.superfarm;

interface UserRepository {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    void save(User user);
}
