(ns ysera.error
     (:require [ysera.test :refer [error?]]))

(defn error
  "Throws an error with the given messages"
  {:test (fn []
           (error? (error "hello")))}
  [& messages]
  (let [message (apply str messages)]
    (throw #?(:cljs (js/Error message)
              :clj  (Exception. message)))))
