package com.example.demo.labs.lab11_hash;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EqualsHashCodeLabTest {

    @Test
    @DisplayName("equals와 hashCode를 함께 정의하면 HashSet이 정상 동작한다")
    void equals_and_hashcode_work_properly() {
        Subscribe s1 = new Subscribe("team.maeilmail@gmail.com", "backend");
        Subscribe s2 = new Subscribe("team.maeilmail@gmail.com", "backend");

        HashSet<Subscribe> set = new HashSet<>(List.of(s1, s2));

        System.out.println("s1 hashCode = " + s1.hashCode());
        System.out.println("s2 hashCode = " + s2.hashCode());
        System.out.println("set size = " + set.size());

        assertEquals(1, set.size());  // ✅ 이제 1이 나와야 정상
    }

    static class Subscribe {
        private final String email;
        private final String category;

        public Subscribe(String email, String category) {
            this.email = email;
            this.category = category;
        }

        // 1️⃣ 논리적 동등성 정의
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Subscribe that = (Subscribe) o;
            return Objects.equals(email, that.email)
                    && Objects.equals(category, that.category);
        }

        // 2️⃣ 반드시 equals 기준과 동일한 필드로 hashCode 생성
        @Override
        public int hashCode() {
            return Objects.hash(email, category);
        }
    }
}