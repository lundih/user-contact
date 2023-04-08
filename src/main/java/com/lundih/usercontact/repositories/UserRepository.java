package com.lundih.usercontact.repositories;

import com.lundih.usercontact.entities.User;
import com.lundih.usercontact.enums.Nationality;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository that allows for fetching of {@link com.lundih.usercontact.entities.User} from the data source
 *
 * @author lundih
 * @since 0.0.1
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findBy(Pageable pageable);

    List<User> findAll(Example<User> example, Pageable pageable);

    Boolean existsByNationalIdAndNationality(String nationalId, Nationality nationality);
}