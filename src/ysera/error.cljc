(ns ysera.error
  (:require [ysera.test :refer [is error?]]))

(defn error
  {:test (fn []
           (is (error? (error "hello"))))}
  [& strs]
  (let [message (apply str strs)]
    (throw #?(:cljs (js/Error message)
              :clj  (Exception. message)))))
