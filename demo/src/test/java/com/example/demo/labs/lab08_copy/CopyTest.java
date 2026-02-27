package com.example.demo.labs.lab08_copy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class CopyTest {

    @Test
    void shallowCopy_shares_author_object() {
        Author author = new Author("조슈아");
        Book original = new Book("이펙티브 자바", author);

        Book copied = original.shallowCopy();
        copied.changeAuthorName("Joshua");

        // 얕은 복사는 Author를 공유하므로 원본도 같이 바뀜
        assertEquals("Joshua", original.getAuthor().getName());
        assertSame(original.getAuthor(), copied.getAuthor()); // 같은 객체(주소)인지 확인
    }

    @Test
    void deepCopy_creates_new_author_object() {
        Author author = new Author("마틴");
        Book original = new Book("리팩터링", author);

        Book copied = original.deepCopy();
        copied.changeAuthorName("Martin");

        // 깊은 복사는 Author까지 새로 만들므로 원본은 안 바뀜
        assertEquals("마틴", original.getAuthor().getName());
        assertEquals("Martin", copied.getAuthor().getName());
        assertNotSame(original.getAuthor(), copied.getAuthor()); // 다른 객체(주소)인지 확인
    }
}