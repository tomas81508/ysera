(ns ysera.math
  (:require [ysera.test :refer [is=]]))

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
           (is= (floor 2.4)
                2)
           (is= (floor 2.5)
                2))}
  [x]
  #?(:clj  (int (Math/floor (double x)))
     :cljs (js/Math.floor x)))

(defn ceil
  {:test (fn []
           (is= (ceil 2.4)
                3)
           (is= (ceil 2.5)
                3))}
  [x]
  #?(:clj  (int (Math/ceil (double x)))
     :cljs (js/Math.ceil x)))

(defn sin [x]
  #?(:clj  (Math/sin (double x))
     :cljs (js/Math.sin x)))

(defn cos [x]
  #?(:clj  (Math/cos (double x))
     :cljs (js/Math.cos x)))