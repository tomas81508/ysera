(ns ysera.collections
  (:require [ysera.test :refer [is is=]]))

(defn index-of
  "Gets the index of the given element of the collection."
  {:test (fn []
           (is= (index-of ["a" "b" "c"] "b")
                1)
           (is= (index-of ["a" "b" "c"] "z")
                nil)
           (is= (index-of [] "b")
                nil))}
  [coll x]
  (first (keep-indexed (fn [y z] (when (= z x) y)) coll)))

(defn seq-contains?
  "Determines if the given element is contained in the given collection."
  {:test (fn []
           (is (seq-contains? ["a" "b" "c"] "a")))}
  [coll x]
  (not (nil? (index-of coll x))))

(defn remove-one
  "Removes one instance of the given element in the given collection."
  {:test (fn []
           (is= (remove-one ["a" "b" "c"] "a")
                ["b" "c"])
           (is= (remove-one ["a" "a" "c"] "a")
                ["a" "c"]))}
  [coll x]
  (let [[n m] (split-with (partial not= x) coll)] (concat n (rest m))))

(defn empty->nil
  "Converts the collection to nil if it is empty."
  {:test (fn []
           (is= (empty->nil []) nil)
           (is= (empty->nil [1]) [1])
           (is= (empty->nil '()) nil)
           (is= (empty->nil '(1)) '(1))
           (is= (empty->nil {}) nil)
           (is= (empty->nil {:a 1}) {:a 1})
           (is= (empty->nil #{}) nil)
           (is= (empty->nil #{1}) #{1}))}
  [coll]
  (if (empty? coll) nil coll))
