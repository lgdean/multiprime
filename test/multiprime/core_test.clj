(ns multiprime.core-test
  (:require [clojure.test :refer :all]
            [multiprime.core :refer :all]))

(deftest primes-test
  (testing "primes"
    (testing "zero case"
      (is (= (primes 0) '())))
    (testing "primes 10 should return first 10 primes."
      (is (= (primes 10) '(2 3 5 7 11 13 17 19 23 29))))))

(deftest times-table-test
  (testing "multiplication table"
    (testing "with empty input"
      (is (= [["X"]] (times-table []))))
    (testing "with multiple integers"
      (is (= [["X" 1 2 5]
              [1   1 2 5]
              [2   2 4 10]
              [5   5 10 25]]
             (times-table [1 2 5]))))))

(deftest integration-test
  (testing "output should be a multiplication table."
    (is (= ["X\t2\t3" "2\t4\t6" "3\t6\t9"]
           (clojure.string/split-lines (with-out-str (-main "2")))))))
