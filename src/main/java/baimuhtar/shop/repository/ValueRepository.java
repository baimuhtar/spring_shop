package baimuhtar.shop.repository;

import baimuhtar.shop.entity.Option;
import baimuhtar.shop.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ValueRepository extends JpaRepository<Value, Long> {
    Value findValuesByOptionAndProductId(Option option, Long productId);

}
