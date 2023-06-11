package baimuhtar.shop.repository;

import baimuhtar.shop.entity.Option;
import baimuhtar.shop.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueRepository extends JpaRepository<Value, Long> {
    Value findValuesByOptionAndProductId(Option option, Long productId);
}
