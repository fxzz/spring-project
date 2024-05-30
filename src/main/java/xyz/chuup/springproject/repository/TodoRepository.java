package xyz.chuup.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.chuup.springproject.domain.Todo;
import xyz.chuup.springproject.repository.search.TodoSearch;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {
}
