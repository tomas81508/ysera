(ns ysera.time)

(defn now []
  "Gets the time in milliseconds."
  #?(:clj  (System/currentTimeMillis)
     :cljs (.getTime (js/Date.))))
