package xyz.chuup.springproject.repository.search;

import org.springframework.data.domain.Page;
import xyz.chuup.springproject.domain.Todo;

public interface TodoSearch {

    Page<Todo> search1();
}
