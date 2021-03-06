(ns ysera.debug
  #?(:clj  (:require [net.cgrand.macrovich :as macros])
     :cljs (:require-macros [net.cgrand.macrovich :as macros]
                            [ysera.debug :refer [printlet printreturn]])))

(macros/deftime

  ; from http://brownsofa.org/blog/2014/08/03/debugging-in-clojure-tools/
  (defmacro printlet [bindings & body]
    `(let [~@(mapcat (fn [[n v]]
                       (if (or (vector? n) (map? n))
                         [n v]
                         [n v '_ `(println (name '~n) ":" ~v)]))
                     (partition 2 bindings))]
       ~@body))

  (defmacro printreturn
    ([expression] `(let [result# ~expression]
                     (println result#)
                     result#))
    ([str expression] `(let [result# ~expression]
                         (println ~str result#)
                         result#))))
