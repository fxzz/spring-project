package xyz.chuup.springproject.repository.search;

import org.springframework.data.domain.Page;
import xyz.chuup.springproject.domain.Todo;
import xyz.chuup.springproject.dto.PageRequestDTO;

public interface TodoSearch {

    Page<Todo> search1(PageRequestDTO pageRequestDTO);
}
