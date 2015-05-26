(ns multiprime.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; it came from the REPL... and is cool but a little hard to follow.
(defn one-and-primes
  []
  (let [divisible-by? (fn [d] (fn [n] (= 0 (mod n d))))
        remove-multiples-of (fn [x s] (remove (divisible-by? x) s))
        range-starting-from (fn [x] (drop x (range)))
        next-item (fn [foo] (cons (first (rest foo))
                                  (remove-multiples-of (first (rest foo))
                                                       (rest (rest foo)))))]
    (map first (iterate next-item (range-starting-from 1)))))

(defn primes
  "Return the first n primes."
  [n]
  (take n (drop 1 (one-and-primes))))
