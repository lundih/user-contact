package com.lundih.usercontact.repositories;

import com.lundih.usercontact.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository that allows for fetching of {@link com.lundih.usercontact.entities.User} from the data source
 *
 * @author lundih
 * @since 0.0.1
 */
interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findBy(Pageable pageable);
}