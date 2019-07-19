package hoperun.pagoda.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hoperun.pagoda.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
