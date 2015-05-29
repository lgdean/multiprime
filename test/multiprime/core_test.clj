(ns multiprime.core-test
  (:require [clojure.test :refer :all]
            [multiprime.core :refer :all]))

(deftest a-test
  (testing "primes 10 should return first 10 primes."
    (is (= (primes 10) '(2 3 5 7 11 13 17 19 23 29)))))

(deftest integration-test
  (testing "output should be a multiplication table."
    (is (= "X\t2\n2\t4\n" (with-out-str (-main 1))))))
