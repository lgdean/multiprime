(ns multiprime.core
  (:gen-class))

(defn all-primes
  []
  (let [remove-multiples-of (fn [d xs] (remove #(= 0 (mod % d)) xs))
        range-starting-from (fn [x] (drop x (range)))
        rel-prime (fn rel-prime [[x & xs]]
                    (cons x (lazy-seq (rel-prime (remove-multiples-of x xs)))))]
    (rel-prime (range-starting-from 2))))

(defn next-prime
  [primes-so-far]
  (first (filter (fn [n] (not-any? #(= 0 (mod n %)) primes-so-far))
                 (iterate inc (inc (last primes-so-far))))))

(defn primes
  "Return the first n primes."
  [n]
  (nth (iterate (fn [xs] (conj xs (next-prime xs))) [2]) (dec n)))
;; (take n (all-primes)))

(defn times-table
  "Return a multiplication table, with 'X' in the (0,0) position."
  [multiplicands]
  (let [header-row (cons "X" multiplicands)
        columns    (cons  1  multiplicands)]
    (cons header-row
          (map (fn [x] (map (fn [y] (* x y)) columns)) multiplicands))))

(defn -main
  "Prints a multiplication table of the first n (default 10) primes.
  Blows up un-gracefully if called with a non-integer value of n."
  ([] (-main "10"))
  ([n & other-args]
     (doall
      (map #(println (apply str (interpose "\t" %)))
           (times-table (primes (Integer/parseInt n)))))))
