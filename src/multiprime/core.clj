(ns multiprime.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn all-primes
  []
  (let [divisible-by? (fn [d] (fn [n] (= 0 (mod n d))))
        remove-multiples-of (fn [x s] (remove (divisible-by? x) s))
        range-starting-from (fn [x] (drop x (range)))
        rel-prime (fn rel-prime [[x & xs]]
                    (cons x (lazy-seq (rel-prime (remove-multiples-of x xs)))))]
    (rel-prime (range-starting-from 2))))

(defn primes
  "Return the first n primes."
  [n]
  (take n (all-primes)))
