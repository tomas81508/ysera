(ns ysera.collections
  (:require [ysera.test :refer [is is=]]))

(defn
  ^{:doc  "Gets the index of the given element of the collection."
    :test (fn []
            (is= (index-of ["a" "b" "c"] "b")
                 1)
            (is= (index-of ["a" "b" "c"] "z")
                 nil)
            (is= (index-of [] "b")
                 nil))}
  index-of [coll x]
  (first (keep-indexed (fn [y z] (when (= z x) y)) coll)))

(defn
  ^{:test (fn []
            (is (seq-contains? ["a" "b" "c"] "a")))}
  seq-contains? [coll x]
  (not (nil? (index-of coll x))))

(defn
  ^{:doc  "Removes one instance of the given element in the given collection."
    :test (fn []
            (is= (remove-one ["a" "b" "c"] "a")
                 ["b" "c"])
            (is= (remove-one ["a" "a" "c"] "a")
                 ["a" "c"]))}
  remove-one [coll el]
  (let [[n m] (split-with (partial not= el) coll)] (concat n (rest m))))
