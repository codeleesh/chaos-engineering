package chaos.engineering.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<Hello, Long> {
}
