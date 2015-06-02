(ns multiprime.core
  (:gen-class))

;; This is just a prime number sieve.
;; An earlier version was simpler and perhaps easier to follow,
;; but had stack overflow around 1000 primes, because (filter (filter (etc))).
;; This approach is more complicated but equivalent:
;; it makes one big predicate and tries it on each candidate int.
(defn all-primes
  "Return a sequence of all the primes, in order."
  []
  (let [is-factor-of (fn [n] (fn [d] (= 0 (mod n d))))
        sieve (fn sieve [primes-so-far ints]
                (let [[prime & next-ints]
                      (drop-while #(some (is-factor-of %) primes-so-far) ints)]
                  (cons prime
                        (lazy-seq
                         (sieve (conj primes-so-far prime) next-ints)))))]
    (sieve [] (iterate inc 2))))

(defn primes
  "Return the first n primes."
  [n]
  (take n (all-primes)))

(defn times-table
  "Return a multiplication table (sequence of sequences),
  with 'X' in the (0,0) position."
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
