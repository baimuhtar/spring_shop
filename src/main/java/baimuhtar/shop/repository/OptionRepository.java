package baimuhtar.shop.repository;

import baimuhtar.shop.entity.Category;
import baimuhtar.shop.entity.Option;
import baimuhtar.shop.entity.Value;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

}
