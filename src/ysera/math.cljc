(ns ysera.math
  (:require
    [ysera.test :refer [is=]]))

(defn round
  {:test (fn []
           (is= (round 2.4)
                2)
           (is= (round 2.5)
                3))}
  [n]
  #?(:clj  (Math/round (double n))
     :cljs (js/Math.round n)))

(defn floor
  {:test (fn []
           (is= (round 2.4)
                2)
           (is= (round 2.5)
                2))}
  [x]
  #?(:clj  (Math/floor (double x))
     :cljs (js/Math.floor x)))

(defn ceil
  {:test (fn []
           (is= (round 2.4)
                3)
           (is= (round 2.5)
                3))}
  [x]
  #?(:clj  (Math/ceil (double x))
     :cljs (js/Math.ceil x)))

(defn sin [x]
  #?(:clj  (Math/sin (double x))
     :cljs (js/Math.sin x)))

(defn cos [x]
  #?(:clj  (Math/cos (double x))
     :cljs (js/Math.cos x)))