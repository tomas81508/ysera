(ns ysera.numeric
  (:require
    [ysera.test :refer [is=]]))

(defn round
  {:doc "Deprecated. Use ysera.math/round."
   :test (fn []
           (is= (round 2.4)
                2)
           (is= (round 2.5)
                3))}
  [n]
  #?(:clj  (Math/round (double n))
     :cljs (js/Math.round n)))