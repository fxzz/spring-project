package xyz.chuup.springproject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.chuup.springproject.domain.Todo;

import java.time.LocalDate;

@SpringBootTest
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {
        for (int i= 1; i<=100; i++) {
            Todo todo =Todo.builder()
                    .title("Title..." +i)
                    .content("content.."+ i)
                    .dueDate(LocalDate.of(2023,12,31))
                    .build();
            todoRepository.save(todo);
        }
    }


    @Test
    public void testSearch1() {

        todoRepository.search1();
    }
}
