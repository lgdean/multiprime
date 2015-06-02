(ns multiprime.core
  (:gen-class))

;; This is just a prime number sieve.
;; An earlier version was simpler and perhaps easier to follow,
;; but led to stack overflow somewhere before 1000 primes.
;; This approach is more complicated but equivalent.
(defn all-primes
  []
  (let [is-factor-of (fn [n] (fn [d] (= 0 (mod n d))))
        sieve (fn sieve [primes-so-far ints]
                (let [[prime & next-ints]
                      (drop-while #(some (is-factor-of %) primes-so-far) ints)]
                  (cons prime
                        (lazy-seq
                         (sieve (conj primes-so-far prime) next-ints)))))]
    (sieve [] (iterate inc 2))))

(defn next-prime
  [primes-so-far]
  (let [is-factor-of (fn [n] (fn [d] (= 0 (mod n d))))
        integers-following (fn [x] (iterate inc (inc x)))]
    (first (filter #(not-any? (is-factor-of %) primes-so-far)
                   (integers-following (last primes-so-far))))))

(defn primes
  "Return the first n primes."
  [n]
;  (nth (iterate (fn [xs] (conj xs (next-prime xs))) [2]) (dec n)))
  (take n (all-primes)))

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
