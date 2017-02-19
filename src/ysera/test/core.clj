(ns ysera.test.core
  (:require [clojure.test]))

(defmacro deftest [name & body]
  (concat (list 'clojure.test/deftest name) body))

(defmacro is
  ([form]
   (list 'clojure.test/is form))
  ([form msg]
   (list 'clojure.test/is form msg)))

(defmacro testing [string body]
  (list 'clojure.test/testing string body))

(defmacro is= [actual expected]
  `(let [actual# ~actual
         expected# ~expected
         equal# (= actual# expected#)]
     (do
       (when-not equal#
         (println "Actual:\t\t" actual# "\nExpected:\t" expected#))
       (clojure.test/is (= actual# expected#)))))

(defmacro is-not [actual]
  `(clojure.test/is (not ~actual)))

(defmacro error? [actual]
  `(try (do
          ~actual
          (println "An error was expected.")
          (clojure.test/is false))
        (catch Exception e#
          (clojure.test/is true))))











