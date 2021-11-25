(ns ysera.random
  (:require [ysera.test :refer [is= is]]
            [ysera.collections :refer [remove-one]]))

(defn- get-pseudo-random-number
  [seed]
  (let [xor-right (fn [x n] (bit-xor x (bit-shift-right x n)))
        xor-left (fn [x n] (bit-xor x (bit-shift-left x n)))]
    (+ 1 (-> (xor-left seed 21)
             (xor-right 35)
             (xor-left 4)))))

(defn get-random-int
  "Returns a new seed and a random integer x such that 0 <= x < max."
  {:test (fn []
           (is= (get-random-int 1 1)
                [35651602 0])
           (is= (get-random-int 35651602 12)
                [1130298060341683 10])
           (is= (get-random-int 35651602 0)
                [1130298060341683 0]))}
  [seed max]
  (case max
    0 [(get-pseudo-random-number seed) 0]
    [(get-pseudo-random-number seed) (mod seed max)]))

(defn random-nth
  "Returns a new seed and a random element of the collection."
  {:test (fn []
           (is= (random-nth 0 ["a" "b" "c" "d" "e" "f"])
                [1 "a"])
           (is= (random-nth 0 [])
                [0 nil]))}
  [seed coll]
  (if (empty? coll)
    [seed nil]
    (let [[new-seed random-number] (get-random-int seed (count coll))]
      [new-seed (nth coll random-number)])))

(defn take-n-random
  "Returns a new seed and (at most) n different random elements from the collection.
   If the collection contains less elements than n, all elements will be returned in a random order."
  {:test (fn []
           (is= (take-n-random 0 3 ["a" "b" "c" "d"])
                [1130298060341683 ["a" "c" "b"]])
           (is= (take-n-random 2 3 ["b" "a" "f"])
                [2 ["b" "a" "f"]])
           (is= (take-n-random 2 3 ["b" "a"])
                [2260595942425364 ["b" "a"]])
           (is= (take-n-random 2 2 ["b"])
                [71303203 ["b"]])
           (is= (take-n-random 2 3 [])
                [2 nil]))}
  [seed n coll]
  (cond (empty? coll)
        [seed nil]

        (= n (count coll))
        [seed coll]

        :else
        (let [[new-seed result _] (reduce (fn [[seed result rest] _]
                                            (let [[seed el] (random-nth seed rest)]
                                              [seed (conj result el) (remove-one rest el)]))
                                          [seed [] coll]
                                          (range n))]
          [new-seed (remove nil? result)])))

(defn shuffle-with-seed
  "Returns a new seed and the collection shuffled."
  {:test (fn []
           (is= (shuffle-with-seed 1 ["a" :b 3 "d"])
                [-9136436700791295257 [:b 3 "d" "a"]])
           (is= (shuffle-with-seed 2 [1 1 2])
                [39562792388305809 [2 1 1]]))}
  [seed coll]
  (let [[new-seed shuffled-coll _] (reduce (fn [[seed shuffled-coll coll] _]
                                             (let [[seed item] (random-nth seed coll)
                                                   coll (remove-one coll item)]
                                               [seed (conj shuffled-coll item) coll]))
                                           [seed [] coll]
                                           (range (count coll)))]
    [new-seed shuffled-coll]))

(defn get-random-uuid
  "Creates a random uuid-string."
  {:test (fn []
           (is (let [pattern #"[\w|\d]{8}-[\w|\d]{4}-[\w|\d]{4}-[\w|\d]{4}-[\w|\d]{11}"]
                 (re-find pattern (get-random-uuid)))))}
  []
  #?(:clj  (str (java.util.UUID/randomUUID))
     :cljs (cljs.core/random-uuid)))
