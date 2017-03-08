/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.MysqlDB.repository;

import org.jarivm.relationGraph.MysqlDB.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jari Van Melckebeke
 * @since 08.03.17.
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
